package com.toreva.mobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val TorevaColorScheme = darkColorScheme(
    primary = TorevaTeal,
    background = TorevaBackground,
    surface = TorevaSurface,
)

@Composable
fun TorevaTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = TorevaColorScheme, typography = AppTypography, content = content)
}
