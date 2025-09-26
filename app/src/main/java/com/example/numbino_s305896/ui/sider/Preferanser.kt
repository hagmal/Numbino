package com.example.numbino_s305896.ui.sider

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.StartKnappen
import com.example.numbino_s305896.ui.komponenter.TilbakeTopBar
import com.example.numbino_s305896.ui.theme.Chewy
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun PreferanserSkjerm(vedTilbake: () -> Unit) {
    val context = LocalContext.current
    val sharedPrefs = remember {
        context.getSharedPreferences("numbino_prefs", Context.MODE_PRIVATE)
    }
    var valgtAntall by remember {
        mutableIntStateOf(sharedPrefs.getInt("antall_oppgaver", 5))
    }

    Scaffold (
        topBar = { TilbakeTopBar(vedTilbake = vedTilbake) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_numbino), // <- bytt til din figur
                contentDescription = stringResource(R.string.cd_maskot),
                modifier = Modifier.size(120.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Tittel
            Text(
                text = stringResource(R.string.preferanser),
                fontFamily = Chewy,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.preferanser_forklaring),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Bruk de samme store knappene som på startskjermen
            listOf(5, 10, 15).forEach { n ->
                StartKnappen(
                    tekst = n.toString(),
                    onClick = {
                        valgtAntall = n
                        sharedPrefs.edit().putInt("antall_oppgaver", n).apply()
                    },
                    farge = ButtonDefaults.buttonColors(
                        containerColor = if (valgtAntall == n)
                            MaterialTheme.colorScheme.tertiary
                        else
                            MaterialTheme.colorScheme.secondary,
                        contentColor = if (valgtAntall == n)
                            MaterialTheme.colorScheme.onTertiary
                        else
                            MaterialTheme.colorScheme.onSecondary
                    )
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreferanserSkjermPreview() {
    Numbino_s305896Theme {
        PreferanserSkjerm(
            vedTilbake = { }
        )
    }
}