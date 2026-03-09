package com.toreva.mobile.ui.strategies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.toreva.mobile.ui.components.Day1Button
import com.toreva.mobile.ui.components.Day1ButtonSize
import com.toreva.mobile.ui.components.Day1Card
import com.toreva.mobile.ui.components.Day1Sheet
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type

@Composable
fun ConfirmIntentScreen(onSubmitted: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Day1Colors.Background),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Day1Sheet {
            Text(
                text = "Activation policy",
                style = Day1Type.headline,
                color = Day1Colors.TextPrimary,
            )
            Spacer(Modifier.height(Day1Spacing.xxs))
            Text(
                text = "Review policy choices before wallet signature.",
                style = Day1Type.body,
                color = Day1Colors.TextSecondary,
            )
            Spacer(Modifier.height(Day1Spacing.md))
            Day1Card(modifier = Modifier.fillMaxWidth()) {
                PolicyRow("Policy mode", "Conservative defaults")
                PolicyRow("Expiry", "7 days")
                PolicyRow("Allowlist", "Strict")
            }
            Spacer(Modifier.height(Day1Spacing.lg))
            Day1Button(
                text = "Sign and activate",
                onClick = onSubmitted,
                size = Day1ButtonSize.LG,
            )
            Spacer(Modifier.height(Day1Spacing.xs))
            Text(
                text = "Back",
                style = Day1Type.callout,
                color = Day1Colors.TextSecondary,
                modifier = Modifier.padding(vertical = Day1Spacing.xs),
            )
        }
    }
}

@Composable
private fun PolicyRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Day1Spacing.xs),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = label, style = Day1Type.body, color = Day1Colors.TextSecondary)
        Text(text = value, style = Day1Type.callout, color = Day1Colors.TextPrimary)
    }
}
