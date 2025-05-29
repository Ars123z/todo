package com.example.basic_todo_app.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object ColorLightTokens {
    // Primary (Vibrant Green)
    val Primary = Color(0xFF00873D)
    val OnPrimary = Color.White // High contrast for readability
    val PrimaryContainer = Color(0xFF86F7A2) // Lighter tone of Primary
    val OnPrimaryContainer = Color(0xFF00210B) // Darker for readability on light container

    // Secondary (Olive Green)
    val Secondary = Color(0xFF5B6300)
    val OnSecondary = Color.White
    val SecondaryContainer = Color(0xFFE0EABE) // Lighter tone of Secondary
    val OnSecondaryContainer = Color(0xFF1A1E00)

    // Tertiary (Earthy Orange)
    val Tertiary = Color(0xFF8B5000)
    val OnTertiary = Color.White
    val TertiaryContainer = Color(0xFFFFDDB8) // Lighter tone of Tertiary
    val OnTertiaryContainer = Color(0xFF2D1600)

    // Error
    val Error = Color(0xFFBA1A1A)
    val OnError = Color.White
    val ErrorContainer = Color(0xFFFFDAD6)
    val OnErrorContainer = Color(0xFF410002)
    val Background = Color(0xFFFDFCF5) // Very light, slightly warm off-white
    val OnBackground = Color(0xFF1A1C18) // Dark gray for text
    val Surface = Color(0xFFFDFCF5)
    val OnSurface = Color(0xFF1A1C18)
    val SurfaceVariant = Color(0xFFDEE5D9) // Light gray
    val OnSurfaceVariant = Color(0xFF424940) // Darker gray for text on SurfaceVariant
    val Outline = Color(0xFF72796F)        // Medium gray for outlines
    val OutlineVariant = Color(0xFFC2C9BD)  // Lighter gray for less prominent outlines
    val Scrim = Color.Black
    val InverseSurface = Color(0xFF2F312C) // Dark for inverse
    val InverseOnSurface = Color(0xFFF0F1EB) // Light for onInverse
    val InversePrimary = Color(0xFF67DA87) // Dark theme's primary for inverse on light

    // Additional Surface Tones (Newer Material 3 additions)
    val SurfaceBright = Color(0xFFFDFCF5)
    val SurfaceDim = Color(0xFFDADAD3)
    val SurfaceContainerLowest = Color.White
    val SurfaceContainerLow = Color(0xFFF7F6EF)
    val SurfaceContainer = Color(0xFFF1F0E9)
    val SurfaceContainerHigh = Color(0xFFEBEAE4)
    val SurfaceContainerHighest = Color(0xFFE5E4DE)
}

object ColorDarkTokens {
    // Primary (Vibrant Green - Dark Theme Version)
    val Primary = Color(0xFF67DA87)
    val OnPrimary = Color(0xFF003918) // Dark green for contrast on light green
    val PrimaryContainer = Color(0xFF005226) // Slightly darker than Primary
    val OnPrimaryContainer = Color(0xFF86F7A2) // Light for readability

    // Secondary (Olive Green - Dark Theme Version)
    val Secondary = Color(0xFFC2CC4F)
    val OnSecondary = Color(0xFF2E3300)
    val SecondaryContainer = Color(0xFF444A00)
    val OnSecondaryContainer = Color(0xFFE0EABE)

    // Tertiary (Earthy Orange - Dark Theme Version)
    val Tertiary = Color(0xFFFFB86C)
    val OnTertiary = Color(0xFF4A2800)
    val TertiaryContainer = Color(0xFF6A3B00)
    val OnTertiaryContainer = Color(0xFFFFDDB8)

    // Error
    val Error = Color(0xFFFFB4AB)
    val OnError = Color(0xFF690005)
    val ErrorContainer = Color(0xFF93000A)
    val OnErrorContainer = Color(0xFFFFDAD6)
    // Neutrals & Surfaces
    val Background = Color(0xFF1A1C18) // Dark gray
    val OnBackground = Color(0xFFE2E3DD) // Light gray for text
    val Surface = Color(0xFF1A1C18)
    val OnSurface = Color(0xFFE2E3DD)
    val SurfaceVariant = Color(0xFF424940) // Medium-dark gray
    val OnSurfaceVariant = Color(0xFFC2C9BD) // Light gray for text on SurfaceVariant
    val Outline = Color(0xFF8B9388)        // Medium-light gray for outlines
    val OutlineVariant = Color(0xFF424940)  // Darker gray for less prominent outlines
    val Scrim = Color.Black
    val InverseSurface = Color(0xFFE2E3DD) // Light for inverse
    val InverseOnSurface = Color(0xFF1A1C18) // Dark for onInverse
    val InversePrimary = Color(0xFF00873D) // Light theme's primary for inverse on dark

    // Additional Surface Tones (Newer Material 3 additions) - these are typically shades of gray in dark theme
    val SurfaceBright = Color(0xFF3F413B)
    val SurfaceDim = Color(0xFF1A1C18) // Often same as background or a bit darker
    val SurfaceContainerLowest = Color(0xFF11140F)
    val SurfaceContainerLow = Color(0xFF1A1C18) // Often same as background
    val SurfaceContainer = Color(0xFF1E201C)
    val SurfaceContainerHigh = Color(0xFF282B26)
    val SurfaceContainerHighest = Color(0xFF333630)
}


