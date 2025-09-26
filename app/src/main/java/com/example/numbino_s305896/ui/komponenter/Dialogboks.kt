package com.example.numbino_s305896.ui.komponenter

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogBoks(
    tittel: String,
    tekst: String,
    bekreftTekst: String,
    avbrytTekst: String,
    vedBekreft: () -> Unit,
    vedAvbryt: () -> Unit,
    kanLukkesUtenValg: Boolean = false
) {
    AlertDialog(
        onDismissRequest = { if (kanLukkesUtenValg) vedAvbryt() },
        title = { Text(tittel, color = MaterialTheme.colorScheme.onSurface) },
        text = { Text(tekst, color = MaterialTheme.colorScheme.onSurface) },
        confirmButton = {
            Button(onClick = vedBekreft) { Text(bekreftTekst) }
        },
        dismissButton = {
            TextButton(onClick = vedAvbryt) { Text(avbrytTekst) }
        }
    )
}
