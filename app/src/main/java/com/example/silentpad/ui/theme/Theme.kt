package com.example.silentpad.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = SilentPadColors.primary,
    secondary = SilentPadColors.secondary,
    background = SilentPadColors.background,
    surface = SilentPadColors.surface,
    onPrimary = SilentPadColors.onPrimary,
    onSecondary = SilentPadColors.onSecondary,
    onBackground = SilentPadColors.onBackground,
    onSurface = SilentPadColors.onSurface,
)

private val LightColorScheme = lightColorScheme(
    primary = SilentPadColors.primary,
    secondary = SilentPadColors.secondary,
    background = White,
    surface = White,
    onPrimary = SilentPadColors.onPrimary,
    onSecondary = SilentPadColors.onSecondary,
    onBackground = Black,
    onSurface = Black,
)

@Composable
fun SilentPadTheme(
    darkTheme: Boolean = true, // Default dark theme untuk SilentPad
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


