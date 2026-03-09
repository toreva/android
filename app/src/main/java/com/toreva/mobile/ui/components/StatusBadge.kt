package com.toreva.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Radius
import com.toreva.mobile.ui.theme.Day1Spacing
import com.toreva.mobile.ui.theme.Day1Type

@Composable
fun StatusBadge(text: String, live: Boolean) {
    Text(
        text = text,
        style = Day1Type.caption2,
        color = Day1Colors.TextPrimary,
        modifier = Modifier
            .background(
                if (live) Day1Colors.PrimaryTeal else Day1Colors.NeutralGrey,
                RoundedCornerShape(Day1Radius.pill),
            )
            .padding(horizontal = Day1Spacing.sm, vertical = Day1Spacing.xxs),
    )
}
