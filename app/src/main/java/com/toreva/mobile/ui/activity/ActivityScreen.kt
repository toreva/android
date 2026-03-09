package com.toreva.mobile.ui.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toreva.mobile.ui.components.ReceiptCard

@Composable
fun ActivityScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Recent Activity")
        ReceiptCard("USDC Yield · Deploy", "→ Kamino (8.2% APY)", "5xK3...9mP2")
    }
}
