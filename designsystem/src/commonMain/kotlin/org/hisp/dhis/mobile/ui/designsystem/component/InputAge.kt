package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType.Age
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType.DateOfBirth
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType.None
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues.YEARS
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation.Companion.DATE_MASK
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * Input filed to enter date-of-birth or age
 *
 * @param title: Label of the component.
 * @param inputType: The type of input :
 *  [None] : default,
 *  [DateOfBirth] : In ddmmyyyy format,
 *  [Age]: Age value with appropriate time unit
 * @param onCalendarActionClicked: Callback to handle the action when the calendar icon is clicked.
 * @param state: [InputShellState]
 * @param legendData: [LegendData]
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param isRequired: Mark this input as marked
 * @param onValueChanged: Callback to receive changes in the input
 */
@Composable
fun InputAge(
    title: String,
    inputType: AgeInputType = None,
    onCalendarActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    state: InputShellState = InputShellState.UNFOCUSED,
    legendData: LegendData? = null,
    supportingText: List<SupportingTextData>? = null,
    isRequired: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    dateOfBirthLabel: String = provideStringResource("date_birth"),
    orLabel: String = provideStringResource("or"),
    ageLabel: String = provideStringResource("age"),
    onFocusChanged: ((Boolean) -> Unit) = {},
    onValueChanged: (AgeInputType) -> Unit,
) {
    val maxAgeCharLimit = 3
    val allowedCharacters = RegExValidations.DATE_TIME.regex

    val helperText = remember(inputType) {
        when (inputType) {
            None, is DateOfBirth -> null
            is Age -> inputType.unit.value
        }
    }
    val helperStyle = remember(inputType) {
        when (inputType) {
            None -> InputStyle.NONE
            is DateOfBirth -> InputStyle.WITH_DATE_OF_BIRTH_HELPER
            is Age -> InputStyle.WITH_HELPER_AFTER
        }
    }

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
                onClick = onCalendarActionClicked,
                enabled = state != InputShellState.DISABLED,
            )
        }
    } else {
        null
    }

    InputShell(
        modifier = modifier.testTag("INPUT_AGE"),
        title = title,
        state = state,
        isRequiredField = isRequired,
        onFocusChanged = onFocusChanged,
        inputField = {
            when (inputType) {
                None -> {
                    TextButtonSelector(
                        modifier = Modifier.focusable(true)
                            .testTag("INPUT_AGE_MODE_SELECTOR"),
                        firstOptionText = dateOfBirthLabel,
                        onClickFirstOption = {
                            onValueChanged.invoke(DateOfBirth.EMPTY)
                        },
                        middleText = orLabel,
                        secondOptionText = ageLabel,
                        onClickSecondOption = {
                            onValueChanged.invoke(Age.EMPTY)
                        },
                        enabled = state != InputShellState.DISABLED,
                    )
                }
                is DateOfBirth, is Age -> {
                    BasicTextField(
                        modifier = Modifier
                            .testTag("INPUT_AGE_TEXT_FIELD")
                            .fillMaxWidth(),
                        inputText = transformInputText(inputType),
                        helper = helperText,
                        isSingleLine = true,
                        helperStyle = helperStyle,
                        onInputChanged = { newText ->
                            if (newText.length > maxAgeCharLimit && inputType is Age) {
                                return@BasicTextField
                            }

                            @Suppress("KotlinConstantConditions")
                            val newInputType: AgeInputType = when (inputType) {
                                is Age -> inputType.copy(value = newText)
                                is DateOfBirth -> updateDateOfBirth(inputType, newText)
                                None -> None
                            }

                            if (allowedCharacters.containsMatchIn(newText) || newText.isBlank()) {
                                onValueChanged.invoke(newInputType)
                            }
                        },
                        enabled = state != InputShellState.DISABLED,
                        state = state,
                        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Number),
                    )
                }
            }
        },
        primaryButton = {
            if (inputType != None && state != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_AGE_RESET_BUTTON").padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        onValueChanged.invoke(None)
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
                )
            }
        },
        legend = {
            if (inputType is Age) {
                TimeUnitSelector(
                    modifier = Modifier.fillMaxWidth()
                        .testTag("INPUT_AGE_TIME_UNIT_SELECTOR"),
                    orientation = Orientation.HORIZONTAL,
                    optionSelected = YEARS.value,
                    enabled = state != InputShellState.DISABLED,
                    onClick = { itemData ->
                        val timeUnit = TimeUnitValues.entries
                            .first { it.value.contains(itemData.textInput!!, ignoreCase = true) }

                        onValueChanged.invoke(inputType.copy(unit = timeUnit))
                    },
                )
            }

            legendData?.let {
                Legend(legendData, Modifier.testTag("INPUT_AGE_LEGEND"))
            }
        },
    )
}

private fun transformInputText(inputType: AgeInputType): String {
    return when (inputType) {
        is Age -> inputType.value
        is DateOfBirth -> inputType.value
        None -> ""
    }
}

private fun updateDateOfBirth(inputType: DateOfBirth, newText: String): AgeInputType {
    return if (newText.length <= DATE_MASK.length) {
        inputType.copy(value = newText)
    } else {
        inputType
    }
}

sealed interface AgeInputType {
    data object None : AgeInputType

    data class DateOfBirth(val value: String) : AgeInputType {
        companion object {
            val EMPTY = DateOfBirth("")
        }
    }

    data class Age(val value: String, val unit: TimeUnitValues) : AgeInputType {
        companion object {
            val EMPTY = Age("", YEARS)
        }
    }
}
