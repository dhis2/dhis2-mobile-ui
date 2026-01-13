package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.internal.convertStringToTextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.internal.formatStoredDateToUI
import org.hisp.dhis.mobile.ui.designsystem.component.internal.formatUIDateToStored
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getSupportingTextList
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getTime
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getTimePickerState
import org.hisp.dhis.mobile.ui.designsystem.component.internal.provideDatePickerState
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeState
import org.hisp.dhis.mobile.ui.designsystem.platform.dates.getDate
import org.hisp.dhis.mobile.ui.designsystem.platform.dates.normalizeToGregorian
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.component.DatePicker as DHIS2DatePicker
import org.hisp.dhis.mobile.ui.designsystem.component.TimePicker as DHIS2TimePicker

/**
 * DHIS2 Input Date Time
 * Input field to enter date, time or date&time. It will format content based on given visual
 * transformation.
 * component uses Material 3 [DatePicker] and [TimePicker]
 * input formats supported are mentioned in the date time input ui model documentation.
 * [DatePicker] Input mode  will always follow locale format.
 * @param state: an [InputDateTimeState] with all the parameters for the input
 * @param modifier: optional modifier.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDateTime(
    state: InputDateTimeState,
    onFocusChanged: ((Boolean) -> Unit) = {},
    onValueChanged: (TextFieldValue?) -> Unit,
    onImeActionClick: ((ImeAction) -> Unit)? = null,
    onActionClicked: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val uiData = state.uiData

    val uiValue =
        remember(state.inputTextFieldValue) {
            formatStoredDateToUI(
                state.inputTextFieldValue ?: TextFieldValue(),
                uiData.actionType,
            )
        }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var showTimePicker by rememberSaveable { mutableStateOf(false) }
    var dateOutOfRangeText = uiData.outOfRangeText ?: provideStringResource("date_out_of_range")

    dateOutOfRangeText = "$dateOutOfRangeText (" +
        formatStringToDate(
            uiData.selectableDates.initialDate,
        ) + " - " +
        formatStringToDate(uiData.selectableDates.endDate) + ")"
    val incorrectHourFormat =
        uiData.incorrectHourFormatText ?: provideStringResource("wrong_hour_format")
    val incorrectHourFormatItem =
        SupportingTextData(
            text = incorrectHourFormat,
            SupportingTextState.ERROR,
        )
    val incorrectDateFormatItem =
        SupportingTextData(
            text = provideStringResource("incorrect_date_format"),
            SupportingTextState.ERROR,
        )
    val dateOutOfRangeItem =
        SupportingTextData(
            text = dateOutOfRangeText,
            SupportingTextState.ERROR,
        )
    val supportingTextList =
        getSupportingTextList(
            state,
            uiValue,
            uiData,
            dateOutOfRangeItem,
            incorrectHourFormatItem,
            incorrectDateFormatItem,
        )

    InputShell(
        modifier =
            modifier
                .testTag("INPUT_DATE_TIME")
                .focusRequester(focusRequester),
        title = uiData.title,
        state =
            getInputState(
                supportingTextList,
                dateOutOfRangeItem,
                incorrectDateFormatItem,
                state.inputState,
            ),
        isRequiredField = uiData.isRequired,
        onFocusChanged = onFocusChanged,
        inputField = {
            if (uiData.allowsManualInput) {
                BasicTextField(
                    modifier =
                        Modifier
                            .testTag("INPUT_DATE_TIME_TEXT_FIELD")
                            .fillMaxWidth(),
                    inputTextValue = uiValue,
                    isSingleLine = true,
                    onInputChanged = { newText ->
                        if (newText.text.length > uiData.visualTransformation.maskLength) {
                            return@BasicTextField
                        }

                        manageOnValueChanged(newText, onValueChanged, uiData.actionType)
                    },
                    enabled = state.inputState != InputShellState.DISABLED,
                    state = state.inputState,
                    keyboardOptions =
                        KeyboardOptions(
                            imeAction = uiData.imeAction,
                            keyboardType = KeyboardType.Number,
                        ),
                    visualTransformation = uiData.visualTransformation,
                    onImeActionClick = {
                        if (onImeActionClick != null) {
                            onImeActionClick.invoke(it)
                        } else {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    },
                )
            } else {
                Box {
                    Text(
                        modifier =
                            Modifier
                                .testTag("INPUT_DATE_TIME_TEXT")
                                .fillMaxWidth(),
                        text = uiData.visualTransformation.filter(AnnotatedString(uiValue.text)).text,
                        style =
                            MaterialTheme.typography.bodyLarge.copy(
                                color = getTextColor(state.inputState, state.inputTextFieldValue),
                            ),
                    )
                    Box(
                        modifier =
                            Modifier
                                .matchParentSize()
                                .alpha(0f)
                                .clickable(
                                    enabled = state.inputState != InputShellState.DISABLED,
                                    onClick = {
                                        if (uiData.actionType == DateTimeActionType.TIME) {
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
            InputDateResetButton(state, onValueChanged, focusRequester)
        },
        secondaryButton = {
            val icon = getActionButtonIcon(uiData.actionType)

            SquareIconButton(
                modifier =
                    Modifier
                        .testTag("INPUT_DATE_TIME_ACTION_BUTTON")
                        .focusable(),
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    if (onActionClicked != null) {
                        onActionClicked.invoke()
                    } else if (uiData.actionType == DateTimeActionType.TIME) {
                        showTimePicker = !showTimePicker
                    } else {
                        showDatePicker = !showDatePicker
                    }
                },
                enabled = state.inputState != InputShellState.DISABLED,
            )
        },
        supportingText = supportingTextList,
        supportingTextTestTag = "INPUT_DATE_TIME_SUPPORTING_TEXT",
        legend = {
            state.legendData?.let {
                Legend(it, Modifier.testTag("INPUT_DATE_TIME_LEGEND"))
            }
        },
        inputStyle = uiData.inputStyle,
    )
    var datePickerState = provideDatePickerState(uiValue, uiData)

    if (showDatePicker) {
        DHIS2DatePicker(
            onConfirm = { updatedState ->
                datePickerState = updatedState
                showDatePicker = false
                if (uiData.actionType != DateTimeActionType.DATE_TIME) {
                    datePickerState.selectedDateMillis?.let {
                        manageOnValueChanged(
                            TextFieldValue(
                                getDate(it),
                                selection =
                                    TextRange(
                                        state.inputTextFieldValue?.text?.length ?: 0,
                                    ),
                            ),
                            onValueChanged,
                            uiData.actionType,
                        )
                    }
                } else {
                    showTimePicker = true
                }
            },
            onCancel = {
                showDatePicker = false
            },
            onDismissRequest = { showDatePicker = false },
            state = datePickerState,
            title = uiData.title,
            acceptText = uiData.acceptText,
            cancelText = uiData.cancelText,
        )
    }

    if (showTimePicker) {
        val timePickerState = getTimePickerState(uiValue, uiData)

        DHIS2TimePicker(
            state = timePickerState,
            title = uiData.title,
            onDismissRequest = { showTimePicker = false },
            onAccept = {
                showTimePicker = false
                manageOnValueChangedFromDateTimePicker(
                    convertStringToTextFieldValue(
                        getTime(
                            timePickerState,
                        ),
                    ),
                    onValueChanged,
                    uiData.actionType,
                    datePickerState,
                    timePickerState,
                )
            },
            onCancel = {
                showTimePicker = false
            },
            acceptText = uiData.acceptText,
            cancelText = uiData.cancelText,
        )
    }
}

private fun getInputState(
    supportingTextList: List<SupportingTextData>,
    dateOutOfRangeItem: SupportingTextData,
    incorrectDateFormatItem: SupportingTextData,
    currentState: InputShellState,
): InputShellState =
    if (supportingTextList.contains(dateOutOfRangeItem) ||
        supportingTextList.contains(
            incorrectDateFormatItem,
        )
    ) {
        InputShellState.ERROR
    } else {
        currentState
    }

private fun getActionButtonIcon(actionType: DateTimeActionType): ImageVector =
    when (actionType) {
        DateTimeActionType.DATE, DateTimeActionType.DATE_TIME -> Icons.Filled.Event
        DateTimeActionType.TIME -> Icons.Filled.Schedule
    }

@Composable
fun InputDateResetButton(
    state: InputDateTimeState,
    onValueChanged: (TextFieldValue?) -> Unit,
    focusRequester: FocusRequester,
) {
    if (!state.inputTextFieldValue?.text.isNullOrBlank() && state.inputState != InputShellState.DISABLED) {
        IconButton(
            modifier = Modifier.testTag("INPUT_DATE_TIME_RESET_BUTTON").padding(Spacing.Spacing0),
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Icon Button",
                )
            },
            onClick = {
                onValueChanged.invoke(TextFieldValue())
                focusRequester.requestFocus()
            },
        )
    }
}

/**
 * DHIS2 Input Date Time
 * Input field to enter date, time or date&time. It will format content based on given visual
 * transformation.
 * component uses Material 3 [DatePicker] and [TimePicker]
 * input formats supported are mentioned in the date time input ui model documentation.
 * [DatePicker] Input mode  will always follow locale format.
 * @param state: an [InputDateTimeState] with all the parameters for the input
 * @param modifier: optional modifier.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Deprecated("Use with onImeActionClick instead of onNextClicked")
@Composable
fun InputDateTime(
    state: InputDateTimeState,
    onFocusChanged: ((Boolean) -> Unit) = {},
    onValueChanged: (TextFieldValue?) -> Unit,
    onNextClicked: (() -> Unit)?,
    onActionClicked: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    InputDateTime(
        state = state,
        onFocusChanged = onFocusChanged,
        onValueChanged = onValueChanged,
        onImeActionClick = { imeAction ->
            if (imeAction == ImeAction.Next) {
                onNextClicked?.invoke()
            }
        },
        onActionClicked = onActionClicked,
        modifier = modifier,
    )
}

fun getTextColor(
    inputState: InputShellState,
    inputTextFieldValue: TextFieldValue?,
): Color =
    if (inputState != InputShellState.DISABLED && !inputTextFieldValue?.text.isNullOrEmpty()) {
        TextColor.OnSurface
    } else {
        TextColor.OnDisabledSurface
    }

private fun manageOnValueChanged(
    newText: TextFieldValue,
    onValueChanged: (TextFieldValue?) -> Unit,
    actionType: DateTimeActionType,
) {
    val normalizeText = normalizeToGregorian(newText.text)
    val allowedCharacters = RegExValidations.DATE_TIME.regex
    if (allowedCharacters.containsMatchIn(normalizeText) || normalizeText.isBlank()) {
        onValueChanged.invoke(formatUIDateToStored(newText, actionType))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun manageOnValueChangedFromDateTimePicker(
    newValue: TextFieldValue?,
    onValueChanged: (TextFieldValue?) -> Unit,
    actionType: DateTimeActionType,
    datePickerState: DatePickerState,
    timePickerState: TimePickerState,
) {
    if (actionType != DateTimeActionType.DATE_TIME) {
        onValueChanged(
            formatUIDateToStored(
                TextFieldValue(
                    getTime(timePickerState),
                    selection = TextRange(newValue?.text?.length ?: 0),
                ),
                actionType,
            ),
        )
    } else {
        onValueChanged(
            formatUIDateToStored(
                TextFieldValue(
                    getDate(datePickerState.selectedDateMillis) +
                        getTime(
                            timePickerState,
                        ),
                    selection = TextRange(newValue?.text?.length ?: 0),
                ),
                actionType,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerColors(): DatePickerColors =
    DatePickerDefaults.colors(
        selectedDayContainerColor = SurfaceColor.Primary,
        selectedDayContentColor = TextColor.OnPrimary,
        todayDateBorderColor = SurfaceColor.Primary,
        selectedYearContainerColor = SurfaceColor.Primary,
        selectedYearContentColor = TextColor.OnPrimary,
        disabledDayContentColor = TextColor.OnDisabledSurface,
    )

fun formatStringToDate(dateString: String): String =
    if (dateString.length == 8) {
        dateString.substring(0, 2) + "/" +
            dateString.substring(
                2,
                4,
            ) + "/" + dateString.substring(4, 8)
    } else {
        dateString
    }

data class SelectableDates(
    val initialDate: String,
    val endDate: String,
)

enum class DateTimeActionType {
    DATE,
    TIME,
    DATE_TIME,
}
