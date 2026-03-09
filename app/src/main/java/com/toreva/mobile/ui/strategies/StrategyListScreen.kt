package com.toreva.mobile.ui.strategies

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
import com.toreva.mobile.ui.components.Day1Button
import com.toreva.mobile.ui.components.Day1ButtonSize
import com.toreva.mobile.ui.components.StrategyCard
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type

@Composable
fun StrategyListScreen(onActivate: () -> Unit, onActivity: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Day1Colors.Background)
            .padding(horizontal = Day1Spacing.screenX)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier.height(Day1Spacing.xxl))
        Text(
            text = "Select Your Strategy",
            style = Day1Type.title2,
            color = Day1Colors.TextPrimary,
        )
        Spacer(Modifier.height(Day1Spacing.xxs))
        Text(
            text = "Swipe to browse strategies. Tap to select.",
            style = Day1Type.body,
            color = Day1Colors.TextSecondary,
        )
        Spacer(Modifier.height(Day1Spacing.lg))
        Column(verticalArrangement = Arrangement.spacedBy(Day1Spacing.sm)) {
            StrategyCard(
                name = "Earn",
                description = "Deploy to highest-yield USDC lending venue",
                live = true,
                onClick = onActivate,
            )
            StrategyCard(
                name = "SOL Staking",
                description = "Switch between best-yield liquid staking tokens",
                live = false,
            )
            StrategyCard(
                name = "Meme Rebalance",
                description = "Equal-weight weekly rebalance across memes",
                live = false,
            )
        }
        Spacer(Modifier.height(Day1Spacing.xl))
        Day1Button(
            text = "View Activity",
            onClick = onActivity,
            size = Day1ButtonSize.MD,
        )
        Spacer(Modifier.height(Day1Spacing.screenBottom))
    }
}
