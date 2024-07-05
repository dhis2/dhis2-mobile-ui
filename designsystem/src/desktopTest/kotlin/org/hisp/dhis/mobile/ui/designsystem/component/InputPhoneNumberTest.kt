package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.junit.Rule
import org.junit.Test

class InputPhoneNumberTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldAllowDigitsInputOnly() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue()) }

            InputPhoneNumber(
                title = "Phone Number",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onCallActionClicked = {
                    // no-op
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").performTextInput("1111")
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").assertTextEquals("1111")
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").performTextInput("1111a")
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").assertTextEquals("1111")
    }

    @Test
    fun shouldAllowAddAndParenthesisInput() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue()) }

            InputPhoneNumber(
                title = "Phone Number",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onCallActionClicked = {
                    // no-op
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").performTextInput("(+91)-9876543210")
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").assertTextEquals("(+91)-9876543210")
    }

    @Test
    fun shouldEnableCallActionButtonWhenTextLengthIsEqualOrGreaterThanMinCharacter() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue()) }

            InputPhoneNumber(
                title = "Phone Number",
                inputTextFieldValue = inputValue,
                minLength = 10,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onCallActionClicked = {
                    // no-op
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("CALL_PHONE_NUMBER_BUTTON").assertIsNotEnabled()
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").performTextInput("9876543210")
        rule.onNodeWithTag("CALL_PHONE_NUMBER_BUTTON").assertIsEnabled()
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").performTextClearance()
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").performTextInput("987654321")
        rule.onNodeWithTag("CALL_PHONE_NUMBER_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldDisplaySupportText() {
        rule.setContent {
            InputPhoneNumber(
                title = "Phone Number",
                inputTextFieldValue = TextFieldValue(),
                state = InputShellState.UNFOCUSED,
                onValueChanged = {
                    // no-op
                },
                onCallActionClicked = {
                    // no-op
                },
            )
        }
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_SUPPORTING_TEXT").assertDoesNotExist()

        rule.setContent {
            InputPhoneNumber(
                title = "Phone Number",
                inputTextFieldValue = TextFieldValue(),
                state = InputShellState.ERROR,
                onValueChanged = {
                    // no-op
                },
                onCallActionClicked = {
                    // no-op
                },
                supportingText = listOf(SupportingTextData("Error", SupportingTextState.ERROR)),
            )
        }
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldClearPhoneNumberWhenClearButtonIsClicked() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue("1234")) }

            InputPhoneNumber(
                title = "Phone Number",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) inputValue = it
                },
                onCallActionClicked = {
                    // no-op
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_FIELD").assertTextEquals("")
        rule.onNodeWithTag("INPUT_PHONE_NUMBER_RESET_BUTTON").assertDoesNotExist()
    }
}
