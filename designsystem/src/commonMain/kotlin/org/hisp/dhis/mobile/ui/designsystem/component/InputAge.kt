package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.DialogProperties
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType.Age
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType.DateOfBirth
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType.None
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues.YEARS
import org.hisp.dhis.mobile.ui.designsystem.component.internal.dateIsInRange
import org.hisp.dhis.mobile.ui.designsystem.component.internal.formatStoredDateToUI
import org.hisp.dhis.mobile.ui.designsystem.component.internal.formatUIDateToStored
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getDateSupportingText
import org.hisp.dhis.mobile.ui.designsystem.component.internal.getSelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.internal.isValidDate
import org.hisp.dhis.mobile.ui.designsystem.component.internal.parseStringDateToMillis
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation.Companion.DATE_MASK
import org.hisp.dhis.mobile.ui.designsystem.component.model.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputAgeState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2LightColorScheme
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * DHIS2 Input Age component wraps DHIS2 [InputShell].
 * Users can enter their age either through Material 3's [DatePicker]
 * or through a Unit Interval selector that can be  [TimeUnitValues.YEARS],
 * [TimeUnitValues.MONTHS] or [TimeUnitValues.DAYS].
 * @param uiModel: data class [InputAgeModel] with all parameters for component.
 * @param modifier: optional modifier.
 */
@Suppress("DEPRECATION")
@Deprecated("This component is deprecated and will be removed in the next release. Use InputAge instead.")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputAge(
    uiModel: InputAgeModel,
    modifier: Modifier = Modifier,
) {
    val maxAgeCharLimit = 3
    val allowedCharacters = RegExValidations.DATE_TIME.regex
    var showDatePicker by rememberSaveable { mutableStateOf(false) }

    val helperText = remember(uiModel.inputType) {
        when (uiModel.inputType) {
            None, is DateOfBirth -> null
            is Age -> uiModel.inputType.unit.value
        }
    }
    val helperStyle = remember(uiModel.inputType) {
        when (uiModel.inputType) {
            None -> HelperStyle.NONE
            is DateOfBirth -> HelperStyle.WITH_DATE_OF_BIRTH_HELPER
            is Age -> HelperStyle.WITH_HELPER_AFTER
        }
    }
    val selectableDates = uiModel.selectableDates ?: SelectableDates(
        MIN_DATE,
        SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().time),
    )

    val focusRequester = remember { FocusRequester() }
    val datePickerState = rememberDatePickerState(
        selectableDates = object : androidx.compose.material3.SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return dateIsInRange(utcTimeMillis, selectableDates)
            }
        },
    )

    val calendarButton: (@Composable () -> Unit)? = if (uiModel.inputType is DateOfBirth) {
        @Composable {
            SquareIconButton(
                modifier = Modifier.testTag("INPUT_AGE_OPEN_CALENDAR_BUTTON"),
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Event,
                        contentDescription = null,
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    showDatePicker = !showDatePicker
                },
                enabled = uiModel.state != InputShellState.DISABLED,
            )
        }
    } else {
        null
    }

    var previousInputType by remember { mutableStateOf(uiModel.inputType) }
    LaunchedEffect(uiModel.inputType) {
        when {
            previousInputType == None && (uiModel.inputType is DateOfBirth || uiModel.inputType is Age) -> {
                focusRequester.requestFocus()
            }

            else -> {
                // no-op
            }
        }

        if (previousInputType != uiModel.inputType) {
            previousInputType = uiModel.inputType
        }
    }

    val supportingText = provideSupportingText(uiModel, selectableDates)

    InputShell(
        modifier = modifier.testTag("INPUT_AGE").focusRequester(focusRequester),
        title = uiModel.title,
        state = when (supportingText) {
            uiModel.supportingText -> uiModel.state
            else -> InputShellState.ERROR
        },
        isRequiredField = uiModel.isRequired,
        inputField = {
            when (uiModel.inputType) {
                None -> {
                    TextButtonSelector(
                        modifier = Modifier.testTag("INPUT_AGE_MODE_SELECTOR"),
                        firstOptionText = uiModel.dateOfBirthLabel ?: provideStringResource("date_birth"),
                        onClickFirstOption = {
                            uiModel.onValueChanged.invoke(DateOfBirth.EMPTY)
                        },
                        middleText = uiModel.orLabel ?: provideStringResource("or"),
                        secondOptionText = uiModel.ageLabel ?: provideStringResource("age"),
                        onClickSecondOption = {
                            uiModel.onValueChanged.invoke(Age.EMPTY)
                        },
                        enabled = uiModel.state != InputShellState.DISABLED,
                    )
                }

                is DateOfBirth, is Age -> {
                    BasicTextField(
                        modifier = Modifier
                            .testTag("INPUT_AGE_TEXT_FIELD")
                            .fillMaxWidth(),
                        inputTextValue = getTextFieldValue(uiModel.inputType),
                        helper = if (helperText != null) provideStringResource(helperText).lowercase() else null,
                        isSingleLine = true,
                        helperStyle = helperStyle,
                        onInputChanged = { newText ->
                            if (newText.text.length > maxAgeCharLimit && uiModel.inputType is Age) {
                                return@BasicTextField
                            }

                            @Suppress("KotlinConstantConditions")
                            val newInputType: AgeInputType = when (uiModel.inputType) {
                                is Age -> uiModel.inputType.copy(value = newText)
                                is DateOfBirth -> updateDateOfBirth(uiModel.inputType, newText)
                                None -> None
                            }

                            if (allowedCharacters.containsMatchIn(newText.text) || newText.text.isBlank()) {
                                uiModel.onValueChanged.invoke(newInputType)
                            }
                        },
                        enabled = uiModel.state != InputShellState.DISABLED,
                        state = uiModel.state,
                        keyboardOptions = KeyboardOptions(imeAction = uiModel.imeAction, keyboardType = KeyboardType.Number),
                        onNextClicked = {
                        },
                    )
                }
            }
        },
        primaryButton = {
            if (uiModel.inputType != None && uiModel.state != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_AGE_RESET_BUTTON").padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        uiModel.onValueChanged.invoke(None)
                    },
                )
            }
        },
        secondaryButton = calendarButton,
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_AGE_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            if (uiModel.inputType is Age) {
                TimeUnitSelector(
                    modifier = Modifier.fillMaxWidth()
                        .testTag("INPUT_AGE_TIME_UNIT_SELECTOR"),
                    orientation = Orientation.HORIZONTAL,
                    optionSelected = YEARS,
                    enabled = uiModel.state != InputShellState.DISABLED,
                    onClick = { timeUnit ->
                        uiModel.onValueChanged.invoke(uiModel.inputType.copy(unit = timeUnit))
                    },
                )
            }

            uiModel.legendData?.let {
                Legend(uiModel.legendData, Modifier.testTag("INPUT_AGE_LEGEND"))
            }
        },
        inputStyle = uiModel.inputStyle,
    )

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
                        if (uiModel.inputType is DateOfBirth) {
                            datePickerState.selectedDateMillis?.let {
                                val newInputType: AgeInputType =
                                    updateDateOfBirth(uiModel.inputType, TextFieldValue(getDate(it), TextRange(getDate(it).length)))
                                uiModel.onValueChanged.invoke(newInputType)
                            }
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
                            modifier = Modifier.padding(start = Spacing.Spacing24, top = Spacing.Spacing24),
                        )
                    },
                    state = datePickerState,
                    showModeToggle = true,
                    modifier = Modifier.padding(Spacing.Spacing0),
                )
            }
        }
    }
}

/**
 * DHIS2 Input Age
 * Input field to enter age. It will format content based on given visual
 * transformation.
 * component uses Material 3 [DatePicker]
 * input formats supported are mentioned in the age input ui model documentation.
 * [DatePicker] Input mode  will always follow locale format.
 * @param state: an [InputAgeState] with all the parameters for the input
 * @param modifier: optional modifier.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputAge(
    state: InputAgeState,
    onValueChanged: (AgeInputType?) -> Unit,
    onNextClicked: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val uiData = state.uiData
    val inputType = state.inputType
    val uiValue = remember(getTextFieldValue(inputType)) { formatStoredDateToUI(getTextFieldValue(inputType), DateTimeActionType.DATE) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val maxAgeCharLimit = 3
    var showDatePicker by rememberSaveable { mutableStateOf(false) }

    val helperText = remember(inputType) {
        if (inputType is Age) {
            inputType.unit.value
        } else {
            null
        }
    }
    val helperStyle = remember(inputType) {
        when (inputType) {
            None -> HelperStyle.NONE
            is DateOfBirth -> HelperStyle.WITH_DATE_OF_BIRTH_HELPER
            is Age -> HelperStyle.WITH_HELPER_AFTER
        }
    }
    val selectableDates = uiData.selectableDates ?: SelectableDates(
        MIN_DATE,
        SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().time),
    )

    val datePickerState = rememberDatePickerState(
        selectableDates = getSelectableDates(selectableDates),
    )

    val calendarButton: (@Composable () -> Unit)? = if (inputType is DateOfBirth) {
        @Composable {
            SquareIconButton(
                modifier = Modifier.testTag("INPUT_AGE_OPEN_CALENDAR_BUTTON"),
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Event,
                        contentDescription = null,
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    showDatePicker = !showDatePicker
                },
                enabled = state.inputState != InputShellState.DISABLED,
            )
        }
    } else {
        null
    }

    var previousInputType by remember { mutableStateOf(inputType) }
    LaunchedEffect(inputType) {
        when {
            previousInputType == None && (inputType is DateOfBirth || inputType is Age) -> {
                focusRequester.requestFocus()
            }

            else -> {
                // no-op
            }
        }

        if (previousInputType != inputType) {
            previousInputType = inputType
        }
    }

    val dateOutOfRangeText = "${provideStringResource("date_out_of_range")} (" +
        formatStringToDate(selectableDates.initialDate) + " - " +
        formatStringToDate(selectableDates.endDate) + ")"
    val dateOutOfRangeItem = SupportingTextData(
        text = dateOutOfRangeText,
        SupportingTextState.ERROR,
    )
    val incorrectDateFormatItem = SupportingTextData(
        text = provideStringResource("incorrect_date_format"),
        SupportingTextState.ERROR,
    )

    val supportingTextList = provideSupportingText(
        inputType,
        uiValue,
        state.supportingText,
        dateOutOfRangeItem,
        incorrectDateFormatItem,
        selectableDates,
    )

    InputShell(
        modifier = modifier.testTag("INPUT_AGE").focusRequester(focusRequester),
        title = uiData.title,
        state = getInputState(supportingTextList, dateOutOfRangeItem, incorrectDateFormatItem, state.inputState),
        isRequiredField = uiData.isRequired,
        inputField = {
            when (inputType) {
                None -> {
                    TextButtonSelector(
                        modifier = Modifier.testTag("INPUT_AGE_MODE_SELECTOR"),
                        firstOptionText = uiData.dateOfBirthLabel ?: provideStringResource("date_birth"),
                        onClickFirstOption = {
                            onValueChanged.invoke(DateOfBirth.EMPTY)
                        },
                        middleText = uiData.orLabel ?: provideStringResource("or"),
                        secondOptionText = uiData.ageLabel ?: provideStringResource("age"),
                        onClickSecondOption = {
                            onValueChanged.invoke(Age.EMPTY)
                        },
                        enabled = state.inputState != InputShellState.DISABLED,
                    )
                }

                is DateOfBirth, is Age -> {
                    BasicTextField(
                        modifier = Modifier
                            .testTag("INPUT_AGE_TEXT_FIELD")
                            .fillMaxWidth(),
                        inputTextValue = uiValue,
                        helper = if (helperText != null) provideStringResource(helperText).lowercase() else null,
                        isSingleLine = true,
                        helperStyle = helperStyle,
                        onInputChanged = { newText ->
                            if ((inputType is Age && newText.text.length > maxAgeCharLimit) ||
                                (inputType is DateOfBirth && newText.text.length > DATE_MASK.length)
                            ) {
                                return@BasicTextField
                            }
                            manageOnValueChanged(newText, inputType, onValueChanged)
                        },
                        enabled = state.inputState != InputShellState.DISABLED,
                        state = state.inputState,
                        keyboardOptions = KeyboardOptions(imeAction = uiData.imeAction, keyboardType = KeyboardType.Number),
                        onNextClicked = {
                            if (onNextClicked != null) {
                                onNextClicked.invoke()
                            } else {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        },
                    )
                }
            }
        },
        primaryButton = {
            if (inputType != None && state.inputState != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_AGE_RESET_BUTTON").padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        onValueChanged.invoke(None)
                    },
                )
            }
        },
        secondaryButton = calendarButton,
        supportingText = {
            supportingTextList.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_AGE_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            if (inputType is Age) {
                TimeUnitSelector(
                    modifier = Modifier.fillMaxWidth()
                        .testTag("INPUT_AGE_TIME_UNIT_SELECTOR"),
                    orientation = Orientation.HORIZONTAL,
                    optionSelected = YEARS,
                    enabled = state.inputState != InputShellState.DISABLED,
                    onClick = { timeUnit ->
                        onValueChanged.invoke(inputType.copy(unit = timeUnit))
                    },
                )
            }

            state.legendData?.let {
                Legend(it, Modifier.testTag("INPUT_AGE_LEGEND"))
            }
        },
        inputStyle = uiData.inputStyle,
    )

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
                        uiData.acceptText ?: provideStringResource("ok"),
                    ) {
                        showDatePicker = false
                        if (inputType is DateOfBirth) {
                            datePickerState.selectedDateMillis?.let {
                                val newInputType: AgeInputType = DateOfBirth(
                                    formatUIDateToStored(TextFieldValue(getDate(it), TextRange(getDate(it).length)), DateTimeActionType.DATE),
                                )
                                onValueChanged.invoke(newInputType)
                            }
                        }
                    }
                },
                colors = datePickerColors(),
                dismissButton = {
                    Button(
                        enabled = true,
                        ButtonStyle.TEXT,
                        ColorStyle.DEFAULT,
                        uiData.cancelText ?: provideStringResource("cancel"),
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
                            text = uiData.title,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = Spacing.Spacing24, top = Spacing.Spacing24),
                        )
                    },
                    state = datePickerState,
                    showModeToggle = true,
                    modifier = Modifier.padding(Spacing.Spacing0),
                )
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
    return if (supportingTextList.contains(dateOutOfRangeItem) || supportingTextList.contains(incorrectDateFormatItem)) InputShellState.ERROR else currentState
}

@Composable
private fun provideSupportingText(
    inputType: AgeInputType,
    uiValue: TextFieldValue,
    supportingText: List<SupportingTextData>?,
    dateOutOfRangeItem: SupportingTextData,
    incorrectDateFormatItem: SupportingTextData,
    selectableDates: SelectableDates,
): List<SupportingTextData> {
    val supportingTextList = supportingText?.toMutableList() ?: mutableListOf()

    return (inputType as? DateOfBirth)?.value?.let {
        getDateSupportingText(
            uiValue = uiValue,
            selectableDates = selectableDates,
            actionType = DateTimeActionType.DATE,
            yearRange = IntRange(MIN_YEAR, MAX_YEAR),
            supportingTextList = supportingTextList,
            dateOutOfRangeItem = dateOutOfRangeItem,
            incorrectDateFormatItem = incorrectDateFormatItem,
        )
    } ?: supportingTextList
}

@Suppress("DEPRECATION")
@Deprecated("This component is deprecated and will be removed in the next release. Use InputDateTime instead.")
@Composable
private fun provideSupportingText(
    uiModel: InputAgeModel,
    selectableDates: SelectableDates,
): List<SupportingTextData>? =
    (uiModel.inputType as? DateOfBirth)?.value?.text?.let {
        if (
            it.length == DATE_FORMAT.length && (!isValidDate(it) || !dateIsInRange(parseStringDateToMillis(it), selectableDates))
        ) {
            val supportingTextErrorList: MutableList<SupportingTextData> = mutableListOf()
            if (!isValidDate(it)) {
                val incorrectFormatText = provideStringResource("incorrect_date_format")
                supportingTextErrorList.add(
                    SupportingTextData(
                        text = incorrectFormatText,
                        SupportingTextState.ERROR,
                    ),
                )
            } else if (!dateIsInRange(parseStringDateToMillis(it), selectableDates)) {
                val dateOutOfRangeText = "${provideStringResource("date_out_of_range")} (" +
                    formatStringToDate(selectableDates.initialDate) + " - " +
                    formatStringToDate(selectableDates.endDate) + ")"
                supportingTextErrorList.add(
                    SupportingTextData(
                        text = dateOutOfRangeText,
                        SupportingTextState.ERROR,
                    ),
                )
            }
            supportingTextErrorList.plus(uiModel.supportingText ?: listOf()).toList()
        } else {
            uiModel.supportingText
        }
    } ?: uiModel.supportingText

private fun manageOnValueChanged(newText: TextFieldValue, inputType: AgeInputType, onValueChanged: (AgeInputType?) -> Unit) {
    val allowedCharacters = RegExValidations.DATE_TIME.regex
    if (allowedCharacters.containsMatchIn(newText.text) || newText.text.isBlank()) {
        when (inputType) {
            is Age -> onValueChanged.invoke((inputType as? Age)?.copy(value = newText))
            is DateOfBirth -> onValueChanged.invoke(DateOfBirth(formatUIDateToStored(newText, DateTimeActionType.DATE)))
            None -> onValueChanged.invoke(None)
        }
    }
}

private fun transformInputText(inputType: AgeInputType): String {
    return when (inputType) {
        is Age -> inputType.value.text
        is DateOfBirth -> inputType.value.text
        None -> ""
    }
}

private fun getTextFieldValue(inputType: AgeInputType): TextFieldValue {
    return when (inputType) {
        is Age -> TextFieldValue(transformInputText(inputType), inputType.value.selection)
        is DateOfBirth -> TextFieldValue(transformInputText(inputType), inputType.value.selection)
        None -> TextFieldValue()
    }
}

private fun updateDateOfBirth(inputType: DateOfBirth, newText: TextFieldValue): AgeInputType {
    return if (newText.text.length <= DATE_MASK.length) {
        inputType.copy(value = newText)
    } else {
        inputType
    }
}

internal const val MIN_DATE = "10111901"
internal const val MIN_YEAR = 1901
internal val MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR)
internal const val DATE_FORMAT = "ddMMYYYY"

sealed interface AgeInputType {
    data object None : AgeInputType

    data class DateOfBirth(val value: TextFieldValue) : AgeInputType {
        companion object {
            val EMPTY = DateOfBirth(TextFieldValue())
        }
    }

    data class Age(val value: TextFieldValue, val unit: TimeUnitValues) : AgeInputType {
        companion object {
            val EMPTY = Age(TextFieldValue(), YEARS)
        }
    }
}

/**
 * Data model used for DHIS2  [InputAge] component.
 * @param title: Label of the component.
 * @param inputType: The type of input :
 *  [None] : default,
 *  [DateOfBirth] : In ddmmyyyy format,
 *  [Age]: Age value with appropriate time unit
 * @param inputStyle: Input shell style.
 * @param state: [InputShellState]
 * @param legendData: [LegendData]
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param isRequired: Mark this input as marked.
 * @param imeAction: keyboard [ImeAction].
 * @param onNextClicked: call back to on next event.
 * @param dateOfBirthLabel: text for the the date of birth selector.
 * @param orLabel: text for middle label.
 * @param ageLabel: text for age selector.
 * @param acceptText: text for date picker accept button.
 * @param cancelText: text for date picker cancel button.
 * @param onValueChanged: Callback to receive changes in the input.
 * @param selectableDates: allowed dates for date picker.
 */

data class InputAgeModel(
    val title: String,
    val inputType: AgeInputType = None,
    val inputStyle: InputStyle = InputStyle.DataInputStyle(),
    val state: InputShellState = InputShellState.UNFOCUSED,
    val legendData: LegendData? = null,
    val supportingText: List<SupportingTextData>? = null,
    val isRequired: Boolean = false,
    val imeAction: ImeAction = ImeAction.Next,
    val onNextClicked: (() -> Unit)? = null,
    val dateOfBirthLabel: String? = null,
    val orLabel: String? = null,
    val ageLabel: String? = null,
    val acceptText: String? = null,
    val cancelText: String? = null,
    val onValueChanged: (AgeInputType) -> Unit,
    val selectableDates: SelectableDates? = null,
)
