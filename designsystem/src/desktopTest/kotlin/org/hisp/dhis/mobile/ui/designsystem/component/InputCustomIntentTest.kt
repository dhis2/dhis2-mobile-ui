package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class InputCustomIntentTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayComponentCorrectly() {
        rule.setContent {
            InputCustomIntent(
                title = "Label",
                values = emptyList(),
                buttonText = "launch",
                customIntentState = CustomIntentState.LAUNCH,
                onLaunch = {},
            )
        }
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + LAUNCH_BUTTON_TEST_TAG).assertExists()
    }

    @Test
    fun shouldShowValueAsTextWhenLoaded() {
        rule.setContent {
            var value = listOf("Option")
            InputCustomIntent(
                title = "Label",
                values = value,
                buttonText = "launch",
                customIntentState = CustomIntentState.LOADED,
                onLaunch = {},
            )
        }
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + INPUT_CUSTOM_INTENT_CLEAR_BUTTON_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_TEST_TAG).assertTextEquals("Option")
    }

    @Test
    fun shouldShowMultipleValuesAsChipsWhenLoaded() {
        rule.setContent {
            var value = listOf("Option 1", "Option 2", "Option 3")
            InputCustomIntent(
                title = "Label",
                values = value,
                buttonText = "launch",
                customIntentState = CustomIntentState.LOADED,
                onLaunch = {},
            )
        }
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + INPUT_CUSTOM_INTENT_CLEAR_BUTTON_TEST_TAG).assertExists()
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_CHIP_TEST_TAG + "0").assertExists()
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_CHIP_TEST_TAG + "1").assertExists()
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_CHIP_TEST_TAG + "2").assertExists()
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_CHIP_TEST_TAG + "0").onChild().assertTextEquals("Option 1")
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_CHIP_TEST_TAG + "1").onChild().assertTextEquals("Option 2")
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_CHIP_TEST_TAG + "2").onChild().assertTextEquals("Option 3")
    }

    @Test
    fun shouldShowProgressIndicatorWhenLoading() {
        rule.setContent {
            InputCustomIntent(
                title = "Label",
                values = emptyList(),
                buttonText = "launch",
                customIntentState = CustomIntentState.LOADING,
                onLaunch = {},
                onClear = {},
            )
        }
        rule.onNodeWithTag(INPUT_CUSTOM_INTENT_TEST_TAG + CIRCULAR_PROGRESS_INDICATOR_TEST_TAG).assertIsDisplayed()
    }
}
