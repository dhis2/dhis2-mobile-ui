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
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputTextTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputTextCorrectly() {
        rule.setContent {
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_TEXT_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_FIELD").performTextInput("Input")
        rule.onNodeWithTag("INPUT_TEXT_FIELD").assert(hasText("Input"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                state = InputShellState.DISABLED,
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_FIELD").performTextInput("Input")
        rule.onNodeWithTag("INPUT_TEXT_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("Input")) }

            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_TEXT_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_TEXT_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                inputTextFieldValue = TextFieldValue("Input"),
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                inputTextFieldValue = TextFieldValue("Input"),
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_SUPPORTING_TEXT").assertExists()
    }
}
