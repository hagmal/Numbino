package com.example.numbino_s305896.ui.sider

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.NummerKnappen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.numbino_s305896.ui.SpillViewModel
import com.example.numbino_s305896.ui.komponenter.AvbrytDialog
import com.example.numbino_s305896.ui.komponenter.RegnestykkeOgSvarBoks
import com.example.numbino_s305896.ui.komponenter.StartKnappen
import com.example.numbino_s305896.ui.komponenter.TallRad
import com.example.numbino_s305896.ui.komponenter.TilbakemeldingsBilde

@Composable
fun SpillSkjermen (
    spillViewModel: SpillViewModel = viewModel(),
    vedAvbryt: () -> Unit
) {
    val ctx = LocalContext.current
    val prefs = remember {ctx.getSharedPreferences("numbino_prefs", Context.MODE_PRIVATE)}
    val antall = prefs.getInt("antall_oppgaver", 5)

    val oppgaver = stringArrayResource(R.array.regnestykker).toList()
    val fasit = integerArrayResource(R.array.svar).toList()

    LaunchedEffect(Unit) {
        spillViewModel.alleRegnestykker = oppgaver
        spillViewModel.alleSvar = fasit
        spillViewModel.startNyttSpill(antall)
    }

    val spillUiState by spillViewModel.uiState.collectAsState()

    SpillSkjermUI(
        regnestykke = spillUiState.regnestykke,
        brukerSvar = spillUiState.brukerSvar,
        tilbakemelding = spillUiState.tilbakemelding,
        ferdig = spillUiState.ferdig,
        vedTallKlikk = { tall -> spillViewModel.sjekkSvar(tall)},
        vedAvbrytKlikk = vedAvbryt,
        vedStartPaNytt = { spillViewModel.startNyttSpill(antall) }
    )
}

@Composable
fun SpillSkjermUI (
    regnestykke: String, // Regnestykket som skal vises
    brukerSvar: String,
    tilbakemelding: Int, // Tilstand for visuell tilbakemelding (1=riktig, 2=feil, 3=venter)
    ferdig: Boolean,
    vedTallKlikk: (Int) -> Unit, // Funksjon som kalles når tallknappen trykkes
    vedAvbrytKlikk: () -> Unit, // Funksjon for avbryt-knapp
    vedStartPaNytt: () -> Unit
) {
    var visDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Avbryt-knapp som avslutter spillet.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(onClick = { visDialog = true }) {
                Icon(Icons.Default.Close,
                    contentDescription = "Avslutt spillet")
            }
        }
        // Viser tilbakemelding til brukeren basert på tilstand, dvs.
        // figuren endrer seg
        TilbakemeldingsBilde(tilstand = tilbakemelding)

        Spacer(modifier = Modifier.padding(8.dp))

        // Viser regnestykke og svarboks på en rad
        RegnestykkeOgSvarBoks(regnestykke = regnestykke, svar = brukerSvar)

        Spacer(modifier = Modifier.padding(16.dp))

        // Rader med tallknapper
        TallRad(tall = listOf(1, 2, 3), vedTallKlikk = vedTallKlikk)
        Spacer(modifier = Modifier.padding(8.dp))

        TallRad(tall = listOf(4, 5, 6), vedTallKlikk = vedTallKlikk)
        Spacer(modifier = Modifier.padding(8.dp))

        TallRad(tall = listOf(7, 8, 9), vedTallKlikk = vedTallKlikk)
        Spacer(modifier = Modifier.padding(8.dp))

        TallRad(tall = listOf(0), vedTallKlikk = vedTallKlikk)

        if (ferdig) {
            Text(
                text = stringResource(R.string.spill_ferdig),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(R.string.ikke_flere_oppgaver),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            StartKnappen(
                tekst = stringResource(R.string.spill_paa_nytt),
                onClick = vedStartPaNytt
            )
        }

        if (visDialog) {
            // Henter Avbryt-dialog-boksen fra komponenter i ui-pakka
            AvbrytDialog(
                vedLukk = { visDialog = false },
                vedBekreft = {
                    visDialog = false
                    vedAvbrytKlikk() }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SpillSkjermPreview() {
    Numbino_s305896Theme {
        SpillSkjermUI(
            regnestykke = "2 + 2 = ",
            tilbakemelding = 3,
            brukerSvar = "4",
            vedTallKlikk = {},
            vedAvbrytKlikk =  {},
            ferdig = false,
            vedStartPaNytt = {}
        )
    }
}
