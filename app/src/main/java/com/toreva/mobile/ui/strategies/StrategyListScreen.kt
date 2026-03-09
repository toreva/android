package com.toreva.mobile.ui.strategies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toreva.mobile.ui.components.StrategyCard

@Composable
fun StrategyListScreen(onActivate: () -> Unit, onActivity: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        StrategyCard("USDC Yield", "Deploy to highest-yield USDC lending venue", live = true, onClick = onActivate)
        StrategyCard("SOL Staking", "Switch between best-yield liquid staking tokens", live = false)
        StrategyCard("Meme Rebalance", "Equal-weight weekly rebalance across memes", live = false)
        Button(onClick = onActivity) { androidx.compose.material3.Text("View Activity") }
    }
}
