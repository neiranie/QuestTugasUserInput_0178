package com.example.questtugasuserinput_0178.ui.screen

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.questtugasuserinput_0178.R

@Composable
fun ColumnScope.FormHeaderText() {
    Text(
        text = stringResource(R.string.form_title),
        color = colorResource(R.color.purple_primary),
        fontSize = dimensionResource(R.dimen.text_title).value.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))
    Text(
        text = stringResource(R.string.form_subtitle),
        color = colorResource(R.color.text_subtitle),
        fontSize = dimensionResource(R.dimen.text_subtitle).value.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))
}

@Composable
fun ColumnScope.FormContent() {
    FormHeaderText()
}