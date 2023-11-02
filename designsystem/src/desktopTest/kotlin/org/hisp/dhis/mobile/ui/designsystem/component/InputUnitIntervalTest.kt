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

class InputUnitIntervalTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputUnitIntervalCorrectly() {
        rule.setContent {
            InputUnitInterval(
                title = "Label",
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputUnitInterval(
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
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").performTextInput("0.25")
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").assert(hasText("0.25"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputUnitInterval(
                title = "Label",
                state = InputShellState.DISABLED,
            )
        }
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputUnitInterval(
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
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").performTextInput("0.25")
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("0.25") }

            InputUnitInterval(
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
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputUnitInterval(
                title = "Label",
                inputText = "",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputUnitInterval(
                title = "Label",
                inputText = "",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldNotAllowValuesOver1() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputUnitInterval(
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
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").performTextInput("2")
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").assertTextEquals("")
    }

    @Test
    fun shouldNotAllowValuesBelow0() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputUnitInterval(
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
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL").assertExists()
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").performTextInput("-2")
        rule.onNodeWithTag("INPUT_UNIT_INTERVAL_FIELD").assertTextEquals("")
    }
}
