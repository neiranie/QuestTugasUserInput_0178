package com.example.questtugasuserinput.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.questtugasuserinput.R

@Composable
fun QuestTheme(content: @Composable () -> Unit) {
    val scheme = lightColorScheme(
        primary = colorResource(R.color.purple_primary),
        background = colorResource(R.color.screen_background),
        surface = colorResource(R.color.card_background),
        onSurface = colorResource(R.color.text_label)
    )
    MaterialTheme(colorScheme = scheme, typography = AppTypography, content = content)
}