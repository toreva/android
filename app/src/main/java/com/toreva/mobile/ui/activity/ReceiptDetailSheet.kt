package com.toreva.mobile.ui.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.toreva.mobile.ui.components.Day1Button
import com.toreva.mobile.ui.components.Day1ButtonSize
import com.toreva.mobile.ui.components.Day1Sheet
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type
import com.toreva.mobile.ui.theme.SpaceMono

@Composable
fun ReceiptDetailSheet(
    title: String = "USDC Yield · Deploy",
    venue: String = "Kamino",
    apy: String = "8.2%",
    signature: String = "5xK3...9mP2",
    onVerify: () -> Unit = {},
) {
    Day1Sheet {
        Text(text = title, style = Day1Type.headline, color = Day1Colors.TextPrimary)
        Spacer(Modifier.height(Day1Spacing.md))
        DetailRow("Venue", venue)
        DetailRow("APY", apy)
        DetailRow("Signature", signature, mono = true)
        Spacer(Modifier.height(Day1Spacing.lg))
        Day1Button(
            text = "Verify on Solscan",
            onClick = onVerify,
            size = Day1ButtonSize.LG,
        )
    }
}

@Composable
private fun DetailRow(label: String, value: String, mono: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Day1Spacing.xs),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = label, style = Day1Type.body, color = Day1Colors.TextSecondary)
        Text(
            text = value,
            style = if (mono) Day1Type.body.copy(fontFamily = SpaceMono) else Day1Type.callout,
            color = if (mono) Day1Colors.PrimaryTeal else Day1Colors.TextPrimary,
        )
    }
}
