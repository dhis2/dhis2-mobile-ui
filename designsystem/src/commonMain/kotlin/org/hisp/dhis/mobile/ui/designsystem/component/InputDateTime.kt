package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTimeVisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2LightColorScheme
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Input field to enter date, time or date&time. It will format content based on given visual
 * transformation
 * @param uiModel an [InputDateTimeModel] with all the parameters for the input
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDateTime(
    uiModel: InputDateTimeModel,
    modifier: Modifier = Modifier,
) {
    val allowedCharacters = RegExValidations.DATE_TIME.regex
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var showTimePicker by rememberSaveable { mutableStateOf(false) }

    InputShell(
        modifier = modifier.testTag("INPUT_DATE_TIME")
            .focusRequester(focusRequester),
        title = uiModel.title,
        state = uiModel.state,
        isRequiredField = uiModel.isRequired,
        onFocusChanged = uiModel.onFocusChanged,
        inputField = {
            if (uiModel.allowsManualInput) {
                BasicTextField(
                    modifier = Modifier
                        .testTag("INPUT_DATE_TIME_TEXT_FIELD")
                        .fillMaxWidth(),
                    inputTextValue = uiModel.inputTextFieldValue ?: TextFieldValue(),
                    isSingleLine = true,
                    onInputChanged = { newText ->
                        if (newText.text.length > uiModel.visualTransformation.maskLength) {
                            return@BasicTextField
                        }

                        if (allowedCharacters.containsMatchIn(newText) || newText.isBlank()) {
                            uiModel.onValueChanged.invoke(newText)
                        }
                    },
                    enabled = uiModel.state != InputShellState.DISABLED,
                    state = uiModel.state,
                    keyboardOptions = KeyboardOptions(imeAction = uiModel.imeAction, keyboardType = KeyboardType.Number),
                    visualTransformation = uiModel.visualTransformation,
                    onNextClicked = {
                        if (uiModel.onNextClicked != null) {
                            uiModel.onNextClicked.invoke()
                        } else {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    },
                )
            } else {
                Box {
                    Text(
                        modifier = Modifier
                            .testTag("INPUT_DATE_TIME_TEXT")
                            .fillMaxWidth(),
                        text = uiModel.visualTransformation.filter(AnnotatedString(uiModel.inputTextFieldValue?.text.orEmpty())).text,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (uiModel.state != InputShellState.DISABLED && !uiModel.inputTextFieldValue?.text.isNullOrEmpty()) {
                                TextColor.OnSurface
                            } else {
                                TextColor.OnDisabledSurface
                            },
                        ),
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .alpha(0f)
                            .clickable(
                                enabled = uiModel.state != InputShellState.DISABLED,
                                onClick = {
                                    if (uiModel.actionType == DateTimeActionType.TIME) {
                                        showTimePicker = !showTimePicker
                                    } else {
                                        showDatePicker = !showDatePicker
                                    }
                                },
                            ),
                    )
                }
            }
        },
        primaryButton = {
            if (!uiModel.inputTextFieldValue?.text.isNullOrBlank() && state != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_DATE_TIME_RESET_BUTTON").padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        uiModel.onValueChanged.invoke(TextFieldValue())
                        focusRequester.requestFocus()
                    },
                )
            }
        },
        secondaryButton = {
            val icon = when (uiModel.actionType) {
                DateTimeActionType.DATE, DateTimeActionType.DATE_TIME -> Icons.Filled.Event
                DateTimeActionType.TIME -> Icons.Filled.Schedule
            }

            SquareIconButton(
                modifier = Modifier.testTag("INPUT_DATE_TIME_ACTION_BUTTON")
                    .focusable(),
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                },
                onClick = {
                    if (uiModel.actionType == DateTimeActionType.TIME) {
                        showTimePicker = !showTimePicker
                    } else {
                        showDatePicker = !showDatePicker
                    }
                },
                enabled = uiModel.state != InputShellState.DISABLED,
            )
        },
        supportingText = {
            uiModel.supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_DATE_TIME_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            uiModel.legendData?.let {
                Legend(uiModel.legendData, Modifier.testTag("INPUT_DATE_TIME_LEGEND"))
            }
        },
        inputStyle = inputStyle,
    )
    var datePickerState = rememberDatePickerState()
    if (!uiModel.value.isNullOrEmpty() && uiModel.actionType != DateTimeActionType.TIME) {
        datePickerState = if (uiModel.actionType == DateTimeActionType.DATE_TIME && uiModel.value.length == 12) {
            rememberDatePickerState(initialSelectedDateMillis = parseStringDateToMillis(uiModel.value.substring(0, uiModel.value.length - 4)))
        } else {
            rememberDatePickerState(initialSelectedDateMillis = parseStringDateToMillis(uiModel.value))
        }
    }

    if (showDatePicker) {
        MaterialTheme(
            colorScheme = DHIS2LightColorScheme.copy(primary = SurfaceColor.ContainerHighest),
        ) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    Button(
                        enabled = true,
                        ButtonStyle.TEXT,
                        ColorStyle.DEFAULT,
                        "OK",
                    ) {
                        showDatePicker = false
                        if (uiModel.actionType != DateTimeActionType.DATE_TIME) {
                            datePickerState.selectedDateMillis?.let {
                                uiModel.onValueChanged(TextFieldValue(getDate(it)))
                            }
                        } else {
                            showTimePicker = true
                        }
                    }
                },
                colors = datePickerColors(),
                dismissButton = {
                    Button(
                        enabled = true,
                        ButtonStyle.TEXT,
                        ColorStyle.DEFAULT,
                        "Cancel",

                    ) {
                        showDatePicker = false
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = true,
                ),
            ) {
                DatePicker(
                    title = {
                        Text(
                            text = uiModel.title,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = Spacing.Spacing24, top = Spacing.Spacing24),
                        )
                    },
                    state = datePickerState,
                )
            }
        }
    }

    if (showTimePicker) {
        var timePickerState = rememberTimePickerState(0, 0, is24Hour = uiModel.is24hourFormat)
        if (!uiModel.value.isNullOrEmpty() && uiModel.actionType == DateTimeActionType.TIME) {
            timePickerState = rememberTimePickerState(initialHour = uiModel.value.substring(0, 2).toInt(), uiModel.value.substring(2, 4).toInt(), is24Hour = uiModel.is24hourFormat)
        } else {
            if (uiModel.value?.length == 12) {
                timePickerState = rememberTimePickerState(initialHour = uiModel.value.substring(uiModel.value.length - 4, uiModel.value.length - 2).toInt(), uiModel.value.substring(uiModel.value.length - 2, uiModel.value.length).toInt(), is24Hour = uiModel.is24hourFormat)
            }
        }
        Dialog(
            onDismissRequest = { showDatePicker = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true, usePlatformDefaultWidth = true),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(
                    color = SurfaceColor.Container,
                    shape = RoundedCornerShape(Radius.L),
                )
                    .padding(vertical = Spacing.Spacing16, horizontal = Spacing.Spacing24),
            ) {
                Text(
                    text = uiModel.title,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = Spacing.Spacing16).align(Alignment.Start),
                )
                TimePicker(
                    state = timePickerState,
                    layoutType = TimePickerLayoutType.Vertical,
                    colors = timePickerColors(),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
                Row(Modifier.align(Alignment.End)) {
                    Button(
                        enabled = true,
                        ButtonStyle.TEXT,
                        ColorStyle.DEFAULT,
                        "Cancel",

                    ) {
                        showTimePicker = false
                    }
                    Button(
                        enabled = true,
                        ButtonStyle.TEXT,
                        ColorStyle.DEFAULT,
                        "OK",
                    ) {
                        showTimePicker = false
                        if (uiModel.actionType != DateTimeActionType.DATE_TIME) {
                            uiModel.onValueChanged(TextFieldValue(getTime(timePickerState)))
                        } else {
                            uiModel.onValueChanged(TextFieldValue(getDate(datePickerState.selectedDateMillis) + getTime(timePickerState)))
                        }
                    }
                }
            }
        }
    }
}

enum class DateTimeActionType {
    DATE, TIME, DATE_TIME
}

/**
 * UiModel used for [InputDateTime]
 * @param title : Label of the component.
 * @param value: Input of the component in the format of DDMMYYYY/HHMM/DDMMYYYYHHMM
 * @param actionType: Type of action icon to display. [DateTimeActionType.DATE_TIME], [DateTimeActionType.DATE], [DateTimeActionType.TIME]
 * @param onActionClicked: Callback to handle the action when the calendar icon is clicked.
 * @param state: [InputShellState]
 * @param legendData: [LegendData]
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param isRequired: Mark this input as marked
 * @param visualTransformation: Pass a visual transformation to format the date input visually. By default uses [DateTransformation]
 * @param onValueChanged: Callback to receive changes in the input in the format of DDMMYYYY/HHMM/DDMMYYYYHHMM
 */
data class InputDateTimeModel(
    val title: String,
    val inputTextFieldValue: TextFieldValue?,
    val actionType: DateTimeActionType = DateTimeActionType.DATE_TIME,
    val allowsManualInput: Boolean = true,
    val onActionClicked: () -> Unit,
    val state: InputShellState = InputShellState.UNFOCUSED,
    val legendData: LegendData? = null,
    val supportingText: List<SupportingTextData>? = null,
    val onNextClicked: (() -> Unit)? = null,
    val isRequired: Boolean = false,
    val imeAction: ImeAction = ImeAction.Next,
    val visualTransformation: DateTimeVisualTransformation = DateTransformation(),
    val format: String,
    val onFocusChanged: ((Boolean) -> Unit) = {},
    val onValueChanged: (TextFieldValue?) -> Unit,
    val is24hourFormat: Boolean = false,
)

fun getDate(milliSeconds: Long?, format: String? = "ddMMYYYY"): String {
    val cal = Calendar.getInstance()
    return if (milliSeconds != null) {
        cal.timeInMillis = milliSeconds
        val formater = SimpleDateFormat(format)
        formater.format(cal.time)
    } else {
        ""
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun getTime(timePickerState: TimePickerState, format: String? = "HHmm"): String {
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
    cal.set(Calendar.MINUTE, timePickerState.minute)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)

    val formater = SimpleDateFormat(format)
    return formater.format(cal.time)
}

fun parseStringDateToMillis(dateString: String, pattern: String = "ddMMyyyy"): Long {
    return if (dateString.isNotEmpty()) {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val date = dateFormat.parse(dateString)
        date?.time ?: 0L
    } else {
        0L
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun timePickerColors(): TimePickerColors {
    return TimePickerDefaults.colors(
        containerColor = SurfaceColor.Container,
        clockDialColor = SurfaceColor.ContainerHigh,
        clockDialUnselectedContentColor = TextColor.OnSurface,
        clockDialSelectedContentColor = TextColor.OnPrimary,
        timeSelectorSelectedContentColor = TextColor.OnPrimaryContainer,
        timeSelectorUnselectedContainerColor = SurfaceColor.ContainerHigh,
        timeSelectorUnselectedContentColor = TextColor.OnSurface,
        periodSelectorSelectedContainerColor = SurfaceColor.WarningContainer,
        periodSelectorUnselectedContentColor = TextColor.OnSurfaceVariant,
        periodSelectorSelectedContentColor = SurfaceColor.Warning,
        periodSelectorUnselectedContainerColor = SurfaceColor.Container,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerColors(): DatePickerColors {
    return DatePickerDefaults.colors(
        selectedDayContainerColor = Color.Red,
        selectedDayContentColor = TextColor.OnPrimary,
        containerColor = SurfaceColor.Container,
        todayDateBorderColor = SurfaceColor.Primary,
        selectedYearContainerColor = SurfaceColor.Primary,
        selectedYearContentColor = TextColor.OnPrimary,
        dayInSelectionRangeContainerColor = Color.Red,
    )
}
