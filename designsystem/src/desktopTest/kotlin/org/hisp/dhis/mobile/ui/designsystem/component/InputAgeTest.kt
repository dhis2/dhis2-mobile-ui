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
import java.text.SimpleDateFormat
import java.util.Calendar

class InputAgeTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun modeSelectionShouldBeShownWhenComponentIsInitialised() {
        rule.setContent {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    onValueChanged = {
                        // no-op
                    },
                ),

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
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.DateOfBirth.EMPTY,
                    onValueChanged = {
                        // no-op
                    },
                ),

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
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.DateOfBirth.EMPTY,
                    onValueChanged = {
                        inputType = it
                    },
                ),

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
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.Age.EMPTY,
                    onValueChanged = {
                        // no-op
                    },
                ),

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
                InputAgeModel(
                    title = "Label",
                    inputType = AgeInputType.Age.EMPTY,
                    onValueChanged = {
                        inputType = it
                    },
                ),

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
                InputAgeModel(
                    title = "Label",
                    inputType = inputType,
                    onValueChanged = {
                        inputType = it
                    },
                ),

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
    fun shouldShowErrorMessageWhenAgeIsOnFuture() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 1)
        }
        val futureDate = SimpleDateFormat(DATE_FORMAT).format(calendar.time)
        var inputType by mutableStateOf<AgeInputType>(AgeInputType.DateOfBirth.EMPTY)

        rule.setContent {
            InputAge(
                InputAgeModel(
                    title = "Label",
                    inputType = inputType,
                    onValueChanged = {
                        inputType = it
                    },
                ),

            )
        }

        rule.onNodeWithTag("INPUT_AGE_TEXT_FIELD").performTextInput(futureDate)

        rule.onNodeWithTag("INPUT_AGE_SUPPORTING_TEXT").assertExists()
    }
}
