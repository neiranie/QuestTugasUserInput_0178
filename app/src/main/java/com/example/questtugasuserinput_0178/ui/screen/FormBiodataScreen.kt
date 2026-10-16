package com.example.questtugasuserinput_0178.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.questtugasuserinput_0178.R

@Composable
fun FormBiodataScreen() {
    Scaffold(containerColor = colorResource(R.color.screen_background)) { inner ->
        Box(modifier = Modifier.fillMaxSize().padding(inner)) {
            // header and floating card are added in the next commits
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