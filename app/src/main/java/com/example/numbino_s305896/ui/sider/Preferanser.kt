package com.example.numbino_s305896.ui.sider

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun PreferanserSkjerm() {
    val context = LocalContext.current
    val sharedPrefs = remember {
        context.getSharedPreferences("numbino_prefs", Context.MODE_PRIVATE)
    }
    var valgtAntall by remember {
        mutableIntStateOf(sharedPrefs.getInt("antall_oppgaver", 5))
    }

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
        listOf(5, 10, 15).forEach { n ->
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        valgtAntall = n
                        sharedPrefs.edit().putInt("antall_oppgaver", n).apply()
                        Log.d("Preferanser", context.getString(R.string.lagret_antall_log, n))
            }
                    .padding(vertical = 8.dp)
            ){
                RadioButton(
                    selected = (valgtAntall == n),
                    onClick = {
                        valgtAntall = n
                        sharedPrefs.edit().putInt("antall_oppgaver", n).apply()
                        Log.d("Preferanser", context.getString(R.string.lagret_antall_log, n))
                    }
                )

                val label = when (n) {
                    5 -> stringResource(R.string.valg_5)
                    10 -> stringResource(R.string.valg_10)
                    else -> stringResource(R.string.valg_15)
                }
                Text(
                    text = label,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreferanserSkjermPreview() {
    Numbino_s305896Theme {
        PreferanserSkjerm()
    }
}