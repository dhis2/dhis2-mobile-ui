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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation.Companion.DATE_MASK
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.component.internal.dateIsInRange
import org.hisp.dhis.mobile.ui.designsystem.component.internal.isValidDate
import org.hisp.dhis.mobile.ui.designsystem.component.internal.parseStringDateToMillis
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
                                val newInputType: AgeInputType = updateDateOfBirth(uiModel.inputType, TextFieldValue(getDate(it), TextRange(getDate(it).length)))
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

internal const val MIN_DATE = "10111901"
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
