package com.example.numbino_s305896.ui.sider

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.StartKnappen
import com.example.numbino_s305896.ui.komponenter.TilbakeTopBar
import com.example.numbino_s305896.ui.theme.Chewy

@Composable
fun PreferanserSkjerm(vedTilbake: () -> Unit) {
    val context = LocalContext.current

    // Lagre valgt antall oppgaver i shared preferences
    val sharedPrefs = remember {
        context.getSharedPreferences("numbino_prefs", Context.MODE_PRIVATE)
    }
    // Holder styr på valgt antall oppgaver. Starter med lagret verdi eller standardspill som er 5
    var valgtAntall by remember {
        mutableIntStateOf(sharedPrefs.getInt("antall_oppgaver", 5))
    }

    Scaffold (
        topBar = { TilbakeTopBar(vedTilbake = vedTilbake) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))

            // Bilde av figuren
            Image(
                painter = painterResource(id = R.drawable.ic_numbino), // <- bytt til din figur
                contentDescription = stringResource(R.string.cd_maskot),
                modifier = Modifier.size(120.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Tittel
            Text(
                text = stringResource(R.string.preferanser),
                fontFamily = Chewy,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(8.dp))
            // Beskrivelse
            Text(
                text = stringResource(R.string.preferanser_forklaring),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Løkke som lager tre knapper ved å gjenbruke knappene på startskjermen
            listOf(5, 10, 15).forEach { n ->
                StartKnappen(
                    tekst = n.toString(), // Viser tallet som tekst på knappen
                    onClick = {
                        // Oppdaterer valgt tall og lagrer i shared preferences
                        valgtAntall = n
                        sharedPrefs.edit().putInt("antall_oppgaver", n).apply()
                    },
                    // Farge på knappen, avhengig av om den er valgt eller ikke
                    farge = ButtonDefaults.buttonColors(
                        containerColor = if (valgtAntall == n)
                            MaterialTheme.colorScheme.tertiary // Valgt knapp er turkis
                        else
                            MaterialTheme.colorScheme.secondary, // Ikke valgt er blå
                        contentColor = if (valgtAntall == n)
                            MaterialTheme.colorScheme.onTertiary // Tekstfarge
                        else
                            MaterialTheme.colorScheme.onSecondary // tekstfarge
                    )
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
