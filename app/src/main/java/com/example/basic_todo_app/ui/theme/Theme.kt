package com.example.basic_todo_app.ui.theme

import android.os.Build
import androidx.compose.animation.core.copy
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.DEFAULT_ARGS_KEY


private val DarkColorScheme = darkColorScheme(
    primary = ColorDarkTokens.Primary,
    onPrimary = ColorDarkTokens.OnPrimary,
    primaryContainer = ColorDarkTokens.PrimaryContainer,
    onPrimaryContainer = ColorDarkTokens.OnPrimaryContainer,
    inversePrimary = ColorDarkTokens.InversePrimary,
    secondary = ColorDarkTokens.Secondary,
    onSecondary = ColorDarkTokens.OnSecondary,
    secondaryContainer = ColorDarkTokens.SecondaryContainer,
    onSecondaryContainer = ColorDarkTokens.OnSecondaryContainer,
    tertiary = ColorDarkTokens.Tertiary,
    onTertiary = ColorDarkTokens.OnTertiary,
    tertiaryContainer = ColorDarkTokens.TertiaryContainer,
    onTertiaryContainer = ColorDarkTokens.OnTertiaryContainer,
    background = ColorDarkTokens.Background,
    onBackground = ColorDarkTokens.OnBackground,
    surface = ColorDarkTokens.Surface,
    onSurface = ColorDarkTokens.OnSurface,
    surfaceVariant = ColorDarkTokens.SurfaceVariant,
    onSurfaceVariant = ColorDarkTokens.OnSurfaceVariant,
    surfaceTint = ColorDarkTokens.Primary, // Often same as primary
    inverseSurface = ColorDarkTokens.InverseSurface,
    inverseOnSurface = ColorDarkTokens.InverseOnSurface,
    error = ColorDarkTokens.Error,
    onError = ColorDarkTokens.OnError,
    errorContainer = ColorDarkTokens.ErrorContainer,
    onErrorContainer = ColorDarkTokens.OnErrorContainer,
    outline = ColorDarkTokens.Outline,
    outlineVariant = ColorDarkTokens.OutlineVariant,
    scrim = ColorDarkTokens.Scrim,
    // Newer surface roles for Dark Theme
    surfaceBright = ColorDarkTokens.SurfaceBright,
    surfaceDim = ColorDarkTokens.SurfaceDim,
    surfaceContainerLowest = ColorDarkTokens.SurfaceContainerLowest,
    surfaceContainerLow = ColorDarkTokens.SurfaceContainerLow,
    surfaceContainer = ColorDarkTokens.SurfaceContainer,
    surfaceContainerHigh = ColorDarkTokens.SurfaceContainerHigh,
    surfaceContainerHighest = ColorDarkTokens.SurfaceContainerHighest
)

private val LightColorScheme = lightColorScheme(
    primary = ColorLightTokens.Primary,
    onPrimary = ColorLightTokens.OnPrimary,
    primaryContainer = ColorLightTokens.PrimaryContainer,
    onPrimaryContainer = ColorLightTokens.OnPrimaryContainer,
    inversePrimary = ColorLightTokens.InversePrimary,
    secondary = ColorLightTokens.Secondary,
    onSecondary = ColorLightTokens.OnSecondary,
    secondaryContainer = ColorLightTokens.SecondaryContainer,
    onSecondaryContainer = ColorLightTokens.OnSecondaryContainer,
    tertiary = ColorLightTokens.Tertiary,
    onTertiary = ColorLightTokens.OnTertiary,
    tertiaryContainer = ColorLightTokens.TertiaryContainer,
    onTertiaryContainer = ColorLightTokens.OnTertiaryContainer,
    background = ColorLightTokens.Background,
    onBackground = ColorLightTokens.OnBackground,
    surface = ColorLightTokens.Surface,
    onSurface = ColorLightTokens.OnSurface,
    surfaceVariant = ColorLightTokens.SurfaceVariant,
    onSurfaceVariant = ColorLightTokens.OnSurfaceVariant,
    surfaceTint = ColorLightTokens.Primary, // Often same as primary
    inverseSurface = ColorLightTokens.InverseSurface,
    inverseOnSurface = ColorLightTokens.InverseOnSurface,
    error = ColorLightTokens.Error,
    onError = ColorLightTokens.OnError,
    errorContainer = ColorLightTokens.ErrorContainer,
    outline = ColorLightTokens.Outline,
    outlineVariant = ColorLightTokens.OutlineVariant,
    scrim = ColorLightTokens.Scrim,
    // Newer surface roles for Light Theme
    surfaceBright = ColorLightTokens.SurfaceBright,
    surfaceDim = ColorLightTokens.SurfaceDim,
    surfaceContainerLowest = ColorLightTokens.SurfaceContainerLowest,
    surfaceContainerLow = ColorLightTokens.SurfaceContainerLow,
    surfaceContainer = ColorLightTokens.SurfaceContainer,
    surfaceContainerHigh = ColorLightTokens.SurfaceContainerHigh,
    surfaceContainerHighest = ColorLightTokens.SurfaceContainerHighest
)

@Composable
fun BasictodoappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}