package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun NummerKnappen(nummer: Int, vedKlikk: (Int) -> Unit) {
    Button(
        onClick = { vedKlikk(nummer) },
        modifier = Modifier.size(64.dp), // Sette størrelsen på knappen
        // Skygge under knapp
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
    ){
        Text(
            text = nummer.toString(),
            fontSize = 24.sp
        )
    }
}

@Preview
@Composable
fun NummerKnappenPreview() {
    Numbino_s305896Theme {
        NummerKnappen(nummer = 5, vedKlikk = {})
    }
}