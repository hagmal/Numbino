package com.example.numbino_s305896.ui.komponenter

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.numbino_s305896.R

@Composable
fun AvbrytDialog(
    vedLukk: () -> Unit,
    vedBekreft: () -> Unit
) {
    AlertDialog(
        onDismissRequest = vedLukk,
        text = {
            Text(text = stringResource(R.string.dialog_tekst))
        },
        confirmButton = {
            Button(onClick = vedBekreft) {
                Text(stringResource(id = R.string.dialog_ja))
            }
        },
        dismissButton = {
            Button(onClick = vedLukk) {
                Text(stringResource(id = R.string.dialog_nei))
            }
        }
    )
}
