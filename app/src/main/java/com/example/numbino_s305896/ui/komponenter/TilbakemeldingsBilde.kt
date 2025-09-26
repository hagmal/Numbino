package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.numbino_s305896.R

@Composable
fun TilbakemeldingsBilde(tilstand: Int, modifier: Modifier = Modifier) {
    // Sjekker hvilken tilstand spillet er i, og viser riktig figur
    when(tilstand) {
        1 -> Image(
            painter = painterResource(id = R.drawable.ic_riktig_svar),
            contentDescription = stringResource(R.string.cd_riktig_svar),
            modifier = modifier.size(240.dp)
        )
        2 -> Image(
            painter = painterResource(id = R.drawable.ic_feil_svar),
            contentDescription = stringResource(R.string.cd_feil_svar),
            modifier = modifier.size(240.dp)
        )
        else -> Image(
            painter = painterResource(id = R.drawable.ic_venter),
            contentDescription = stringResource(R.string.cd_venter),
            modifier = modifier.size(240.dp)
        )
    }
}