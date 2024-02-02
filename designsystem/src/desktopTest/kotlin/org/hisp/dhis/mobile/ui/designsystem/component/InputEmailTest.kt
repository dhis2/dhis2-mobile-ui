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

class InputEmailTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayInputEmailCorrectly() {
        rule.setContent {
            InputEmail(
                title = "Label",
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_LEGEND").assertDoesNotExist()
        rule.onNodeWithTag("INPUT_EMAIL_SUPPORTING_TEXT").assertDoesNotExist()
    }

    @Test
    fun shouldAllowUserInputWhenEnabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputEmail(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    inputValue = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("fatiman@gmail.com")
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").assert(hasText("fatiman@gmail.com"))
    }

    @Test
    fun shouldNotAllowUserInputWhenDisabled() {
        rule.setContent {
            InputEmail(
                title = "Label",
                state = InputShellState.DISABLED,
                onEmailActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenTextFieldHasContent() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
            InputEmail(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    inputValue = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("fatiman@gmail.com")
        rule.onNodeWithTag("INPUT_EMAIL_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("fatiman@gmail.com")) }

            InputEmail(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    inputValue = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_EMAIL_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("fatiman@gmail.com")) }
            InputEmail(
                title = "Label",
                state = InputShellState.DISABLED,
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    inputValue = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputEmail(
                title = "Label",
                inputTextFieldValue = TextFieldValue("Input"),
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_LEGEND").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_LEGEND").assertHasNoClickAction()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputEmail(
                title = "Label",
                inputTextFieldValue = TextFieldValue("Input"),
                supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_EMAIL").assertExists()
        rule.onNodeWithTag("INPUT_EMAIL_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldEnableEmailActionButtonOnEnteringValidEmailAddress() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue()) }

            InputEmail(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    inputValue = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("workingexample@email.com")
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("another_working@somethingelse.org")
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("very.common@example.com")
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("abc@example.co.uk")
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldDisableEmailActionButtonOnEnteringInValidEmailAddress() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue("fatiman@gmail.com")) }

            InputEmail(
                title = "Label",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    inputValue = it ?: TextFieldValue()
                },
                onEmailActionCLicked = {},
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsEnabled()

        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("abc.def@mail.c")
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("abc.def@mail")
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsNotEnabled()

        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_EMAIL_FIELD").performTextInput("abc#def@mail.com")
        rule.onNodeWithTag("EMAIL_BUTTON").assertIsNotEnabled()
    }
}
