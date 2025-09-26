package com.example.numbino_s305896.ui.sider

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.numbino_s305896.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numbino_s305896.ui.SpillViewModel
import com.example.numbino_s305896.ui.komponenter.DialogBoks
import com.example.numbino_s305896.ui.komponenter.ProgressIndikator
import com.example.numbino_s305896.ui.komponenter.RegnestykkeOgSvarBoks
import com.example.numbino_s305896.ui.komponenter.SpillTopBar
import com.example.numbino_s305896.ui.komponenter.TallRad
import com.example.numbino_s305896.ui.komponenter.TilbakemeldingsBilde

@Composable
fun SpillSkjermen (
    spillViewModel: SpillViewModel = viewModel(),
    vedAvbryt: () -> Unit
) {
    val ctx = LocalContext.current

    // Leser antall valgte oppgaver fra shared preferences
    val prefs = remember {ctx.getSharedPreferences("numbino_prefs", Context.MODE_PRIVATE)}
    val antall = prefs.getInt("antall_oppgaver", 5)

    // Laster oppgaver og fasit fra regnestykkerOgSvar.xml
    val oppgaver = stringArrayResource(R.array.regnestykker).toList()
    val fasit = integerArrayResource(R.array.svar).toList()

    // Starter et nytt spill når skjermen opprettes
        LaunchedEffect(Unit) {
        spillViewModel.alleRegnestykker = oppgaver
        spillViewModel.alleSvar = fasit
        spillViewModel.startNyttSpill(antall)
    }
    // Henter tilstanden til spill-skjermen fra ViewModel
    val spillUiState by spillViewModel.uiState.collectAsState()

    // Sender tilstanden til skjermen (UI)
    SpillSkjermUI(
        regnestykke = spillUiState.regnestykke,
        brukerSvar = spillUiState.brukerSvar,
        tilbakemelding = spillUiState.tilbakemelding,
        ferdig = spillUiState.ferdig,
        vedTallKlikk = { tall -> spillViewModel.sjekkSvar(tall)},
        vedAvbrytKlikk = vedAvbryt,
        vedStartPaNytt = {
            val antall = prefs.getInt("antall_oppgaver", 5)
            spillViewModel.startNyttSpill(antall)
        },
        current = spillUiState.current,
        total = spillUiState.total
    )
}

@Composable
fun SpillSkjermUI (
    regnestykke: String, // Regnestykket som skal vises
    brukerSvar: String, // Brukers svar på regnestykket
    tilbakemelding: Int, // Tilstand for visuell tilbakemelding (1=riktig, 2=feil, 3=venter)
    ferdig: Boolean, // True når alle regnestykker er brukt
    vedTallKlikk: (Int) -> Unit, // Funksjon som kalles når tallknappen trykkes
    vedAvbrytKlikk: () -> Unit, // Funksjon for avbryt-knapp
    vedStartPaNytt: () -> Unit, // Starter nytt spill
    current: Int, // Nåværende oppgave
    total: Int // Antall oppgaver i dette spillet
) {
    // Styrer avslutt?-dialog
    var visDialog by remember { mutableStateOf(false) }

    Scaffold (
        topBar = { SpillTopBar(onClose = { visDialog = true })
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background (MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progresjonsindikator som viser hvor mange oppgaver brukeren har tatt og har igjen
            ProgressIndikator(current = current, total = total)

            Spacer(modifier = Modifier.height(16.dp))

            // Figur som gir visuell tilbakemelding (riktig, feil eller venter)
            TilbakemeldingsBilde(tilstand = tilbakemelding)

            Spacer(modifier = Modifier.height(8.dp))

            // Regnestykke og svarfelt
            RegnestykkeOgSvarBoks(regnestykke, brukerSvar)

            Spacer(modifier = Modifier.height(14.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Rader med tallknapper
                TallRad(listOf(1, 2, 3), vedTallKlikk)
                Spacer(modifier = Modifier.height(8.dp))

                TallRad(listOf(4, 5, 6), vedTallKlikk)
                Spacer(modifier = Modifier.height(8.dp))

                TallRad(listOf(7, 8, 9), vedTallKlikk)
                Spacer(modifier = Modifier.height(8.dp))

                TallRad(listOf(0), vedTallKlikk)
            }

            // Avslutte spill dialogboks
            if (visDialog) {
                DialogBoks(
                    tittel = stringResource(R.string.dialog_tittel_avbryt),
                    tekst = stringResource(R.string.dialog_tekst_avbryt),
                    bekreftTekst = stringResource(R.string.dialog_ja),
                    avbrytTekst = stringResource(R.string.dialog_nei),
                    vedBekreft = {
                        visDialog = false
                        vedAvbrytKlikk()
                    },
                    vedAvbryt = { visDialog = false }
                )
            }
            // Ikke flere oppgaver igjen, dialogboks
            if (ferdig) {
                DialogBoks(
                    tittel = stringResource(R.string.spill_ferdig),
                    tekst = stringResource(R.string.spill_paa_nytt),
                    bekreftTekst = stringResource(R.string.dialog_ja),
                    avbrytTekst = stringResource(R.string.dialog_nei),
                    vedBekreft = vedStartPaNytt,
                    vedAvbryt = vedAvbrytKlikk
                )
            }
        }
    }
}