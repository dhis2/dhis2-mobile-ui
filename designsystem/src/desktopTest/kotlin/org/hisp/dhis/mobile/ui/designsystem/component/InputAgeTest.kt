package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class InputAgeTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun modeSelectionShouldBeShownWhenComponentIsInitialised() {
        rule.setContent {
            InputAge(
                title = "Label",
                onCalendarActionClicked = {
                    // no-op
                },
            ) {
                // no-op
            }
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
                title = "Label",
                inputType = AgeInputType.DateOfBirth.EMPTY,
                onCalendarActionClicked = {
                    // no-op
                },
            ) {
                // no-op
            }
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
                title = "Label",
                inputType = AgeInputType.DateOfBirth.EMPTY,
                onCalendarActionClicked = {
                    // no-op
                },
            ) {
                inputType = it
            }
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput("1002")

        assert(inputType == AgeInputType.DateOfBirth("1002"))
    }

    @Test
    fun ageFieldShouldBeShownCorrectly() {
        rule.setContent {
            InputAge(
                title = "Label",
                inputType = AgeInputType.Age.EMPTY,
                onCalendarActionClicked = {
                    // no-op
                },
            ) {
                // no-op
            }
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
                title = "Label",
                inputType = AgeInputType.Age.EMPTY,
                onCalendarActionClicked = {
                    // no-op
                },
            ) {
                inputType = it
            }
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput("56")

        assert(inputType == AgeInputType.Age(value = "56", unit = TimeUnitValues.YEARS))
    }

    @Test
    fun clickingOnRestButtonShouldResetMode() {
        var inputType by mutableStateOf<AgeInputType>(AgeInputType.Age.EMPTY)

        rule.setContent {
            InputAge(
                title = "Label",
                inputType = inputType,
                onCalendarActionClicked = {
                    // no-op
                },
            ) {
                inputType = it
            }
        }

        rule.onNodeWithTag("INPUT_AGE_RESET_BUTTON").performClick()

        rule.onNodeWithTag("INPUT_AGE_MODE_SELECTOR").assertExists()
        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_RESET_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_OPEN_CALENDAR_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_AGE_TIME_UNIT_SELECTOR").assertDoesNotExist()
    }
}
