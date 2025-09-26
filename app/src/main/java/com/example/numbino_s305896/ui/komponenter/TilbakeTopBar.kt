package com.example.numbino_s305896.ui.komponenter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.numbino_s305896.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TilbakeTopBar(vedTilbake: () -> Unit) { // Funksjon som kalles når "Tilbake" trykkes
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = vedTilbake) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.tilbake)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}
