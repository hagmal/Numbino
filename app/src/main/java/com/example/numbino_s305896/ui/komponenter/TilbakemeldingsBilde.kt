package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.numbino_s305896.R

@Composable
fun TilbakemeldingsBilde(tilstand: Int) {
    when(tilstand) {
        1 -> Image(
            painter = painterResource(id = R.drawable.ic_riktig_svar),
            contentDescription = "Riktig svar",
            modifier = Modifier.size(300.dp)
        )
        2 -> Image(
            painter = painterResource(id = R.drawable.ic_feil_svar),
            contentDescription = "Feil svar",
            modifier = Modifier.size(300.dp)
        )
        3 -> Image(
            painter = painterResource(id = R.drawable.ic_venter),
            contentDescription = "Venter på svar",
            modifier = Modifier.size(300.dp)
        )
    }
}