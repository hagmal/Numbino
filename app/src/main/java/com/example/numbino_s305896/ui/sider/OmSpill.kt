package com.example.numbino_s305896.ui.sider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.TilbakeTopBar
import com.example.numbino_s305896.ui.theme.Chewy

@Composable
fun OmSpillet(vedTilbake: () -> Unit) {
    Scaffold(topBar = { TilbakeTopBar(vedTilbake = vedTilbake) }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Bilde av figuren
            Image(
                painter = painterResource(id = R.drawable.ic_numbino),
                contentDescription = stringResource(R.string.cd_maskot),
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )
            // Overskrift
            Text(
                text = stringResource(id = R.string.om_spill_overskrift),
                fontFamily = Chewy,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Brødtekst
            Text(
                text = stringResource(id = R.string.om_spill_tekst),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            )
        }
    }
}