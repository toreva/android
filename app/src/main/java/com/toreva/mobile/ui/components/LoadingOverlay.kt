package com.toreva.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type

@Composable
fun LoadingOverlay(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Day1Colors.Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(color = Day1Colors.SecondaryOrange)
        Spacer(Modifier.height(Day1Spacing.md))
        Text(text = message, style = Day1Type.body, color = Day1Colors.TextSecondary)
    }
}
