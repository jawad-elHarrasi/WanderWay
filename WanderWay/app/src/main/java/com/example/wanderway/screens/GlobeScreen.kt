package com.example.wanderway.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wanderway.navigation.BottomNavigationBar
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.rememberMapState
import com.mapbox.maps.extension.compose.style.MapStyle
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun GlobeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    // Utiliser un CoroutineScope pour gérer les événements asynchrones
    val coroutineScope = rememberCoroutineScope()

    // Initialiser l'état de la carte
    val mapState = rememberMapState {
        coroutineScope.launch {
            styleLoadedEvents.first().let {
                // Le style est chargé, vous pouvez maintenant ajouter des données ou ajuster la carte
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        MapboxMap(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(bottom = 56.dp),
            mapState = mapState
        ) {
            val mapStyleUrl = if (isSystemInDarkTheme()) {
                "mapbox://styles/jawad55/cm2db4xb3000t01pebv5cavzm" // Style sombre
            } else {
                Style.LIGHT // Style clair
            }
            MapStyle(style = mapStyleUrl)
        }
    }
}
