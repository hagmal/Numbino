package com.example.numbino_s305896.ui.sider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SpillSkjermen (
    spørsmål: String, // Regnestykket som skal vises
    brukerSvar: String, // Svaret som brukeren trykker inn
    tilbakemelding: Int, // Tilstand for visuell tilbakemelding (1=riktig, 2=feil, 3=venter)
    vedTallKlikk: (Int) -> Unit, // Funksjon som kalles når tallknappen trykkes
    vedAvbrytKlikk: () -> Unit // Funksjon for avbryt-knapp
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Viser tilbakemelding til brukeren basert på tilstand
        when(tilbakemelding) {
            1 -> Image(
                painter = painterResource(id = R.drawable.ic_riktig_svar),
                contentDescription = "Riktig svar"
            )
            2 -> Image(
                painter = painterResource(id = R.drawable.ic_galt_svar),
                contentDescription = "Feil svar"
            )
            3 -> Image(
                painter = painterResource(id = R.drawable.ic_venter),
                contentDescription = "Venter på svar"
            )
        }

        // Viser regnestykket
        Text(
            text = spørsmål,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Viser svaret som brukeren taster inn.
        Text(
            text = brukerSvar,
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
            horizontalArrangement = Arrangement.Center
        ) {
            NummerKnappen(nummer = 0, vedKlikk = vedTallKlikk)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Avbryt knapp


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
