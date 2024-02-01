package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class MultiSelectInputTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun when_no_items_are_present_then_hide_multiselect_list_in_input() {
        composeRule.setContent {
            MultiSelectInput(
                items = emptyList(),
                title = "Multi Select 1",
                state = InputShellState.UNFOCUSED,
                onItemSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_LIST_ITEM_1").assertDoesNotExist()
    }

    @Test
    fun when_items_are_less_than_required_then_show_multiselect_list_in_input() {
        val multiSelect1Items = listOf(
            CheckBoxData(
                uid = "uid-1",
                checked = false,
                enabled = true,
                textInput = "Item 1",
            ),
            CheckBoxData(
                uid = "uid-2",
                checked = false,
                enabled = true,
                textInput = "Item 2",
            ),
        )

        composeRule.setContent {
            MultiSelectInput(
                items = multiSelect1Items,
                title = "Multi Select 1",
                state = InputShellState.UNFOCUSED,
                onItemSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_LIST").assertExists()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_LIST_ITEM_1").assertExists()
    }

    @Test
    fun when_items_are_less_than_required_and_no_items_are_selected_then_hide_clear_button_in_input() {
        val multiSelect1Items = listOf(
            CheckBoxData(
                uid = "uid-1",
                checked = false,
                enabled = true,
                textInput = "Item 1",
            ),
            CheckBoxData(
                uid = "uid-2",
                checked = false,
                enabled = true,
                textInput = "Item 2",
            ),
        )

        composeRule.setContent {
            MultiSelectInput(
                items = multiSelect1Items,
                title = "Multi Select 1",
                state = InputShellState.UNFOCUSED,
                onItemSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CLEAR_ICON_BUTTON").assertDoesNotExist()
    }

    @Test
    fun when_items_are_less_than_required_and_at_least_one_item_is_selected_then_show_clear_button_in_input() {
        val multiSelect1Items = listOf(
            CheckBoxData(
                uid = "uid-1",
                checked = true,
                enabled = true,
                textInput = "Item 1",
            ),
            CheckBoxData(
                uid = "uid-2",
                checked = false,
                enabled = true,
                textInput = "Item 2",
            ),
        )

        composeRule.setContent {
            MultiSelectInput(
                items = multiSelect1Items,
                title = "Multi Select 1",
                state = InputShellState.UNFOCUSED,
                onItemSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CLEAR_ICON_BUTTON").assertExists()
    }
}
