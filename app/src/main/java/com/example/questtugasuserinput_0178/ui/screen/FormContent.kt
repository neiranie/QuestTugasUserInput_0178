package com.example.questtugasuserinput_0178.ui.screen

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.questtugasuserinput_0178.R
import com.example.questtugasuserinput_0178.ui.component.AppTextField
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var experience by rememberSaveable { mutableStateOf("") }
    var showPicker by rememberSaveable { mutableStateOf(false) }
    val skills = remember { mutableStateListOf<Int>() }

    val gap = dimensionResource(R.dimen.spacing_medium)

    FormHeaderText()
    NameRow(firstName, { firstName = it }, lastName, { lastName = it })
    Spacer(Modifier.height(gap))
    EmailField(email) { email = it }
    Spacer(Modifier.height(gap))
    Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))) {
        BirthDateField(birthDate, Modifier.weight(1f)) { showPicker = true }
        ExperienceDropdown(experience, Modifier.weight(1f)) { experience = it }
    }
    Spacer(Modifier.height(gap))
    SkillsLabel()
    SkillsRow(skills) { id -> if (id in skills) skills.remove(id) else skills.add(id) }
    Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

    val isValid = firstName.isNotBlank() && lastName.isNotBlank() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            birthDate.isNotBlank() && experience.isNotBlank() && skills.isNotEmpty()
    ProceedButton(enabled = isValid) { }

    if (showPicker) {
        BirthDatePickerDialog(
            onDismiss = { showPicker = false },
            onDateSelected = { birthDate = it }
        )
    }
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
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = stringResource(R.string.cd_calendar)
                )
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

@Composable
fun ExperienceDropdown(selected: String, modifier: Modifier = Modifier, onSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = stringArrayResource(R.array.experience_options)
    Box(modifier) {
        AppTextField(
            value = selected,
            onValueChange = {},
            placeholder = stringResource(R.string.hint_experience),
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_dropdown),
                    contentDescription = stringResource(R.string.cd_dropdown)
                )
            }
        )
        Box(Modifier.matchParentSize().clickable { expanded = true })
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { onSelected(option); expanded = false }
                )
            }
        }
    }
}

@Composable
fun SkillsLabel() {
    Text(
        text = stringResource(R.string.label_skills),
        color = colorResource(R.color.text_label),
        fontSize = dimensionResource(R.dimen.text_label).value.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SkillCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(R.color.purple_primary),
                uncheckedColor = colorResource(R.color.checkbox_border)
            )
        )
        Text(
            text = label,
            color = colorResource(R.color.text_label),
            fontSize = dimensionResource(R.dimen.text_skill).value.sp
        )
    }
}

@Composable
fun SkillsRow(selected: List<Int>, onToggle: (Int) -> Unit) {
    val skills = listOf(R.string.skill_android, R.string.skill_uiux, R.string.skill_web)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        skills.forEach { id ->
            SkillCheckbox(stringResource(id), id in selected) { onToggle(id) }
        }
    }
}

@Composable
fun ProceedButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.purple_primary),
            contentColor = colorResource(R.color.button_enabled_text),
            disabledContainerColor = colorResource(R.color.button_disabled_bg),
            disabledContentColor = colorResource(R.color.button_disabled_text)
        ),
        modifier = Modifier.fillMaxWidth().height(dimensionResource(R.dimen.button_height))
    ) {
        Text(
            text = stringResource(R.string.btn_proceed),
            fontSize = dimensionResource(R.dimen.text_button).value.sp
        )
    }
}