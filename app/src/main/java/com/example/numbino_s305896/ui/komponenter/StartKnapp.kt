package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun StartKnappen(
    tekst: String, // Teksten som skal vises på knappen
    onClick: () -> Unit, // Funksjonen som kalles på når knappen trykkes
    farge: ButtonColors = ButtonDefaults.buttonColors() // Farger for knappen
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(0.8f),
        shape = RoundedCornerShape(50.dp), // Gir knappen avrundede hjørner
        colors = farge
    ) {
        // Teksten som vises inne i knappen
        Text(
            text = tekst,
            fontSize = 24.sp, // Skriftstørrelse
            modifier = Modifier.padding(12.dp)) // Padding rundt teksten
    }
}

@Preview(showBackground = true)
@Composable
fun StartKnappPreview() {
    Numbino_s305896Theme {
        StartKnappen(
            tekst = "Start spill",
            onClick = {}
        )
    }
}
