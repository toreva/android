package com.toreva.mobile.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.toreva.mobile.ui.theme.Day1Colors
import com.toreva.mobile.ui.theme.Day1Radius
import com.toreva.mobile.ui.theme.Day1Type

enum class Day1ButtonSize(val height: Dp) {
    SM(36.dp), MD(44.dp), LG(56.dp)
}

@Composable
fun Day1Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Day1ButtonSize = Day1ButtonSize.LG,
    enabled: Boolean = true,
    fullWidth: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(size.height)
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier),
        enabled = enabled,
        shape = RoundedCornerShape(Day1Radius.pill),
        colors = ButtonDefaults.buttonColors(
            containerColor = Day1Colors.SecondaryOrange,
            contentColor = Day1Colors.TextPrimary,
            disabledContainerColor = Day1Colors.NeutralGrey,
            disabledContentColor = Day1Colors.TextMuted,
        ),
    ) {
        Text(text = text, style = Day1Type.callout)
    }
}
