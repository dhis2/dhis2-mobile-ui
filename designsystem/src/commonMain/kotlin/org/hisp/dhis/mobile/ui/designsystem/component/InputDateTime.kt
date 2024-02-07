package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.AddLocationAlt
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTimeVisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import java.text.SimpleDateFormat
import java.util.Calendar

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
                                    if (uiModel.actionIconType == DateTimeActionType.TIME) {
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
            val icon = when (uiModel.actionIconType) {
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
                    if (uiModel.actionIconType == DateTimeActionType.TIME) {
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
    if (!uiModel.value.isNullOrEmpty() && uiModel.actionIconType != DateTimeActionType.TIME) {
        datePickerState = rememberDatePickerState(initialSelectedDateMillis = parseStringDateToMillis(uiModel.value))
    }

    if (showDatePicker) {
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
                    if (uiModel.actionIconType != DateTimeActionType.DATE_TIME) {
                        datePickerState.selectedDateMillis?.let {
                            uiModel.onValueChanged(getDate(it))
                        }
                    } else {
                        showTimePicker = true
                    }
                }
            },
            dismissButton = {
                Button(
                    enabled = true,
                    ButtonStyle.TEXT,
                    ColorStyle.DEFAULT,
                    "Cancel",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AddLocationAlt,
                            contentDescription = "Add Location Button",
                            tint = SurfaceColor.Primary,
                        )
                    },
                ) {
                    showDatePicker = false
                }
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true, usePlatformDefaultWidth = true),
        ) {
            uiModel.value?.let {
                datePickerState = rememberDatePickerState(initialSelectedDateMillis = parseStringDateToMillis(it))
            }
            DatePicker(
                state = datePickerState,
            )
        }
    }

    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(0, 0, is24Hour = uiModel.is24hourFormat)

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(
                    enabled = true,
                    ButtonStyle.TEXT,
                    ColorStyle.DEFAULT,
                    "OK",
                ) {
                    showTimePicker = false
                    if (uiModel.actionIconType != DateTimeActionType.DATE_TIME) {
                        uiModel.onValueChanged(getTime(timePickerState))
                    } else {
                        uiModel.onValueChanged(getDate(datePickerState.selectedDateMillis) + getTime(timePickerState))
                    }
                }
            },
            dismissButton = {
                Button(
                    enabled = true,
                    ButtonStyle.TEXT,
                    ColorStyle.DEFAULT,
                    "Cancel",

                ) {
                    showTimePicker = false
                }
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true, usePlatformDefaultWidth = true),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box {
                    TimePicker(
                        state = timePickerState,
                        layoutType = TimePickerLayoutType.Vertical,
                    )
                }
            }
        }
        // val showingPicker = remember { mutableStateOf(true) }
    }
}

enum class DateTimeActionType {
    DATE, TIME, DATE_TIME
}

/**
 * UiModel used for [InputDateTime]
 * @param title : Label of the component.
 * @param value: Input of the component in the format of DDMMYYYY/HHMM/DDMMYYYYHHMM
 * @param actionIconType: Type of action icon to display. [DateTimeActionType.DATE_TIME], [DateTimeActionType.DATE], [DateTimeActionType.TIME]
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
    val actionIconType: DateTimeActionType = DateTimeActionType.DATE_TIME,
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
    // Create a calendar object that will convert the date and time value in milliseconds to date.
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
    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
    cal.set(Calendar.MINUTE, timePickerState.minute)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)

    val formater = SimpleDateFormat(format)
    return formater.format(cal.time)
}

fun parseStringDateToMillis(dateString: String, pattern: String = "ddMMYYYY"): Long {
    return if (!dateString.isNotEmpty()) {
        val dateFormat = SimpleDateFormat(pattern)
        val date = dateFormat.parse(dateString)
        date?.time ?: 0L
    } else {
        0L
    }
}
