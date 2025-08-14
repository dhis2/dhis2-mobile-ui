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

    val helperText =
        remember(inputType) {
            if (inputType is Age) {
                inputType.unit.value
            } else {
                null
            }
        }
    val helperStyle =
        remember(inputType) {
            when (inputType) {
                None -> HelperStyle.NONE
                is DateOfBirth -> HelperStyle.WITH_DATE_OF_BIRTH_HELPER
                is Age -> HelperStyle.WITH_HELPER_AFTER
            }
        }
    val selectableDates =
        uiData.selectableDates ?: SelectableDates(
            MIN_DATE,
            SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().time),
        )

    val datePickerState =
        uiValue.text
            .takeIf {
                it.isNotEmpty() && isValidDate(it) && dateIsInRange(parseStringDateToMillis(it), selectableDates)
            }?.let {
                rememberDatePickerState(
                    initialSelectedDateMillis = parseStringDateToMillis(it),
                    selectableDates = getSelectableDates(selectableDates),
                )
            } ?: rememberDatePickerState(selectableDates = getSelectableDates(selectableDates))

    val calendarButton: (@Composable () -> Unit)? =
        if (inputType is DateOfBirth) {
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

    val dateOutOfRangeText =
        "${provideStringResource("date_out_of_range")} (" +
            formatStringToDate(selectableDates.initialDate) + " - " +
            formatStringToDate(selectableDates.endDate) + ")"
    val dateOutOfRangeItem =
        SupportingTextData(
            text = dateOutOfRangeText,
            SupportingTextState.ERROR,
        )
    val incorrectDateFormatItem =
        SupportingTextData(
            text = provideStringResource("incorrect_date_format"),
            SupportingTextState.ERROR,
        )

    val supportingTextList =
        provideSupportingText(
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
                        modifier =
                            Modifier
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
                    modifier =
                        Modifier
                            .fillMaxWidth()
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
            colorScheme =
                DHIS2LightColorScheme.copy(
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
                                val newInputType: AgeInputType =
                                    DateOfBirth(
                                        formatUIDateToStored(
                                            TextFieldValue(getDate(it), TextRange(getDate(it).length)),
                                            DateTimeActionType.DATE,
                                        ),
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
                properties =
                    DialogProperties(
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
): InputShellState =
    if (supportingTextList.contains(dateOutOfRangeItem) ||
        supportingTextList.contains(incorrectDateFormatItem)
    ) {
        InputShellState.ERROR
    } else {
        currentState
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

private fun manageOnValueChanged(
    newText: TextFieldValue,
    inputType: AgeInputType,
    onValueChanged: (AgeInputType?) -> Unit,
) {
    val allowedCharacters = RegExValidations.DATE_TIME.regex
    if (allowedCharacters.containsMatchIn(newText.text) || newText.text.isBlank()) {
        when (inputType) {
            is Age -> onValueChanged.invoke((inputType as? Age)?.copy(value = newText))
            is DateOfBirth -> onValueChanged.invoke(DateOfBirth(formatUIDateToStored(newText, DateTimeActionType.DATE)))
            None -> onValueChanged.invoke(None)
        }
    }
}

private fun transformInputText(inputType: AgeInputType): String =
    when (inputType) {
        is Age -> inputType.value.text
        is DateOfBirth -> inputType.value.text
        None -> ""
    }

private fun getTextFieldValue(inputType: AgeInputType): TextFieldValue =
    when (inputType) {
        is Age -> TextFieldValue(transformInputText(inputType), inputType.value.selection)
        is DateOfBirth -> TextFieldValue(transformInputText(inputType), inputType.value.selection)
        None -> TextFieldValue()
    }

internal const val MIN_DATE = "10111901"
internal const val MIN_YEAR = 1901
internal val MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR)
internal const val DATE_FORMAT = "ddMMYYYY"

sealed interface AgeInputType {
    data object None : AgeInputType

    data class DateOfBirth(
        val value: TextFieldValue,
    ) : AgeInputType {
        companion object {
            val EMPTY = DateOfBirth(TextFieldValue())
        }
    }

    data class Age(
        val value: TextFieldValue,
        val unit: TimeUnitValues,
    ) : AgeInputType {
        companion object {
            val EMPTY = Age(TextFieldValue(), YEARS)
        }
    }
}
