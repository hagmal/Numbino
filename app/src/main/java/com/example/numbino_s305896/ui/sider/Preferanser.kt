package com.example.numbino_s305896.ui.sider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun PreferanserSkjerm() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.preferanser),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding (bottom = 16.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun PreferanserSkjermPreview() {
    Numbino_s305896Theme {
        PreferanserSkjerm()
    }
}