package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun NummerKnappen(nummer: Int, vedKlikk: (Int) -> Unit) {
    Button(
        onClick = { vedKlikk(nummer) },
        modifier = Modifier
            .size(72.dp)
            .semantics { contentDescription = nummer.toString() },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 6.dp
        )
    ){
        Text(
            text = nummer.toString(),
            fontSize = 36.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NummerKnappenPreview() {
    Numbino_s305896Theme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            NummerKnappen(nummer = 5, vedKlikk = {})
        }
    }
}