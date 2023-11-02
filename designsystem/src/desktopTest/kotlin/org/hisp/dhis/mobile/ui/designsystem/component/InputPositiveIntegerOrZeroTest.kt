package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasNoClickAction
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

class InputPositiveIntegerOrZeroTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputPositiveIntegerOrZeroCorrectly() {
        rule.setContent {
            InputPositiveIntegerOrZero(
                title = "Label",
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").performTextInput("1234")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assert(hasText("1234"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputPositiveIntegerOrZero(
                title = "Label",
                state = InputShellState.DISABLED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").performTextInput("1234")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("1234") }

            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = "",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = "",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldNotAllowDecimalValues() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").performTextInput("12.12")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assert(hasText(""))
    }

    @Test
    fun shouldNotAllowNegativeValues() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").performTextInput("-1212")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assert(hasText(""))
    }

    @Test
    fun shouldNotAllowValuesAfterLeadingAZero() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").performTextInput("012")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assert(hasText(""))
    }

    @Test
    fun shouldAllowZero() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPositiveIntegerOrZero(
                title = "Label",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO").assertExists()
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").performTextInput("0")
        rule.onNodeWithTag("INPUT_POSITIVE_INTEGER_OR_ZERO_FIELD").assert(hasText("0"))
    }
}
