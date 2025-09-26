package com.example.numbino_s305896.ui.komponenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TallRad(
    tall: List<Int>,
    vedTallKlikk: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterHorizontally)
    ) {
        tall.forEach { nummer ->
            NummerKnappen(nummer, vedTallKlikk)
        }
    }
}