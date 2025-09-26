package com.example.numbino_s305896

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.numbino_s305896.ui.navigasjon.AppNavHost
import com.example.numbino_s305896.ui.theme.Numbino_s305896Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Numbino_s305896Theme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}