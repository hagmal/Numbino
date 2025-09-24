package com.example.numbino_s305896.ui.navigasjon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.numbino_s305896.ui.sider.OmSpillet
import com.example.numbino_s305896.ui.sider.PreferanserSkjerm
import com.example.numbino_s305896.ui.sider.SpillSkjermen
import com.example.numbino_s305896.ui.sider.StartSkjermen

@Composable
fun AppNavHost (navController: NavHostController){
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
            OmSpillet(vedTilbake = {navController.popBackStack()})
        }
        composable ("prefs") {
            PreferanserSkjerm(
                vedTilbake = {navController.popBackStack()}
            )
        }
    }

}