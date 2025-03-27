package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputAgeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputAgeState
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar

class InputAgeTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun modeSelectionShouldBeShownWhenComponentIsInitialised() {
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_MODE_SELECTOR").assertExists()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_OPEN_CALENDAR_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_TIME_UNIT_SELECTOR").assertDoesNotExist()
    }

    @Test
    fun dateOfBirthFieldShouldBeShownCorrectly() {
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.DateOfBirth.EMPTY,
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_MODE_SELECTOR").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_AGE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_AGE_OPEN_CALENDAR_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_AGE_TIME_UNIT_SELECTOR").assertDoesNotExist()
    }

    @Test
    fun dateOfBirthFieldChangesShouldWorkCorrectly() {
        var inputType by mutableStateOf<AgeInputType>(AgeInputType.None)
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.DateOfBirth.EMPTY,
                ),
                onValueChanged = {
                    inputType = it ?: AgeInputType.None
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput("1002")

        val newInputType = inputType as AgeInputType.DateOfBirth
        assert(newInputType.value.text == "1002")
    }

    @Test
    fun ageFieldShouldBeShownCorrectly() {
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.Age.EMPTY,
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_MODE_SELECTOR").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_AGE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_AGE_OPEN_CALENDAR_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_TIME_UNIT_SELECTOR").assertExists()
    }

    @Test
    fun ageFieldChangesShouldWorkCorrectly() {
        var inputType by mutableStateOf<AgeInputType>(AgeInputType.None)
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.Age.EMPTY,
                ),
                onValueChanged = {
                    inputType = it ?: AgeInputType.None
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput("56")
        val newInputType = inputType as AgeInputType.Age
        assert(newInputType.value.text == "56")
    }

    @Test
    fun clickingOnRestButtonShouldResetMode() {
        var inputType by mutableStateOf<AgeInputType>(AgeInputType.Age.EMPTY)

        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = inputType,
                ),
                onValueChanged = {
                    inputType = it ?: AgeInputType.None
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_RESET_BUTTON").performClick()

        rule.onNodeWithTag("INPUT_AGE_MODE_SELECTOR").assertExists()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_OPEN_CALENDAR_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_TIME_UNIT_SELECTOR").assertDoesNotExist()
    }

    @Test
    fun clickingOnActionButtonForAgeInputShouldShowDatePicker() {
        val input by mutableStateOf(AgeInputType.DateOfBirth(TextFieldValue("1991-11-27")))

        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = input,
                ),
                onValueChanged = {
                    /* no-op */
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_OPEN_CALENDAR_BUTTON").performClick()
        rule.onNodeWithTag("DATE_PICKER").assertExists()
        rule.onNodeWithText("27 nov 1991").assertExists()
    }

    @Test
    fun shouldShowErrorMessageWhenAgeIsOnFuture() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 1)
        }
        val futureDate = SimpleDateFormat(DATE_FORMAT).format(calendar.time)
        var inputType by mutableStateOf<AgeInputType>(AgeInputType.DateOfBirth.EMPTY)

        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = inputType,
                ),
                onValueChanged = {
                    inputType = it ?: AgeInputType.None
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput(futureDate)

        rule.onNodeWithTag("INPUT_AGE_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun changingAgeTimeUnitShouldWorkProperly() {
        var inputType by mutableStateOf<AgeInputType>(AgeInputType.Age.EMPTY)

        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = inputType,
                ),
                onValueChanged = {
                    inputType = it ?: AgeInputType.None
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_TIME_UNIT_SELECTOR").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_YEARS").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_MONTHS").assertExists()
        rule.onNodeWithTag("RADIO_BUTTON_DAYS").assertExists()

        rule.onNodeWithTag("RADIO_BUTTON_MONTHS").performClick()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput("11")
        val newInputMonthType = inputType as AgeInputType.Age
        assert(newInputMonthType.value.text == "11")
        assert(newInputMonthType.unit == TimeUnitValues.MONTHS)

        rule.onNodeWithTag("RADIO_BUTTON_DAYS").performClick()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput("28")
        val newInputDaysType = inputType as AgeInputType.Age
        assert(newInputDaysType.value.text == "28")
        assert(newInputDaysType.unit == TimeUnitValues.DAYS)
    }

    @Test
    fun shouldFormatDateCorrectly() {
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.DateOfBirth(TextFieldValue("1991-11-27")),
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertExists().assertTextEquals("27/11/1991")
    }

    @Test
    fun shouldShowErrorForOutsideRangeDate() {
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.DateOfBirth(TextFieldValue("2025-11-27")),
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertExists().assertTextEquals("27/11/2025")
        rule.onNodeWithTag("INPUT_AGE_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldWorkWithInvalidDate() {
        rule.setContent {
            InputAge(
                state = rememberInputAgeState(
                    inputAgeData = InputAgeData(
                        title = "Label",
                    ),
                    inputType = AgeInputType.DateOfBirth(TextFieldValue("1004-9999-9999")),
                ),
                onValueChanged = {
                    // no-op
                },
            )
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertExists().assertTextEquals("99/99/9999")
    }
}
