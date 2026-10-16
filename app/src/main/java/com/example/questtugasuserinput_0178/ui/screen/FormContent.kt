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

@Composable
fun NameRow(first: String, onFirst: (String) -> Unit, last: String, onLast: (String) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))) {
        AppTextField(first, onFirst, stringResource(R.string.hint_first_name), Modifier.weight(1f))
        AppTextField(last, onLast, stringResource(R.string.hint_last_name), Modifier.weight(1f))
    }
}

@Composable
fun EmailField(email: String, onChange: (String) -> Unit) {
    AppTextField(
        value = email,
        onValueChange = onChange,
        placeholder = stringResource(R.string.hint_email),
        keyboardType = KeyboardType.Email,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BirthDateField(date: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(modifier) {
        AppTextField(
            value = date,
            onValueChange = {},
            placeholder = stringResource(R.string.hint_birth_date),
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(Icons.Default.DateRange, stringResource(R.string.cd_calendar))
            }
        )
        Box(Modifier.matchParentSize().clickable(onClick = onClick))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDatePickerDialog(onDismiss: () -> Unit, onDateSelected: (String) -> Unit) {
    val state = rememberDatePickerState()
    val pattern = stringResource(R.string.date_format)
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                state.selectedDateMillis?.let {
                    val fmt = SimpleDateFormat(pattern, Locale.getDefault())
                    fmt.timeZone = TimeZone.getTimeZone("UTC")
                    onDateSelected(fmt.format(Date(it)))
                }
                onDismiss()
            }) { Text(stringResource(R.string.btn_ok)) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.btn_cancel)) }
        }
    ) { DatePicker(state = state) }
}