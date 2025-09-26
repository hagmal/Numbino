package com.example.numbino_s305896.ui.komponenter

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogBoks(
    tittel: String, // Titteltekst i dialogboksen
    tekst: String, // Tekst idialogboks
    bekreftTekst: String, // Tekst på bekreft-knappen (ja)
    avbrytTekst: String, // Tekst på avbryt-knappen (nei)
    vedBekreft: () -> Unit, // Funksjon som kalles når "Bekreft" trykkes
    vedAvbryt: () -> Unit // Funksjon som kalles når "Avbryt" trykkes
) {
    AlertDialog(
        // Hvis brukeren trykker utenfor dialogen, vil dialogboksen lukkes
        onDismissRequest = { vedAvbryt() },

        title = { Text(tittel, color = MaterialTheme.colorScheme.onSurface) },

        text = { Text(tekst, color = MaterialTheme.colorScheme.onSurface) },
        // Bekreft-knappen
        confirmButton = {
            Button(onClick = vedBekreft) { Text(bekreftTekst) }
        },
        // Avbryt-knappen
        dismissButton = {
            TextButton(onClick = vedAvbryt) { Text(avbrytTekst) }
        }
    )
}
