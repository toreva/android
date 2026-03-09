package com.toreva.mobile.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.toreva.mobile.ui.components.ReceiptCard
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type

@Composable
fun ActivityScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Day1Colors.Background)
            .padding(horizontal = Day1Spacing.screenX)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier.height(Day1Spacing.xxl))
        Text(
            text = "Recent Activity",
            style = Day1Type.title2,
            color = Day1Colors.TextPrimary,
        )
        Spacer(Modifier.height(Day1Spacing.md))
        Column(verticalArrangement = Arrangement.spacedBy(Day1Spacing.sm)) {
            ReceiptCard(
                title = "USDC Yield · Deploy",
                subtitle = "→ Kamino (8.2% APY)",
                signature = "5xK3...9mP2",
            )
        }
        Spacer(Modifier.height(Day1Spacing.screenBottom))
    }
}
