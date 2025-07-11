@file:OptIn(ExperimentalTestApi::class)

package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class InputDropDownTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldAllowDropDownSelectionWhenEnabled() {
        val options = listOf(DropdownItem("Option 1"))
        rule.setContent {
            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").assertIsEnabled()
    }

    @Test
    fun shouldNotAllowDropDownSelectionWhenDisabled() {
        val options = listOf(DropdownItem("Option 1"))
        rule.setContent {
            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").assertIsNotEnabled()
    }

    @Test
    fun shouldShowResetButtonWhenItemIsSelected() {
        val options = listOf(DropdownItem("Option 1"))

        rule.setContent {
            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                selectedItem = options[0],
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertExists()
    }

    @Test
    fun shouldHideResetButtonWhenNoItemIsSelected() {
        val options = emptyList<DropdownItem>()
        rule.setContent {
            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldHideResetButtonWhenDisabled() {
        rule.setContent {
            val options = listOf(DropdownItem("Option 1"))

            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                selectedItem = options[0],
                state = InputShellState.DISABLED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_RESET_BUTTON").assertDoesNotExist()
    }

    @Test
    fun shouldRemoveSelectedItemWhenResetButtonIsClickedAndHideResetButton() {
        rule.setContent {
            val options = listOf(DropdownItem("Option 1"))
            var itemSelected by rememberSaveable { mutableStateOf<DropdownItem?>(options.first()) }

            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                selectedItem = itemSelected,
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {
                    itemSelected = null
                },
                onItemSelected = { _, item ->
                    itemSelected = item
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
        val options = emptyList<DropdownItem>()
        rule.setContent {
            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_LEGEND").assertExists()
    }

    @Test
    fun shouldShowSupportingTextCorrectly() {
        val options = emptyList<DropdownItem>()
        rule.setContent {
            InputDropDown(
                title = "Label",
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_SUPPORTING_TEXT").assertExists()
    }

    @Test
    fun shouldShowDropdownMenuOnIconClickIfThereAreLessThan7Items() {
        val options =
            listOf(
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
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU").assertExists()
    }

    @Test
    fun shouldShowBottomSheetOnIconClickIfThereAre7OrMoreItems() {
        val options =
            listOf(
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
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
                useDropDown = false,
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
    }

    @Test
    fun shouldOnlyShowMatchedSearchResultsInBottomSheet() {
        val options =
            listOf(
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
            var filteredOptions by remember { mutableStateOf(options) }
            InputDropDown(
                title = "Label",
                itemCount = filteredOptions.size,
                onSearchOption = { query ->
                    filteredOptions = options.filter { it.label.contains(query) }.toMutableList()
                },
                fetchItem = {
                    filteredOptions[it]
                },
                loadOptions = {
                },
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
                useDropDown = false,
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
        rule
            .onNode(
                hasTestTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").and(
                    SemanticsMatcher.expectValue(DropDownItemCount, options.size),
                ),
            ).assertExists()
        rule.onNodeWithContentDescription(searchSemantics).assertExists()

        // Search
        rule.onNodeWithContentDescription(searchSemantics).performTextInput("Option 1")
        rule
            .onNode(
                hasTestTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").and(
                    SemanticsMatcher.expectValue(DropDownItemCount, 2),
                ),
            ).assertExists()
        rule
            .onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS")
            .onChildren()[0]
            .assertTextEquals("Option 1")
        rule
            .onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS")
            .onChildren()[1]
            .assertTextEquals("Option 10")
    }

    @Test
    fun shouldNoResultsFoundTextWhenThereAreNoSearchResults() {
        val options =
            listOf(
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
            var filteredOptions by remember { mutableStateOf(options) }
            InputDropDown(
                title = "Label",
                itemCount = filteredOptions.size,
                onSearchOption = { query ->
                    filteredOptions = options.filter { it.label.contains(query) }
                },
                fetchItem = {
                    filteredOptions[it]
                },
                loadOptions = {
                },
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
                useDropDown = false,
                noResultsFoundString = "No results found",
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").onChildren().assertCountEquals(10)
        rule.onNodeWithContentDescription(searchSemantics).assertExists()

        // Search
        rule.onNodeWithContentDescription(searchSemantics).performTextInput("Option 50")
        rule
            .onNode(
                hasTestTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").and(
                    SemanticsMatcher.expectValue(DropDownItemCount, 0),
                ),
            ).assertExists()
        rule.onNodeWithText("No results found").assertExists()
    }

    @Test
    fun shouldNotShowSearchBarWhenSearchBarConfigIsFalse() {
        val options =
            listOf(
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
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
                showSearchBar = false,
                useDropDown = false,
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").onChildren().assertCountEquals(10)
        rule.onNodeWithContentDescription(searchSemantics).assertDoesNotExist()
    }

    @Test
    fun clickingOnDropdownMenuItemShouldTriggerCallbackAndDismissMenu() {
        val options =
            listOf(
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
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                selectedItem = selectedItem,
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, item ->
                    selectedItem = item
                },
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU_ITEM_0").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_MENU").assertDoesNotExist()
        assert(selectedItem == options.first())
    }

    @Test
    fun clickingOnBottomSheetItemShouldTriggerCallbackAndDismissBottomSheet() {
        val options =
            listOf(
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
                itemCount = options.size,
                onSearchOption = {
                },
                fetchItem = {
                    options[it]
                },
                loadOptions = {
                },
                selectedItem = selectedItem,
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, item ->
                    selectedItem = item
                },
                useDropDown = false,
                noResultsFoundString = "No results found",
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEM_2").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertDoesNotExist()
        assert(selectedItem == options[2])
    }

    @Test
    fun shouldShowSearchForMoreOptionTextWhenMoreThan50Option() {
        val options = mutableListOf<DropdownItem>()
        for (i in 1..100) {
            options.add(
                DropdownItem("Option $i"),
            )
        }

        val searchSemantics = "Search"

        rule.setContent {
            var filteredOptions by remember { mutableStateOf(options) }

            InputDropDown(
                title = "Label",
                itemCount = filteredOptions.size,
                onSearchOption = { query ->
                    filteredOptions = options.filter { it.label.contains(query) }.toMutableList()
                },
                fetchItem = {
                    filteredOptions[it]
                },
                loadOptions = {
                },
                supportingTextData =
                    listOf(
                        SupportingTextData(
                            "Supporting text",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.UNFOCUSED,
                onResetButtonClicked = {},
                onItemSelected = { _, _ -> },
                useDropDown = false,
                noResultsFoundString = "No results found",
            )
        }
        rule.onNodeWithTag("INPUT_DROPDOWN").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_ARROW_BUTTON").performClick()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET").assertExists()
        rule.onNodeWithTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").onChildren().assertCountEquals(10)
        rule.onNodeWithContentDescription(searchSemantics).assertExists()

        rule
            .onNode(
                hasTestTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").and(
                    SemanticsMatcher.expectValue(DropDownItemCount, options.size),
                ),
            ).assertExists()
        rule
            .onNodeWithText("Not all options are displayed.\\n Search to see more.")
            .assertDoesNotExist()

        // Search
        rule.onNodeWithContentDescription(searchSemantics).performTextInput("5")
        rule
            .onNode(
                hasTestTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").and(
                    SemanticsMatcher.expectValue(DropDownItemCount, 19),
                ),
            ).assertExists()
        rule
            .onNodeWithText("Not all options are displayed.\\n Search to see more.")
            .assertDoesNotExist()

        rule.onNodeWithContentDescription(searchSemantics).performTextClearance()
        rule.onNodeWithContentDescription(searchSemantics).performTextInput("55")
        rule
            .onNode(
                hasTestTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").and(
                    SemanticsMatcher.expectValue(DropDownItemCount, options.filter { it.label.contains("55") }.size),
                ),
            ).assertExists()
        rule
            .onNodeWithText("Not all options are displayed.\\n Search to see more.")
            .assertDoesNotExist()

        rule.onNodeWithContentDescription(searchSemantics).performTextClearance()
        rule.onNodeWithContentDescription(searchSemantics).performTextInput("555")
        rule
            .onNode(
                hasTestTag("INPUT_DROPDOWN_BOTTOM_SHEET_ITEMS").and(
                    SemanticsMatcher.expectValue(DropDownItemCount, 0),
                ),
            ).assertExists()
        rule.onNodeWithText("No results found").assertExists()
    }
}
