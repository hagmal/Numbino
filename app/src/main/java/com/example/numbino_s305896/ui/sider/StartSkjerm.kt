package com.example.numbino_s305896.ui.sider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.StartKnappen
import com.example.numbino_s305896.ui.theme.Chewy

@Composable
fun StartSkjermen (
    klikkStartSpill: () -> Unit, // Funksjon som kalles når "Start spill" trykkes
    klikkOmSpill: () -> Unit, // Funksjon som kalles når "Om spill" trykkes
    klikkPreferanser: () -> Unit // Funksjon som kalles når "Preferanser" trykkes
) {
    Scaffold ()
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(90.dp))
            // Bilde av figuren
            Image(
                painter = painterResource(id = R.drawable.ic_numbino),
                contentDescription = stringResource(R.string.cd_maskot),
                modifier = Modifier.height(200.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Overskrift
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = Chewy,
                fontSize = 60.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(200.dp))

            // Start spill-knapp
            StartKnappen(
                tekst = stringResource(id = R.string.start_spill),
                onClick = klikkStartSpill,
                farge = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            //Preferanser-knapp
            StartKnappen(
                tekst = stringResource(id = R.string.preferanser),
                onClick = klikkPreferanser,
                farge = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            //Om spill-knapp
            StartKnappen(
                tekst = stringResource(id = R.string.om_spill),
                onClick = klikkOmSpill,
                farge = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        }
    }
}
