package com.toreva.mobile.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.toreva.mobile.ui.components.Day1Button
import com.toreva.mobile.ui.components.Day1ButtonSize
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type

@Composable
fun ConnectWalletScreen(onConnected: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Day1Colors.Background)
            .padding(horizontal = Day1Spacing.screenX),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "toreva",
            style = Day1Type.title1,
            color = Day1Colors.TextPrimary,
        )
        Spacer(Modifier.height(Day1Spacing.xs))
        Text(
            text = "An autopilot for your money",
            style = Day1Type.body,
            color = Day1Colors.TextSecondary,
        )
        Spacer(Modifier.height(Day1Spacing.xxl))
        Day1Button(
            text = "Connect Wallet",
            onClick = onConnected,
            size = Day1ButtonSize.LG,
        )
    }
}
