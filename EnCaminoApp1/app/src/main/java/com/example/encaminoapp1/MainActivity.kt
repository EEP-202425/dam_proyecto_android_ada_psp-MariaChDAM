package com.example.encaminoapp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.encaminoapp1.ui.screens.AddTrayectoScreen
import com.example.encaminoapp1.ui.screens.TrayectoListScreen
import com.example.encaminoapp1.ui.theme.EnCaminoApp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EnCaminoApp1Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "trayecto_list") {
                    composable("trayecto_list") { TrayectoListScreen(navController) }
                    composable("add_trayecto") { AddTrayectoScreen(navController) }
                }
            }
        }
    }
}
