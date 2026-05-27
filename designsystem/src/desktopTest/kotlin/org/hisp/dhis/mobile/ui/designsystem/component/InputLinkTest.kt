package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputLinkTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputLinkCorrectly() {
        rule.setContent {
            InputLink(
                title = "Label",
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_LEGEND", useUnmergedTree = true).assertDoesNotExist()
        rule.onNodeWithTag("INPUT_LINK_SUPPORTING_TEXT", useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputLink(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("example.com")
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).assert(hasText("example.com"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputLink(
                title = "Label",
                state = InputShellState.DISABLED,
                onLinkActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputLink(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("example.com")
        rule.onNodeWithTag("INPUT_LINK_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("example.com")) }

            InputLink(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_LINK_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).assertTextEquals("")
        rule.onNodeWithTag("INPUT_LINK_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("example.com")) }
            InputLink(
                title = "Label",
                state = InputShellState.DISABLED,
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onLinkActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputLink(
                title = "Label",
                inputTextFieldValue = TextFieldValue("Input"),
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_LEGEND", useUnmergedTree = true).assertExists()
        rule.onNodeWithTag("INPUT_LINK_LEGEND", useUnmergedTree = true).assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputLink(
                title = "Label",
                inputTextFieldValue = TextFieldValue("Input"),
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_SUPPORTING_TEXT", useUnmergedTree = true).assertExists()
    }

    @Test
    fun shouldEnableLinkActionButtonOnEnteringValidLinkAddress() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue()) }

            InputLink(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("LINK_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("example.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("https://www.example.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("https://google.us.edi?34535/534534?dfg=g&fg")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("https://abcd.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldDisableLinkActionButtonOnEnteringInValidLinkAddress() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue("example.com")) }

            InputLink(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("htps://example.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("example.")
        rule.onNodeWithTag("LINK_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD", useUnmergedTree = true).performTextInput("")
        rule.onNodeWithTag("LINK_BUTTON").assertIsNotEnabled()
    }
}
