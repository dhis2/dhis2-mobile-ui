package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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

class InputNumberTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputNumberCorrectly() {
        rule.setContent {
            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_NUMBER_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").performTextInput("3232")
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").assert(hasText("3232"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                state = InputShellState.DISABLED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").performTextInput("1234")
        rule.onNodeWithTag("INPUT_NUMBER_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("1234") }

            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_NUMBER_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                inputText = "",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                inputText = "",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldAllowOnlyNumbersAsInput() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputNumber(
                title = "Label",
                modifier = Modifier.testTag("INPUT_NUMBER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_NUMBER").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").performTextInput("string value example")
        rule.onNodeWithTag("INPUT_NUMBER_FIELD").assert(hasText(""))
    }
}
