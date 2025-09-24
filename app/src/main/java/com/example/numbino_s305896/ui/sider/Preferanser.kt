package com.example.numbino_s305896.ui.sider

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numbino_s305896.R
import com.example.numbino_s305896.ui.komponenter.TilbakeTopBar
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

@Composable
fun PreferanserSkjerm(vedTilbake: () -> Unit) {
    val context = LocalContext.current
    val sharedPrefs = remember {
        context.getSharedPreferences("numbino_prefs", Context.MODE_PRIVATE)
    }
    var valgtAntall by remember {
        mutableIntStateOf(sharedPrefs.getInt("antall_oppgaver", 5))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Topbar
        TilbakeTopBar(vedTilbake = vedTilbake)

        Spacer(Modifier.height(12.dp))

        // Liten figur for helhet (valgfritt)
        Image(
            painter = painterResource(id = R.drawable.ic_numbino), // <- bytt til din figur
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )

        Spacer(Modifier.height(8.dp))

        // Tittel
        Text(
            text = stringResource(R.string.preferanser),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(20.dp))

        // Seksjonskort med valg
        Surface(
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.preferanser_forklaring),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf(5, 10, 15).forEach { n ->
                        val selected = (valgtAntall == n)
                        Button(
                            onClick = {
                                valgtAntall = n
                                sharedPrefs.edit().putInt("antall_oppgaver", n).apply()
                                Log.d("Preferanser", context.getString(R.string.lagret_antall_log, n))
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = if (selected)
                                    MaterialTheme.colorScheme.onPrimary
                                else
                                    MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = n.toString(), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreferanserSkjermPreview() {
    Numbino_s305896Theme {
        PreferanserSkjerm(
            vedTilbake = { }
        )
    }
}