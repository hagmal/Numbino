package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.numbino_s305896.R

@Composable
fun ProgressIndikator(
    current: Int, // Hvor langt brukeren har kommet i spillet
    total: Int, // Totalt antall oppgaver
    modifier: Modifier = Modifier
) {
    // For skjermleser. Lager streng som beskriver hvor langt brukeren har kommet
    val cd = stringResource(R.string.progresstekst, current, total)

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = cd } // For skjemlesere
    ) {
        // En runding for hver oppgave
        for (i in 1..total) {
            Box( // Lager en sirkel for hver oppgave (starter som en boks)
                modifier = Modifier
                    .size(20.dp) // Størrelse på rundingene
                    .padding(4.dp) // Mellomrom mellom rundingene
                    .clip(CircleShape) // Gjør boksene runde
                    .background(
                        if (i <= current) { // Hvis man har nådd denne oppgaven
                            MaterialTheme.colorScheme.tertiary // Farg rundingen turkis
                        } else {
                            // Oppgavene som ikke er nådd enda
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                        }
                    )
            )
        }
    }
}