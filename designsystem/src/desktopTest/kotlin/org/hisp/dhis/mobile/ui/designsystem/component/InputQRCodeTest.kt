package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.input.TextFieldValue
import org.junit.Rule
import org.junit.Test

class InputQRCodeTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayComponentCorrectly() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue()) }

            InputQRCode(
                title = "Phone Number",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onQRButtonClicked = {
                    // no-op
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_QR_CODE").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetIsClicked() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue("12345")) }

            InputQRCode(
                title = "Phone Number",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onQRButtonClicked = {
                    // no-op
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_QR_CODE").assertExists()
        rule.onNodeWithTag("INPUT_QR_CODE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_QR_CODE_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_QR_CODE_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_QR_CODE_FIELD").assertTextEquals("")
    }

    @Test
    fun shouldShowActionButtonCorrectlyAndBeClickable() {
        rule.setContent {
            var inputValue by remember { mutableStateOf(TextFieldValue("12345")) }

            InputQRCode(
                title = "Phone Number",
                inputTextFieldValue = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onQRButtonClicked = {
                    // no-op
                },
                state = InputShellState.UNFOCUSED,
            )
        }
        rule.onNodeWithTag("INPUT_QR_CODE").assertExists()
        rule.onNodeWithTag("INPUT_QR_CODE_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_QR_CODE_BUTTON").assertIsEnabled()
    }
}
