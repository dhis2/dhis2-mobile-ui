package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputPositiveIntegerTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputPositiveIntegerCorrectly() {
        rule.setContent {
            InputPositiveInteger(
                title = "Label",
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveInteger(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").performTextInput("1234")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").assert(hasText("1234"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputPositiveInteger(
                title = "Label",
                state = InputShellState.DISABLED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveInteger(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").performTextInput("1234")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("1234") }

            InputPositiveInteger(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputPositiveInteger(
                title = "Label",
                inputText = "",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputPositiveInteger(
                title = "Label",
                inputText = "",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldNotAllowDecimalValues() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveInteger(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").performTextInput("12.12")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").assert(hasText(""))
    }

    @Test
    fun shouldNotAllowNegativeValues() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveInteger(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").performTextInput("-1212")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").assert(hasText(""))
    }

    @Test
    fun shouldNotAllowValuesWithALeadingZero() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveInteger(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").performTextInput("01212")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_FIELD").assert(hasText(""))
    }
}
