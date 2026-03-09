package com.toreva.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.toreva.mobile.ui.theme.Day1Colors

data class NavItem(
    val route: String,
    val icon: ImageVector,
    val label: String,
)

@Composable
fun BottomNavDay1(
    items: List<NavItem>,
    currentRoute: String,
    onNavigate: (String) -> Unit,
) {
    val safeAreaBottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Day1Colors.Background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { onNavigate(item.route) }
                        .weight(1f),
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (selected) Day1Colors.TextPrimary else Day1Colors.TextMuted,
                        modifier = Modifier.size(24.dp),
                    )
                    if (selected) {
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .width(20.dp)
                                .height(2.dp)
                                .background(Day1Colors.TextPrimary, RoundedCornerShape(1.dp)),
                        )
                    }
                }
            }
        }
        if (safeAreaBottom > 0.dp) {
            Box(Modifier.height(safeAreaBottom))
        }
    }
}
