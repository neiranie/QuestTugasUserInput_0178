package com.example.questtugasuserinput_0178.ui.theme

import android.graphics.Typeface
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

val AppFontFamily = FontFamily(Typeface.create("casual", Typeface.NORMAL))

val AppTypography = Typography(
    displayLarge = TextStyle(fontFamily = AppFontFamily),
    headlineLarge = TextStyle(fontFamily = AppFontFamily),
    titleLarge = TextStyle(fontFamily = AppFontFamily),
    titleMedium = TextStyle(fontFamily = AppFontFamily),
    bodyLarge = TextStyle(fontFamily = AppFontFamily),
    bodyMedium = TextStyle(fontFamily = AppFontFamily),
    bodySmall = TextStyle(fontFamily = AppFontFamily),
    labelLarge = TextStyle(fontFamily = AppFontFamily),
    labelMedium = TextStyle(fontFamily = AppFontFamily)
)