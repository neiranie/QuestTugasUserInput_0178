package com.example.questtugasuserinput_0178.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.questtugasuserinput_0178.R

@Composable
fun FormBiodataScreen() {
    Scaffold(containerColor = colorResource(R.color.screen_background)) { inner ->
        Box(modifier = Modifier.fillMaxSize().padding(inner)) {
            HeaderImage(Modifier.align(Alignment.TopCenter))
            PagerDots(Modifier.align(Alignment.TopStart))
            FloatingCard(Modifier.align(Alignment.BottomCenter)) {
                FormContent()
            }
        }
    }
}

@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.img_header_maps),
        contentDescription = stringResource(R.string.cd_header_image),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.header_height))
    )
}

@Composable
fun PagerDots(modifier: Modifier = Modifier, activeIndex: Int = 0) {
    Row(
        modifier = modifier.padding(dimensionResource(R.dimen.dot_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dot_spacing))
    ) {
        repeat(integerResource(R.integer.dot_count)) { index ->
            Box(
                Modifier
                    .size(dimensionResource(R.dimen.dot_size))
                    .clip(CircleShape)
                    .background(
                        colorResource(
                            if (index == activeIndex) R.color.dot_active else R.color.dot_inactive
                        )
                    )
            )
        }
    }
}

@Composable
fun FloatingCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    val shape = RoundedCornerShape(
        topStart = dimensionResource(R.dimen.card_corner),
        topEnd = dimensionResource(R.dimen.card_corner)
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = dimensionResource(R.dimen.card_top_margin))
            .shadow(dimensionResource(R.dimen.card_elevation), shape)
            .background(colorResource(R.color.card_background), shape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(R.dimen.card_padding)),
            content = content
        )
    }
}