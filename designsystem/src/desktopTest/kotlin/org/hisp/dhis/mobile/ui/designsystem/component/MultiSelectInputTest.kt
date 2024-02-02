package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
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
                onItemsSelected = { _ ->
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
                onItemsSelected = { _ ->
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
                onItemsSelected = { _ ->
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
                onItemsSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CLEAR_ICON_BUTTON").assertExists()
    }

    @Test
    fun when_items_are_more_than_required_then_show_dropdown_button() {
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
            CheckBoxData(
                uid = "uid-3",
                checked = false,
                enabled = true,
                textInput = "Item 3",
            ),
            CheckBoxData(
                uid = "uid-4",
                checked = false,
                enabled = true,
                textInput = "Item 4",
            ),
            CheckBoxData(
                uid = "uid-5",
                checked = false,
                enabled = true,
                textInput = "Item 5",
            ),
            CheckBoxData(
                uid = "uid-6",
                checked = false,
                enabled = true,
                textInput = "Item 6",
            ),
            CheckBoxData(
                uid = "uid-7",
                checked = false,
                enabled = true,
                textInput = "Item 7",
            ),
        )

        composeRule.setContent {
            MultiSelectInput(
                items = multiSelect1Items,
                title = "Multi Select 1",
                state = InputShellState.UNFOCUSED,
                onItemsSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_DROP_DOWN_ICON_BUTTON").assertExists()
    }

    @Test
    fun when_items_are_more_than_required_then_show_inline_chip_items_if_item_are_selected() {
        val multiSelect1Items = listOf(
            CheckBoxData(
                uid = "uid-1",
                checked = true,
                enabled = true,
                textInput = "Item 1",
            ),
            CheckBoxData(
                uid = "uid-2",
                checked = true,
                enabled = true,
                textInput = "Item 2",
            ),
            CheckBoxData(
                uid = "uid-3",
                checked = false,
                enabled = true,
                textInput = "Item 3",
            ),
            CheckBoxData(
                uid = "uid-4",
                checked = false,
                enabled = true,
                textInput = "Item 4",
            ),
            CheckBoxData(
                uid = "uid-5",
                checked = false,
                enabled = true,
                textInput = "Item 5",
            ),
            CheckBoxData(
                uid = "uid-6",
                checked = false,
                enabled = true,
                textInput = "Item 6",
            ),
            CheckBoxData(
                uid = "uid-7",
                checked = false,
                enabled = true,
                textInput = "Item 7",
            ),
        )

        composeRule.setContent {
            MultiSelectInput(
                items = multiSelect1Items,
                title = "Multi Select 1",
                state = InputShellState.UNFOCUSED,
                onItemsSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_0").assertExists()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_1").assertExists()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_2").assertDoesNotExist()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_3").assertDoesNotExist()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_4").assertDoesNotExist()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_5").assertDoesNotExist()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_6").assertDoesNotExist()
    }

    @Test
    fun when_dropdown_button_is_clicked_then_show_bottom_sheet() {
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
            CheckBoxData(
                uid = "uid-3",
                checked = false,
                enabled = true,
                textInput = "Item 3",
            ),
            CheckBoxData(
                uid = "uid-4",
                checked = false,
                enabled = true,
                textInput = "Item 4",
            ),
            CheckBoxData(
                uid = "uid-5",
                checked = false,
                enabled = true,
                textInput = "Item 5",
            ),
            CheckBoxData(
                uid = "uid-6",
                checked = false,
                enabled = true,
                textInput = "Item 6",
            ),
            CheckBoxData(
                uid = "uid-7",
                checked = false,
                enabled = true,
                textInput = "Item 7",
            ),
        )

        composeRule.setContent {
            MultiSelectInput(
                items = multiSelect1Items,
                title = "Multi Select 1",
                state = InputShellState.UNFOCUSED,
                onItemsSelected = { _ ->
                    // no-op
                },
                onClearItemSelection = {
                    // no-op
                },
            )
        }

        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_BOTTOM_SHEET").assertDoesNotExist()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_DROP_DOWN_ICON_BUTTON").performClick()
        composeRule.onNodeWithTag("INPUT_MULTI_SELECT_BOTTOM_SHEET").assertExists()
    }
}
