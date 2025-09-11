package com.example.numbino_s305896.ui.sider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.StartKnappen
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun StartSkjermen (
    klikkStartSpill: () -> Unit, // Funksjon som kalles når "Start spill" trykkes
    klikkOmSpill: () -> Unit, // Funksjon som kalles når "Om spill" trykkes
    klikkPreferanser: () -> Unit // Funksjon som kalles når "Preferanser" trykkes
) {
    // Stabler komponentene vertikalt
    Column (
        modifier = Modifier
            .fillMaxSize() // Fyller hele skjermens bredde og høyde
            .padding(16.dp), // Padding rundt kolonnen
        horizontalAlignment = Alignment.CenterHorizontally // Sentrerer innholdet horisontalt
    ) {
        // Overskrift
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        // Avstand mellom overskrift og knapper
        Spacer(modifier = Modifier.height(400.dp))

        // Bruker startknapp-komponenten
        StartKnappen(
            tekst = stringResource(id = R.string.start_spill),
            onClick = klikkStartSpill
        )

        Spacer(modifier = Modifier.height(16.dp))

        StartKnappen(
            tekst = stringResource(id = R.string.om_spill),
            onClick = klikkOmSpill
        )

        Spacer(modifier = Modifier.height(16.dp))

        StartKnappen(
            tekst = stringResource(id = R.string.preferanser),
            onClick = klikkPreferanser
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartSkjermPreview() {
    Numbino_s305896Theme {
        StartSkjermen(
            klikkStartSpill = {},
            klikkOmSpill = {},
            klikkPreferanser = {}
        )
    }
}