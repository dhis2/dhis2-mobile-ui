package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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

class InputLetterTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputTextCorrectly() {
        rule.setContent {
            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_LETTER_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_FIELD").performTextInput("A")
        rule.onNodeWithTag("INPUT_LETTER_FIELD").assert(hasText("A"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
                state = InputShellState.DISABLED,
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_FIELD").performTextInput("A")
        rule.onNodeWithTag("INPUT_LETTER_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("Input") }

            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_LETTER_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_LETTER_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
                inputText = "Input",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
                inputText = "Input",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldOnlyAllowASingleUpperCaseCharacter() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputLetter(
                title = "Label",
                modifier = Modifier.testTag("INPUT_LETTER"),
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
            )
        }
        rule.onNodeWithTag("INPUT_LETTER").assertExists()
        rule.onNodeWithTag("INPUT_LETTER_FIELD").performTextInput("a")
        rule.onNodeWithTag("INPUT_LETTER_FIELD").assert(hasText("A"))
    }
}
