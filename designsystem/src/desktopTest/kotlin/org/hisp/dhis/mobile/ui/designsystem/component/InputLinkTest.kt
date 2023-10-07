package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
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
        rule.onNodeWithTag("INPUT_LINK_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_LINK_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputLink(
                title = "Label",
                inputText = inputValue,
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
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("example.com")
        rule.onNodeWithTag("INPUT_LINK_FIELD").assert(hasText("example.com"))
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
        rule.onNodeWithTag("INPUT_LINK_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("") }
            InputLink(
                title = "Label",
                inputText = inputValue,
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
        rule.onNodeWithTag("INPUT_LINK_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("example.com")
        rule.onNodeWithTag("INPUT_LINK_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("example.com") }

            InputLink(
                title = "Label",
                inputText = inputValue,
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
        rule.onNodeWithTag("INPUT_LINK_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_LINK_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            var inputValue by rememberSaveable { mutableStateOf("example.com") }
            InputLink(
                title = "Label",
                state = InputShellState.DISABLED,
                inputText = inputValue,
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
                inputText = "Input",
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_LINK_LEGEND").assertHasClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputLink(
                title = "Label",
                inputText = "Input",
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onLinkActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_LINK").assertExists()
        rule.onNodeWithTag("INPUT_LINK_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldEnableLinkActionButtonOnEnteringValidLinkAddress() {
        rule.setContent {
            var inputValue by remember { mutableStateOf("") }

            InputLink(
                title = "Label",
                inputText = inputValue,
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

        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("example.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("https://www.example.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("https://google.us.edi?34535/534534?dfg=g&fg")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("https://abcd.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldDisableLinkActionButtonOnEnteringInValidLinkAddress() {
        rule.setContent {
            var inputValue by remember { mutableStateOf("example.com") }

            InputLink(
                title = "Label",
                inputText = inputValue,
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

        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("htps://example.com")
        rule.onNodeWithTag("LINK_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("example.")
        rule.onNodeWithTag("LINK_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_LINK_FIELD").performTextInput("")
        rule.onNodeWithTag("LINK_BUTTON").assertIsNotEnabled()
    }
}
