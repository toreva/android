package com.toreva.mobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val TorevaColorScheme = darkColorScheme(
    primary = Day1Colors.SecondaryOrange,
    onPrimary = Day1Colors.TextPrimary,
    secondary = Day1Colors.PrimaryTeal,
    onSecondary = Day1Colors.TextPrimary,
    background = Day1Colors.Background,
    onBackground = Day1Colors.TextPrimary,
    surface = Day1Colors.BackgroundCard,
    onSurface = Day1Colors.TextPrimary,
    surfaceVariant = Day1Colors.BackgroundElevated,
    onSurfaceVariant = Day1Colors.TextSecondary,
    outline = Day1Colors.Border,
    error = Day1Colors.Error,
    onError = Day1Colors.TextPrimary,
)

@Composable
fun TorevaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = TorevaColorScheme,
        typography = AppTypography,
        content = content,
    )
}
