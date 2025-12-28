package com.example.silentpad.ui.theme

import androidx.compose.ui.graphics.Color

// SilentPad Global Color Palette
val LightBlue = Color(0xFFA9CFFF)        // Outline color untuk buttons, secondary text
val DarkerBlue = Color(0xFF3F6694)       // Button background color
val White = Color(0xFFFFFFFF)            // Text color, icons
val Black = Color(0xFF000514)            // Background, input fields
val PureBlack = Color(0xFF000000)        // Pure black background

// Social Media Colors
val GoogleWhite = Color(0xFFFFFFFF)      // Google button background
val FacebookBlue = Color(0xFF1877F2)     // Facebook button background

// Text Colors
val PrimaryText = White                   // Main text color
val SecondaryText = LightBlue            // Secondary/hint text color
val PlaceholderText = White.copy(alpha = 0.6f)  // Placeholder text

// Button Colors
val ButtonBackground = DarkerBlue         // Primary button background
val ButtonBorder = LightBlue             // Button border/outline
val InputFieldBackground = Black         // Input field background

object SilentPadColors {
    val background = PureBlack
    val surface = Black
    val primary = DarkerBlue
    val primaryVariant = LightBlue
    val secondary = LightBlue
    val onPrimary = White
    val onSecondary = Black
    val onBackground = White
    val onSurface = White
    val textPrimary = PrimaryText
    val textSecondary = SecondaryText
    val textPlaceholder = PlaceholderText
    val buttonPrimary = ButtonBackground
    val buttonBorder = ButtonBorder
    val inputBackground = InputFieldBackground
}

