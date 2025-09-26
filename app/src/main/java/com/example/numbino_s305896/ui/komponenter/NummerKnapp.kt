package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NummerKnappen(
    nummer: Int,
    vedKlikk: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        // vedKlikk-funksjonen kalles når knappen trykkes, med nummeret som argument
        onClick = { vedKlikk(nummer) },

        modifier = modifier
            .size(72.dp) // Firkantet med fast str
            .semantics { contentDescription = nummer.toString() }, // For skjermleser
        shape = RoundedCornerShape(12.dp), // Runder av hjørner på knappene
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary, // Bakgrunnsfarge på knappene
            contentColor = MaterialTheme.colorScheme.onSecondary // Tekstfarge på knappene
        ),
        // Skygge for visuell respons når man trykker
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp, // skygge til vanlig
            pressedElevation = 6.dp // skygge når man trykker
        )
    ){
        // Tallet som vises på knappen
        Text(
            text = nummer.toString(),
            fontSize = 36.sp
        )
    }
}