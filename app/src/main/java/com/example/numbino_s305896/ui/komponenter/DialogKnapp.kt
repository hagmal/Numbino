package com.example.numbino_s305896.ui.komponenter

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogKnapp (
    tekst: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(text = tekst)
    }
}