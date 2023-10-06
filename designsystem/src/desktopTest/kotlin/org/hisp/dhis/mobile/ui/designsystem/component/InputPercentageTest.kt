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

class InputPercentageTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputPositiveIntegerCorrectly() {
        rule.setContent {
            InputPercentage(
                title = "Label",
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_PERCENTAGE_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPercentage(
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
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").performTextInput("25")
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").assert(hasText("25 %"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputPercentage(
                title = "Label",
                state = InputShellState.DISABLED,
            )
        }
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPercentage(
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
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").performTextInput("25")
        rule.onNodeWithTag("INPUT_PERCENTAGE_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("25") }

            InputPercentage(
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
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_PERCENTAGE_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputPercentage(
                title = "Label",
                inputText = "",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputPercentage(
                title = "Label",
                inputText = "",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldNotAllowValuesOver100() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPercentage(
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
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").performTextInput("1212")
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").assertTextEquals(" %")
    }

    @Test
    fun shouldNotAllowValuesWithALeadingZero() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputPercentage(
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
        rule.onNodeWithTag("INPUT_PERCENTAGE").assertExists()
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").performTextInput("012")
        rule.onNodeWithTag("INPUT_PERCENTAGE_FIELD").assertTextEquals(" %")
    }
}
