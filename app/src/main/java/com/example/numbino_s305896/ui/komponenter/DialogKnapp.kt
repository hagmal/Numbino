package com.example.numbino_s305896.ui.komponenter

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DialogKnapp (
    tekst: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = tekst)
    }
}