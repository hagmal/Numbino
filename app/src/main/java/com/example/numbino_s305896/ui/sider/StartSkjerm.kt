package com.example.numbino_s305896.ui.sider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.StartKnappen
import com.example.numbino_s305896.ui.theme.Chewy
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
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp), // Padding rundt kolonnen
        horizontalAlignment = Alignment.CenterHorizontally // Sentrerer innholdet horisontalt
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_numbino),
            contentDescription = "Numbino-figur",
            modifier = Modifier
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.velkommen_til),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        // Overskrift
        Text(
            text = stringResource(id = R.string.app_name),
            fontFamily = Chewy,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(200.dp))

        // Bruker startknapp-komponenten
        StartKnappen(
            tekst = stringResource(id = R.string.start_spill),
            onClick = klikkStartSpill,
            farge = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        StartKnappen(
            tekst = stringResource(id = R.string.om_spill),
            onClick = klikkOmSpill,
            farge = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        StartKnappen(
            tekst = stringResource(id = R.string.preferanser),
            onClick = klikkPreferanser,
            farge = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
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