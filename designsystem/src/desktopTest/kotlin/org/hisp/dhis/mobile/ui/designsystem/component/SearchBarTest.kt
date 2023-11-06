package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class SearchBarTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplayTextCorrectly() {
        rule.setContent {
            SearchBar(text = "Input")
        }
        rule.onNodeWithText("Input").assertExists()
    }

    @Test
    fun shouldDisplayClearButton() {
        rule.setContent {
            SearchBar(text = "Input")
        }
        rule.onNodeWithTag("CANCEL_BUTTON").assertExists()
        rule.onNodeWithTag("SEARCH_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldDisplaySearchButton() {
        rule.setContent {
            SearchBar()
        }
        rule.onNodeWithTag("CANCEL_BUTTON").assertDoesNotExist()
        rule.onNodeWithTag("SEARCH_BUTTON").assertExists()
    }

    @Test
    fun textDisappearsAfterPressCancelButton() {
        rule.setContent {
            SearchBar(text = "Input")
        }
        rule.onNodeWithText("Input").assertExists()
        rule.onNodeWithTag("CANCEL_BUTTON").performClick()
        rule.onNodeWithText("").assertExists()
        rule.onNodeWithTag("SEARCH_BUTTON").assertExists()
    }
}
