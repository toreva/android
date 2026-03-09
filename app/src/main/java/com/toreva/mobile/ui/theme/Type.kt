package com.toreva.mobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.toreva.mobile.R

val SpaceGrotesk = FontFamily(
    Font(R.font.space_grotesk_regular, FontWeight.Normal),
    Font(R.font.space_grotesk_medium, FontWeight.Medium),
    Font(R.font.space_grotesk_semibold, FontWeight.SemiBold),
    Font(R.font.space_grotesk_bold, FontWeight.Bold),
)

val SpaceMono = FontFamily(
    Font(R.font.space_mono_regular, FontWeight.Normal),
)

object Day1Type {
    val caption2 = TextStyle(fontFamily = SpaceGrotesk, fontSize = 11.sp, fontWeight = FontWeight.Normal, lineHeight = (11 * 1.3).sp)
    val caption = TextStyle(fontFamily = SpaceGrotesk, fontSize = 12.sp, fontWeight = FontWeight.Normal, lineHeight = (12 * 1.35).sp)
    val footnote = TextStyle(fontFamily = SpaceGrotesk, fontSize = 13.sp, fontWeight = FontWeight.Normal, lineHeight = (13 * 1.4).sp)
    val body = TextStyle(fontFamily = SpaceGrotesk, fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = (14 * 1.45).sp)
    val callout = TextStyle(fontFamily = SpaceGrotesk, fontSize = 16.sp, fontWeight = FontWeight.Medium, lineHeight = (16 * 1.4).sp)
    val headline = TextStyle(fontFamily = SpaceGrotesk, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, lineHeight = (18 * 1.35).sp)
    val title3 = TextStyle(fontFamily = SpaceGrotesk, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, lineHeight = (20 * 1.3).sp)
    val title2 = TextStyle(fontFamily = SpaceGrotesk, fontSize = 24.sp, fontWeight = FontWeight.Bold, lineHeight = (24 * 1.25).sp)
    val title1 = TextStyle(fontFamily = SpaceGrotesk, fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = (28 * 1.2).sp)
    val mono = TextStyle(fontFamily = SpaceMono, fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = (14 * 1.45).sp)
}

val AppTypography = Typography(
    displayLarge = Day1Type.title1,
    displayMedium = Day1Type.title2,
    displaySmall = Day1Type.title3,
    headlineLarge = Day1Type.title1,
    headlineMedium = Day1Type.title2,
    headlineSmall = Day1Type.headline,
    titleLarge = Day1Type.title2,
    titleMedium = Day1Type.headline,
    titleSmall = Day1Type.callout,
    bodyLarge = Day1Type.callout,
    bodyMedium = Day1Type.body,
    bodySmall = Day1Type.footnote,
    labelLarge = Day1Type.callout,
    labelMedium = Day1Type.caption,
    labelSmall = Day1Type.caption2,
)
