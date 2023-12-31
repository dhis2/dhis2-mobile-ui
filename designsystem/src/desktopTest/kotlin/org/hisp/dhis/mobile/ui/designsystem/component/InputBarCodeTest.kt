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
import org.junit.Rule
import org.junit.Test

class InputBarCodeTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayComponentCorrectly() {
        rule.setContent {
            var inputValue by remember { mutableStateOf("") }

            InputBarCode(
                title = "Bar code",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onActionButtonClicked = {
                    // no-op
                },
            )
        }
        rule.onNodeWithTag("INPUT_BAR_CODE").assertExists()
    }

    @Test
    fun shouldDeleteContentWhenResetIsClicked() {
        rule.setContent {
            var inputValue by remember { mutableStateOf("12345") }

            InputBarCode(
                title = "Phone Number",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onActionButtonClicked = {
                    // no-op
                },
            )
        }
        rule.onNodeWithTag("INPUT_BAR_CODE").assertExists()
        rule.onNodeWithTag("INPUT_BAR_CODE_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_BAR_CODE_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_BAR_CODE_FIELD").assertExists()
        rule.onNodeWithTag("INPUT_BAR_CODE_FIELD").assertTextEquals("")
    }

    @Test
    fun shouldShowActionButtonCorrectlyAndBeClickable() {
        rule.setContent {
            var inputValue by remember { mutableStateOf("12345") }

            InputBarCode(
                title = "Phone Number",
                inputText = inputValue,
                onValueChanged = {
                    if (it != null) {
                        inputValue = it
                    }
                },
                onActionButtonClicked = {
                    // no-op
                },
            )
        }
        rule.onNodeWithTag("INPUT_BAR_CODE").assertExists()
        rule.onNodeWithTag("INPUT_BAR_CODE_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_BAR_CODE_BUTTON").assertIsEnabled()
    }
}
