package com.toreva.mobile.ui.strategies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmIntentScreen(onSubmitted: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("USDC Yield")
        Text("Risk mode: Safe")
        Text("Modules: USDC")
        Text("Allocation: USDC 100%")
        Button(onClick = onSubmitted) { Text("Activate") }
    }
}
