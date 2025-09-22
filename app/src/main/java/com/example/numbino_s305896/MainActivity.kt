package com.example.numbino_s305896

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numbino_s305896.ui.sider.OmSpillet
import com.example.numbino_s305896.ui.sider.PreferanserSkjerm
import com.example.numbino_s305896.ui.sider.SpillSkjermen
import com.example.numbino_s305896.ui.sider.StartSkjermen
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Numbino_s305896Theme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "start"
                ) {
                    composable("start") {
                        StartSkjermen(
                            klikkStartSpill = { navController.navigate("spill") },
                            klikkOmSpill = { navController.navigate("om") },
                            klikkPreferanser = { navController.navigate("prefs") }
                        )
                    }
                    composable("spill") {
                        SpillSkjermen(
                            vedAvbryt = { navController.popBackStack() }
                        )
                    }
                    composable ("om") {
                        OmSpillet()
                    }
                    composable ("prefs") {
                        PreferanserSkjerm()
                    }
                }
            }
        }
    }
}