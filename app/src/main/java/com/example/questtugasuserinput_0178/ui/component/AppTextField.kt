package com.example.questtugasuserinput_0178.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.questtugasuserinput_0178.R

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                color = colorResource(R.color.text_hint),
                fontSize = dimensionResource(R.dimen.text_field).value.sp
            )
        },
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner)),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.purple_primary),
            unfocusedBorderColor = colorResource(R.color.field_border),
            focusedTextColor = colorResource(R.color.text_label),
            unfocusedTextColor = colorResource(R.color.text_label)
        ),
        modifier = modifier.height(dimensionResource(R.dimen.field_height))
    )
}