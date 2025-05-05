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
import androidx.compose.material3.DatePickerState
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
import androidx.compose.ui.focus.FocusManager
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.hisp.dhis.mobile.ui.designsystem.component.internal.convertStringToTextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.internal.formatStoredDateToUI
import org.hisp.dhis.mobile.ui.designsystem.component.internal.formatUIDateToStored
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getDefaultFormat
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getSelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getSupportingTextList
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getTime
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getTimePickerState
import org.hisp.dhis.mobile.ui.designsystem.component.internal.isValidHourFormat
import org.hisp.dhis.mobile.ui.designsystem.component.internal.normalizeToGregorian
import org.hisp.dhis.mobile.ui.designsystem.component.internal.parseDate
import org.hisp.dhis.mobile.ui.designsystem.component.internal.provideDatePickerState
import org.hisp.dhis.mobile.ui.designsystem.component.internal.timePickerColors
import org.hisp.dhis.mobile.ui.designsystem.component.internal.yearIsInRange
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTimeVisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2LightColorScheme
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone
import org.hisp.dhis.mobile.ui.designsystem.component.DatePicker as DHIS2DatePicker
import org.hisp.dhis.mobile.ui.designsystem.component.TimePicker as DHIS2TimePicker

/**
 * DHIS2 Input Date Time
 * Input field to enter date, time or date&time. It will format content based on given visual
 * transformation.
 * component uses Material 3 [DatePicker] and [TimePicker]
 * input formats supported are mentioned in the date time input ui model documentation.
 * [DatePicker] Input mode  will always follow locale format.
 * @param uiModel: an [InputDateTimeModel] with all the parameters for the input
 * @param modifier: optional modifier.
 */

@Suppress("DEPRECATION")
@Deprecated("This component is deprecated and will be removed in the next release. Use InputDateTime instead.")
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
    var dateOutOfRangeText = uiModel.outOfRangeText ?: provideStringResource("date_out_of_range")

    dateOutOfRangeText = "$dateOutOfRangeText (" + formatStringToDate(
        uiModel.selectableDates.initialDate,
    ) + " - " +
        formatStringToDate(uiModel.selectableDates.endDate) + ")"
    val incorrectHourFormatText =
        uiModel.incorrectHourFormatText ?: provideStringResource("wrong_hour_format")
    val incorrectHourFormatItem = SupportingTextData(
        text = incorrectHourFormatText,
        SupportingTextState.ERROR,
    )
    val incorrectDateFormatItem = SupportingTextData(
        text = provideStringResource("incorrect_date_format"),
        SupportingTextState.ERROR,
    )
    val dateOutOfRangeItem = SupportingTextData(
        text = dateOutOfRangeText,
        SupportingTextState.ERROR,
    )
    val supportingTextList =
        getSupportingTextList(
            uiModel,
            dateOutOfRangeItem,
            incorrectHourFormatItem,
            incorrectDateFormatItem,
        )

    InputShell(
        modifier = modifier.testTag("INPUT_DATE_TIME")
            .focusRequester(focusRequester),
        title = uiModel.title,
        state = if (supportingTextList.contains(dateOutOfRangeItem) || supportingTextList.contains(
                incorrectDateFormatItem,
            )
        ) {
            InputShellState.ERROR
        } else {
            uiModel.state
        },
        isRequiredField = uiModel.isRequired,
        onFocusChanged = uiModel.onFocusChanged,
        inputField = {
            if (uiModel.allowsManualInput) {
                BasicTextField(
                    modifier = Modifier
                        .testTag("INPUT_DATE_TIME_TEXT_FIELD")
                        .fillMaxWidth(),
                    inputTextValue = TextFieldValue(
                        uiModel.inputTextFieldValue?.text ?: "",
                        TextRange(uiModel.inputTextFieldValue?.text?.length ?: 0),
                    ),
                    isSingleLine = true,
                    onInputChanged = { newText ->
                        if (newText.text.length > uiModel.visualTransformation.maskLength) {
                            return@BasicTextField
                        }

                        if (allowedCharacters.containsMatchIn(newText.text) || newText.text.isBlank()) {
                            uiModel.onValueChanged.invoke(newText)
                        }
                    },
                    enabled = uiModel.state != InputShellState.DISABLED,
                    state = uiModel.state,
                    keyboardOptions = KeyboardOptions(
                        imeAction = uiModel.imeAction,
                        keyboardType = KeyboardType.Number,
                    ),
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
            if (!uiModel.inputTextFieldValue?.text.isNullOrBlank() && uiModel.state != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_DATE_TIME_RESET_BUTTON")
                        .padding(Spacing.Spacing0),
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
                    focusRequester.requestFocus()
                    if (uiModel.onActionClicked != null) {
                        uiModel.onActionClicked.invoke()
                    } else {
                        if (uiModel.actionType == DateTimeActionType.TIME) {
                            showTimePicker = !showTimePicker
                        } else {
                            showDatePicker = !showDatePicker
                        }
                    }
                },
                enabled = uiModel.state != InputShellState.DISABLED,
            )
        },
        supportingText =
        {
            supportingTextList.forEach { item ->
                SupportingText(
                    item.text,
                    item.state,
                    modifier = Modifier.testTag("INPUT_DATE_TIME_SUPPORTING_TEXT" + item.text),
                )
            }
        },
        legend = {
            uiModel.legendData?.let {
                Legend(uiModel.legendData, Modifier.testTag("INPUT_DATE_TIME_LEGEND"))
            }
        },
        inputStyle = uiModel.inputStyle,
    )
    val datePickerState = provideDatePickerState(uiModel)

    if (showDatePicker) {
        MaterialTheme(
            colorScheme = DHIS2LightColorScheme.copy(
                outlineVariant = Outline.Medium,
            ),
        ) {
            DatePickerDialog(
                modifier = Modifier.testTag("DATE_PICKER"),
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    Button(
                        enabled = true,
                        ButtonStyle.TEXT,
                        ColorStyle.DEFAULT,
                        uiModel.acceptText ?: provideStringResource("ok"),
                    ) {
                        showDatePicker = false
                        if (uiModel.actionType != DateTimeActionType.DATE_TIME) {
                            datePickerState.selectedDateMillis?.let {
                                uiModel.onValueChanged(
                                    TextFieldValue(
                                        getDate(it, uiModel.format),
                                        selection = TextRange(
                                            uiModel.inputTextFieldValue?.text?.length ?: 0,
                                        ),
                                    ),
                                )
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
                        uiModel.cancelText ?: provideStringResource("cancel"),

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
                            modifier = Modifier.padding(
                                start = Spacing.Spacing24,
                                top = Spacing.Spacing24,
                            ),
                        )
                    },
                    state = datePickerState,
                    showModeToggle = true,
                    modifier = Modifier.padding(Spacing.Spacing0),
                )
            }
        }
    }

    if (showTimePicker) {
        var timePickerState = rememberTimePickerState(0, 0, is24Hour = uiModel.is24hourFormat)
        if (!uiModel.inputTextFieldValue?.text.isNullOrEmpty() && uiModel.actionType == DateTimeActionType.TIME && isValidHourFormat(
                uiModel.inputTextFieldValue?.text ?: "",
            )
        ) {
            timePickerState = rememberTimePickerState(
                initialHour = uiModel.inputTextFieldValue?.text?.substring(0, 2)!!
                    .toInt(),
                uiModel.inputTextFieldValue.text.substring(2, 4).toInt(),
                is24Hour = uiModel.is24hourFormat,
            )
        } else {
            if (uiModel.inputTextFieldValue?.text?.length == 12 && isValidHourFormat(
                    uiModel.inputTextFieldValue.text.substring(
                        8,
                        12,
                    ),
                )
            ) {
                timePickerState = rememberTimePickerState(
                    initialHour = uiModel.inputTextFieldValue.text.substring(
                        uiModel.inputTextFieldValue.text.length - 4,
                        uiModel.inputTextFieldValue.text.length - 2,
                    )
                        .toInt(),
                    uiModel.inputTextFieldValue.text.substring(
                        uiModel.inputTextFieldValue.text.length - 2,
                        uiModel.inputTextFieldValue.text.length,
                    ).toInt(),
                    is24Hour = uiModel.is24hourFormat,
                )
            }
        }
        Dialog(
            onDismissRequest = { showDatePicker = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true,
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(
                    color = SurfaceColor.Container,
                    shape = RoundedCornerShape(Radius.L),
                ).testTag("TIME_PICKER")
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
                        uiModel.cancelText ?: provideStringResource("cancel"),

                    ) {
                        showTimePicker = false
                    }
                    Button(
                        enabled = true,
                        ButtonStyle.TEXT,
                        ColorStyle.DEFAULT,
                        uiModel.acceptText ?: provideStringResource("ok"),
                    ) {
                        showTimePicker = false
                        if (uiModel.actionType != DateTimeActionType.DATE_TIME) {
                            uiModel.onValueChanged(
                                TextFieldValue(
                                    getTime(timePickerState),
                                    selection = TextRange(
                                        uiModel.inputTextFieldValue?.text?.length ?: 0,
                                    ),
                                ),
                            )
                        } else {
                            uiModel.onValueChanged(
                                TextFieldValue(
                                    getDate(datePickerState.selectedDateMillis) + getTime(
                                        timePickerState,
                                    ),
                                    selection = TextRange(
                                        uiModel.inputTextFieldValue?.text?.length ?: 0,
                                    ),
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getInputState(
    supportingTextList: List<SupportingTextData>,
    dateOutOfRangeItem: SupportingTextData,
    incorrectDateFormatItem: SupportingTextData,
    currentState: InputShellState,
): InputShellState {
    return if (supportingTextList.contains(dateOutOfRangeItem) || supportingTextList.contains(
            incorrectDateFormatItem,
        )
    ) {
        InputShellState.ERROR
    } else {
        currentState
    }
}

private fun getActionButtonIcon(actionType: DateTimeActionType): ImageVector {
    return when (actionType) {
        DateTimeActionType.DATE, DateTimeActionType.DATE_TIME -> Icons.Filled.Event
        DateTimeActionType.TIME -> Icons.Filled.Schedule
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
@Composable
fun InputDateTime(
    state: InputDateTimeState,
    onFocusChanged: ((Boolean) -> Unit) = {},
    onValueChanged: (TextFieldValue?) -> Unit,
    onNextClicked: (() -> Unit)? = null,
    onActionClicked: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val uiData = state.uiData

    val uiValue = remember(state.inputTextFieldValue) {
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

    dateOutOfRangeText = "$dateOutOfRangeText (" + formatStringToDate(
        uiData.selectableDates.initialDate,
    ) + " - " +
        formatStringToDate(uiData.selectableDates.endDate) + ")"
    val incorrectHourFormat =
        uiData.incorrectHourFormatText ?: provideStringResource("wrong_hour_format")
    val incorrectHourFormatItem = SupportingTextData(
        text = incorrectHourFormat,
        SupportingTextState.ERROR,
    )
    val incorrectDateFormatItem = SupportingTextData(
        text = provideStringResource("incorrect_date_format"),
        SupportingTextState.ERROR,
    )
    val dateOutOfRangeItem = SupportingTextData(
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
        modifier = modifier.testTag("INPUT_DATE_TIME")
            .focusRequester(focusRequester),
        title = uiData.title,
        state = getInputState(
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
                    modifier = Modifier
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
                    keyboardOptions = KeyboardOptions(
                        imeAction = uiData.imeAction,
                        keyboardType = KeyboardType.Number,
                    ),
                    visualTransformation = uiData.visualTransformation,
                    onNextClicked = {
                        manageOnNext(focusManager, onNextClicked)
                    },
                )
            } else {
                Box {
                    Text(
                        modifier = Modifier
                            .testTag("INPUT_DATE_TIME_TEXT")
                            .fillMaxWidth(),
                        text = uiData.visualTransformation.filter(AnnotatedString(uiValue.text)).text,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = getTextColor(state.inputState, state.inputTextFieldValue),
                        ),
                    )
                    Box(
                        modifier = Modifier
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
                modifier = Modifier.testTag("INPUT_DATE_TIME_ACTION_BUTTON")
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
        supportingText =
        {
            supportingTextList.forEach { item ->
                SupportingText(
                    item.text,
                    item.state,
                    modifier = Modifier.testTag("INPUT_DATE_TIME_SUPPORTING_TEXT" + item.text),
                )
            }
        },
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
                                selection = TextRange(
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

fun getTextColor(inputState: InputShellState, inputTextFieldValue: TextFieldValue?): Color {
    return if (inputState != InputShellState.DISABLED && !inputTextFieldValue?.text.isNullOrEmpty()) {
        TextColor.OnSurface
    } else {
        TextColor.OnDisabledSurface
    }
}

fun manageOnNext(focusManager: FocusManager, onNextClicked: (() -> Unit)?) {
    if (onNextClicked != null) {
        onNextClicked.invoke()
    } else {
        focusManager.moveFocus(FocusDirection.Down)
    }
}

private fun manageOnValueChanged(
    newText: TextFieldValue,
    onValueChanged: (TextFieldValue?) -> Unit,
    actionType: DateTimeActionType,
) {
    val normalizedText = normalizeToGregorian(newText.text)
    val normalizedTextField = newText.copy(
        text = normalizedText,
        selection = TextRange(normalizedText.length),
    )
    val allowedCharacters = RegExValidations.DATE_TIME.regex
    if (allowedCharacters.containsMatchIn(normalizedTextField.text) || normalizedTextField.text.isBlank()) {
        onValueChanged.invoke(formatUIDateToStored(normalizedTextField, actionType))
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
                    getDate(datePickerState.selectedDateMillis) + getTime(
                        timePickerState,
                    ),
                    selection = TextRange(newValue?.text?.length ?: 0),
                ),
                actionType,
            ),
        )
    }
}

@Suppress("deprecation")
@Deprecated(
    "This function is deprecated and will be removed in the next release.",
    replaceWith = ReplaceWith("provideDatePickerState(state: InputDateTimeState, data: InputDateTimeData)"),
)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun provideDatePickerState(uiModel: InputDateTimeModel): DatePickerState {
    return uiModel.inputTextFieldValue?.text?.takeIf {
        it.isNotEmpty() &&
            yearIsInRange(it, getDefaultFormat(uiModel.actionType), uiModel.yearRange)
    }?.let {
        rememberDatePickerState(
            initialSelectedDateMillis = parseStringDateToMillis(
                dateString = it,
                pattern = getDefaultFormat(uiModel.actionType),
            ),
            yearRange = uiModel.yearRange,
            selectableDates = getSelectableDates(uiModel),
        )
    } ?: rememberDatePickerState(selectableDates = getSelectableDates(uiModel))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun datePickerColors(): DatePickerColors {
    return DatePickerDefaults.colors(
        selectedDayContainerColor = SurfaceColor.Primary,
        selectedDayContentColor = TextColor.OnPrimary,
        todayDateBorderColor = SurfaceColor.Primary,
        selectedYearContainerColor = SurfaceColor.Primary,
        selectedYearContentColor = TextColor.OnPrimary,
        disabledDayContentColor = TextColor.OnDisabledSurface,
    )
}

@Deprecated(
    "This function is deprecated and will be removed in the near future",
    replaceWith = ReplaceWith("parseStringDateToMillis(dateString: String, pattern: String)"),
)
private fun parseStringDateToMillis(dateString: String, pattern: String = "ddMMyyyy"): Long {
    val cal = Calendar.getInstance()
    return dateString.parseDate(pattern)?.let {
        cal.time = it
        cal.timeInMillis
    } ?: 0L
}

internal fun getDate(milliSeconds: Long?, format: String = "ddMMyyyy"): String {
    val cal = Calendar.getInstance()
    val currentTimeZone: TimeZone = cal.getTimeZone()
    val currentDt: Calendar = GregorianCalendar(currentTimeZone, Locale.getDefault())
    var gmtOffset: Int = currentTimeZone.getOffset(
        currentDt[Calendar.ERA],
        currentDt[Calendar.YEAR],
        currentDt[Calendar.MONTH],
        currentDt[Calendar.DAY_OF_MONTH],
        currentDt[Calendar.DAY_OF_WEEK],
        currentDt[Calendar.MILLISECOND],
    )
    gmtOffset /= (60 * 60 * 1000)
    cal.add(Calendar.HOUR_OF_DAY, +gmtOffset)
    return if (milliSeconds != null) {
        cal.timeInMillis = milliSeconds
        val formater = SimpleDateFormat(format)
        if (gmtOffset < 0) {
            var day = formater.format(cal.time).substring(0, 2).toInt()
            day += 1
            formater.format(cal.time).replaceRange(0, 2, String.format("%02d", day))
        } else {
            formater.format(cal.time)
        }
    } else {
        ""
    }
}

fun formatStringToDate(dateString: String): String {
    return if (dateString.length == 8) {
        dateString.substring(0, 2) + "/" + dateString.substring(
            2,
            4,
        ) + "/" + dateString.substring(4, 8)
    } else {
        dateString
    }
}

/**
 * UiModel used for [InputDateTime]
 * @param title : Label of the component.
 * @param inputTextFieldValue: Input of the component in the format of ddMMyyyy/HHMM/ddMMyyyyHHMM.
 * @param allowsManualInput: if true the user can utilize the keyboard to enter the date.
 * @param actionType: Type of action icon to display. [DateTimeActionType.DATE_TIME], [DateTimeActionType.DATE], [DateTimeActionType.TIME]
 * @param onActionClicked: Callback to override the action when the calendar icon is clicked.
 * material3 DatePicker or Timepicker will be used calling onValueChanged after selecting a date or time.
 * @param state: [InputShellState]
 * @param legendData: [LegendData]
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param onNextClicked: gives access to the on next callback.
 * @param onFocusChanged: gives access to the on Focus changed callback.
 * @param isRequired: Mark this input as marked.
 * @param visualTransformation: Pass a visual transformation to format the date input visually. By default uses [DateTransformation].
 * @param format: the date value format, current supported formats are -> ddMMyyyy and MMddyyyy.
 * @param is24hourFormat: only applies if input is of type DateTime or Time, manages whether the
 * TimePicker is on 24 hour format or not.
 * @param inputStyle: gives access to [InputShell] style parameters.
 * @param selectableDates: a [SelectableDates] object that takes in two dates as strings in ddMMyyyy format and
 * will not allow selection for the date picker, default value will be -> SelectableDates("01011940","12312300").
 * @param acceptText: manages the [DatePicker] confirm button text.
 * @param cancelText: manages the [DatePicker] cancel button text.
 * @param yearRange: manages the year range to be shown.
 * @param onValueChanged: Callback to receive changes in the input in the following formats:
 * <ddMMyyyy>, <HHMM>,  <ddMMyyyyHHMM>.
 * @param imeAction: the keyboard [ImeAction]
 * @param incorrectHourFormatText: error text to be used if hour format is incorrect.
 * @param outOfRangeText: error text to be used if date is out of range.
 */
data class InputDateTimeModel(
    val title: String,
    val inputTextFieldValue: TextFieldValue?,
    val actionType: DateTimeActionType = DateTimeActionType.DATE_TIME,
    val allowsManualInput: Boolean = true,
    val state: InputShellState = InputShellState.UNFOCUSED,
    val legendData: LegendData? = null,
    val supportingText: List<SupportingTextData>? = null,
    val onActionClicked: (() -> Unit)? = null,
    val onNextClicked: (() -> Unit)? = null,
    val isRequired: Boolean = false,
    val imeAction: ImeAction = ImeAction.Next,
    val visualTransformation: DateTimeVisualTransformation = DateTransformation(),
    val format: String = "ddMMyyyy",
    val onFocusChanged: ((Boolean) -> Unit) = {},
    val onValueChanged: (TextFieldValue?) -> Unit,
    val is24hourFormat: Boolean = false,
    val inputStyle: InputStyle = InputStyle.DataInputStyle(),
    val acceptText: String? = null,
    val cancelText: String? = null,
    val selectableDates: SelectableDates = SelectableDates("01011940", "12312300"),
    val yearRange: IntRange = IntRange(1970, 2070),
    val outOfRangeText: String? = null,
    val incorrectHourFormatText: String? = null,
)

data class SelectableDates(
    val initialDate: String,
    val endDate: String,
)

enum class DateTimeActionType {
    DATE, TIME, DATE_TIME
}
