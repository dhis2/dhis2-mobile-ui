package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputDropDownTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldAllowDropDownSelectionWhenEnabled() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = listOf(DropdownItem("Option 1")),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowDropDownSelectionWhenDisabled() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = listOf(DropdownItem("Option 1")),
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenItemIsSelected() {
        val dropdownItem = DropdownItem("Option 1")

        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = listOf(dropdownItem),
                selectedItem = dropdownItem,
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetButtonWhenNoItemIsSelected() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = emptyList(),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            val dropdownItem = DropdownItem("Option 1")

            InputDropDown(
                title = "Label",
                dropdownItems = listOf(dropdownItem),
                selectedItem = dropdownItem,
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldRemoveSelectedItemWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            val dropdownItem = DropdownItem("Option 1")
            var itemSelected by rememberSaveable { mutableStateOf<DropdownItem?>(dropdownItem) }

            InputDropDown(
                title = "Label",
                dropdownItems = listOf(dropdownItem),
                selectedItem = itemSelected,
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {
                    itemSelected = null
                },
                onItemSelected = {
                    itemSelected = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_TEXT").assertTextEquals("")
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldShowLegendCorrectly() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = emptyList(),
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_LEGEND").assertExists()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = emptyList(),
                supportingTextData = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldShowDropdownMenuOnIconClickIfThereAreLessThan7Items() {
        val dropdownItems = listOf(
            DropdownItem("Option 1"),
            DropdownItem("Option 2"),
            DropdownItem("Option 3"),
            DropdownItem("Option 4"),
            DropdownItem("Option 5"),
            DropdownItem("Option 6"),
        )

        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = dropdownItems,
                supportingTextData = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU").assertExists()
    }

    @Test
    fun shouldShowBottomSheetOnIconClickIfThereAre7OrMoreItems() {
        val dropdownItems = listOf(
            DropdownItem("Option 1"),
            DropdownItem("Option 2"),
            DropdownItem("Option 3"),
            DropdownItem("Option 4"),
            DropdownItem("Option 5"),
            DropdownItem("Option 6"),
            DropdownItem("Option 7"),
        )

        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = dropdownItems,
                supportingTextData = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
    }

    @Test
    fun shouldOnlyShowMatchedSearchResultsInBottomSheet() {
        val dropdownItems = listOf(
            DropdownItem("Option 1"),
            DropdownItem("Option 2"),
            DropdownItem("Option 3"),
            DropdownItem("Option 4"),
            DropdownItem("Option 5"),
            DropdownItem("Option 6"),
            DropdownItem("Option 7"),
            DropdownItem("Option 8"),
            DropdownItem("Option 9"),
            DropdownItem("Option 10"),
        )

        val searchSemantics = "Search"

        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = dropdownItems,
                supportingTextData = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {},
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").onChildren().assertCountEquals(10)
        rule.onNodeWithContentDescription(searchSemantics).assertExists()

        // Search
        rule.onNodeWithContentDescription(searchSemantics).performTextInput("Option 1")
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").onChildren().assertCountEquals(2)
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").onChildren()[0].assertTextEquals("Option 1")
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").onChildren()[1].assertTextEquals("Option 10")
    }

    @Test
    fun clickingOnDropdownMenuItemShouldTriggerCallbackAndDismissMenu() {
        val dropdownItems = listOf(
            DropdownItem("Option 1"),
            DropdownItem("Option 2"),
            DropdownItem("Option 3"),
            DropdownItem("Option 4"),
            DropdownItem("Option 5"),
            DropdownItem("Option 6"),
        )

        var selectedItem by mutableStateOf<DropdownItem?>(null)

        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = dropdownItems,
                selectedItem = selectedItem,
                supportingTextData = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU_ITEM_0").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU").assertDoesNotExist()
        assert(selectedItem == dropdownItems.first())
    }

    @Test
    fun clickingOnBottomSheetItemShouldTriggerCallbackAndDismissBottomSheet() {
        val dropdownItems = listOf(
            DropdownItem("Option 1"),
            DropdownItem("Option 2"),
            DropdownItem("Option 3"),
            DropdownItem("Option 4"),
            DropdownItem("Option 5"),
            DropdownItem("Option 6"),
            DropdownItem("Option 7"),
        )

        var selectedItem by mutableStateOf<DropdownItem?>(null)

        rule.setContent {
            InputDropDown(
                title = "Label",
                dropdownItems = dropdownItems,
                selectedItem = selectedItem,
                supportingTextData = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = {
                    selectedItem = it
                },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEM_2").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertDoesNotExist()
        assert(selectedItem == dropdownItems[2])
    }
}
