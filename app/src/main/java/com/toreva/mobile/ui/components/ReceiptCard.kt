package com.toreva.mobile.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Radius
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type
import com.toreva.mobile.ui.theme.SpaceMono

@Composable
fun ReceiptCard(title: String, subtitle: String, signature: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Day1Radius.card),
        color = Day1Colors.BackgroundCard,
        border = BorderStroke(1.dp, Day1Colors.Border),
    ) {
        Column(
            modifier = Modifier.padding(Day1Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Day1Spacing.xxs),
        ) {
            Text(text = title, style = Day1Type.callout, color = Day1Colors.TextPrimary)
            Text(text = subtitle, style = Day1Type.body, color = Day1Colors.TextSecondary)
            Text(
                text = "Tx: $signature",
                style = Day1Type.caption.copy(fontFamily = SpaceMono),
                color = Day1Colors.PrimaryTeal,
            )
        }
    }
}
