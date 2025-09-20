package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TallRad(
    tall: List<Int>,
    vedTallKlikk: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tall.forEach { nummer ->
            NummerKnappen(nummer = nummer, vedKlikk = vedTallKlikk)
        }
    }
}