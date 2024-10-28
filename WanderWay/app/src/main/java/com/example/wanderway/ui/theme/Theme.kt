package com.example.wanderway.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Couleurs pour le mode clair
private val LightColors = lightColorScheme(
    primary = Color(0xFFFFC107), // Jaune pour les boutons
    onPrimary = Color.Black, // Texte des boutons en noir
    background = Color.White, // Fond blanc
    onBackground = Color.Black // Texte en noir
)

// Couleurs pour le mode sombre
private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFC107), // Jaune pour les boutons
    onPrimary = Color.Black,      // Texte sur les boutons jaunes
    background = Color.Black,     // Fond noir
    onBackground = Color.White  // Texte en blanc
)

@Composable
fun WanderWayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Active le thème sombre automatiquement selon les réglages du système
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
