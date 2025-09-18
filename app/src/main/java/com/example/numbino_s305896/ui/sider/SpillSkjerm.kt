package com.example.numbino_s305896.ui.sider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.NummerKnappen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource

@Composable
fun SpillSkjermen (
    spørsmål: String, // Regnestykket som skal vises
    brukerSvar: String, // Svaret som brukeren trykker inn
    tilbakemelding: Int, // Tilstand for visuell tilbakemelding (1=riktig, 2=feil, 3=venter)
    vedTallKlikk: (Int) -> Unit, // Funksjon som kalles når tallknappen trykkes
    vedAvbrytKlikk: () -> Unit // Funksjon for avbryt-knapp
) {
    var visDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Avbryt-knapp som avslutter spillet.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(onClick = { visDialog = true }) {
                Icon(Icons.Default.Close,
                    contentDescription = "Avslutt spillet")
            }
        }
        // Resterende innhold under avbryt-knappen.
        // Viser tilbakemelding til brukeren basert på tilstand
        when(tilbakemelding) {
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

        Spacer(modifier = Modifier.padding(8.dp))

        // Viser regnestykket
        Text(
            text = spørsmål,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NummerKnappen(nummer = 1, vedKlikk = vedTallKlikk)
            NummerKnappen(nummer = 2, vedKlikk = vedTallKlikk)
            NummerKnappen(nummer = 3, vedKlikk = vedTallKlikk)
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NummerKnappen(nummer = 4, vedKlikk = vedTallKlikk)
            NummerKnappen(nummer = 5, vedKlikk = vedTallKlikk)
            NummerKnappen(nummer = 6, vedKlikk = vedTallKlikk)
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NummerKnappen(nummer = 7, vedKlikk = vedTallKlikk)
            NummerKnappen(nummer = 8, vedKlikk = vedTallKlikk)
            NummerKnappen(nummer = 9, vedKlikk = vedTallKlikk)
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            NummerKnappen(nummer = 0, vedKlikk = vedTallKlikk)
        }
    }

    if (visDialog) {
        AlertDialog(
            onDismissRequest = { visDialog = false },
            text = {
                Text(text = stringResource(R.string.dialog_tekst))
            },
            confirmButton = {
                Button(
                    onClick = { vedAvbrytKlikk() }
                ) {
                    Text(stringResource(id = R.string.dialog_ja))
                }
            },
            dismissButton = {
                Button(
                    onClick = { visDialog = false }
                ) {
                    Text(stringResource(id = R.string.dialog_nei))
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SpillSkjermPreview() {
    Numbino_s305896Theme {
        SpillSkjermen(
            spørsmål = "2 + 2",
            brukerSvar = "3",
            tilbakemelding = 1,
            vedTallKlikk = {},
            vedAvbrytKlikk =  {}
        )
    }
}
