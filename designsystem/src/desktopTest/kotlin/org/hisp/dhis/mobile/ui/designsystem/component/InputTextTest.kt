package org.hisp.dhis.mobile.ui.designsystem.component

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
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_TEXT_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
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
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_FIELD").performTextInput("Input")
        rule.onNodeWithTag("INPUT_TEXT_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                inputText = "Input",
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
                inputText = "Input",
                legendText = "Legend",
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputText(
                title = "Label",
                modifier = Modifier.testTag("INPUT_TEXT"),
                inputText = "Input",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
            )
        }
        rule.onNodeWithTag("INPUT_TEXT").assertExists()
        rule.onNodeWithTag("INPUT_TEXT_SUPPORTING_TEXT").assertExists()
    }
}
