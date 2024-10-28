package com.example.wanderway.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wanderway.screens.GlobeScreen
import com.example.wanderway.screens.LoginScreen
import com.example.wanderway.screens.MainScreen
import com.example.wanderway.ui.theme.WanderWayTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            WanderWayTheme {
                LoginScreen(
                    onLoginSuccess = {
                        Log.d("WanderWayDebug", "Connexion réussie, navigation vers main")
                        navController.navigate("main") // Naviguer vers main après login
                    }
                )
            }
        }
        composable("main") {
            WanderWayTheme { MainScreen(navController = navController) }
        }
        composable("globe") {
            WanderWayTheme { GlobeScreen(navController = navController) }
        }
    }


}

