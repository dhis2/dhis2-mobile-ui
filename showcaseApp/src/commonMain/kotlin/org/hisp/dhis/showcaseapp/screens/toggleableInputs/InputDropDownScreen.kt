package org.hisp.dhis.showcaseapp.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellDefaults

@Composable
fun InputDropDownScreen() {
    ColumnScreenContainer(title = ToggleableInputs.INPUT_DROPDOWN.label) {
        val options = listOf(
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
        var selectedItem by remember { mutableStateOf<DropdownItem?>(null) }

        ColumnComponentContainer("Basic Input Dropdown") {
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                itemCount = options.size,
                onSearchOption = {},
                fetchItem = { index -> options[index] },
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = { _, item ->
                    selectedItem = item
                },
                selectedItem = selectedItem,
                loadOptions = {
                    /*no-op*/
                },
            )

            InputDropDown(
                title = "Label - With supporting text",
                state = InputShellState.UNFOCUSED,
                itemCount = options.size,
                onSearchOption = {},
                fetchItem = { index -> options[index] },
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = { _, item ->
                    selectedItem = item
                },
                selectedItem = selectedItem,
                supportingTextData = listOf(
                    SupportingTextData(text = "Options"),
                ),
                loadOptions = {
                    /*no-op*/
                },
            )

            InputDropDown(
                title = "Label - Parameter Style",
                inputStyle = InputStyle.ParameterInputStyle(),
                state = InputShellState.UNFOCUSED,
                itemCount = options.size,
                onSearchOption = {},
                fetchItem = { index -> options[index] },
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = { _, item ->
                    selectedItem = item
                },
                selectedItem = selectedItem,
                loadOptions = {
                    /*no-op*/
                },
            )
        }

        ColumnComponentContainer("Basic Input Dropdown for large set") {
            var selectedItem4 by remember { mutableStateOf<DropdownItem?>(null) }
            var filteredItems by remember { mutableStateOf(options) }
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                itemCount = filteredItems.size,
                onSearchOption = { query ->
                    filteredItems = options.filter { it.label.contains(query) }
                },
                fetchItem = { index -> filteredItems[index] },
                useDropDown = false,
                onResetButtonClicked = {
                    selectedItem4 = null
                },
                onItemSelected = { _, item ->
                    selectedItem4 = item
                },
                selectedItem = selectedItem4,
                loadOptions = {
                    /*no-op*/
                },
            )
        }

        ColumnComponentContainer("Basic Input Dropdown for large set and devices with edge to edge enabled") {
            var selectedItem4 by remember { mutableStateOf<DropdownItem?>(null) }
            var filteredItems by remember { mutableStateOf(options) }
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                itemCount = filteredItems.size,
                onSearchOption = { query ->
                    filteredItems = options.filter { it.label.contains(query) }
                },
                fetchItem = { index -> filteredItems[index] },
                useDropDown = false,
                onResetButtonClicked = {
                    selectedItem4 = null
                },
                onItemSelected = { _, item ->
                    selectedItem4 = item
                },
                selectedItem = selectedItem4,
                loadOptions = {
                    /*no-op*/
                },
                bottomSheetLowerPadding = BottomSheetShellDefaults.lowerPadding(isEdgeToEdgeEnabled = true),
                windowInsets = { BottomSheetShellDefaults.windowInsets(true) },
            )
        }

        ColumnComponentContainer("Basic Input Dropdown with content ") {
            var selectedItem1 by remember { mutableStateOf<DropdownItem?>(options[0]) }
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                itemCount = options.size,
                onSearchOption = {},
                fetchItem = { index -> options[index] },
                useDropDown = false,
                onResetButtonClicked = {
                    selectedItem1 = null
                },
                onItemSelected = { _, item ->
                    selectedItem1 = item
                },
                selectedItem = selectedItem1,
                loadOptions = {
                    /*no-op*/
                },
            )
        }

        ColumnComponentContainer("Error Input Dropdown ") {
            var selectedItem2 by remember { mutableStateOf<DropdownItem?>(null) }
            InputDropDown(
                title = "Label",
                state = InputShellState.ERROR,
                itemCount = options.size,
                onSearchOption = {},
                fetchItem = { index -> options[index] },
                useDropDown = false,
                onResetButtonClicked = {
                    selectedItem2 = null
                },
                onItemSelected = { _, item ->
                    selectedItem2 = item
                },
                selectedItem = selectedItem2,
                loadOptions = {
                    /*no-op*/
                },
            )
        }

        ColumnComponentContainer("Disabled Input Dropdown with content ") {
            var selectedItem3 by remember { mutableStateOf<DropdownItem?>(options[1]) }
            InputDropDown(
                title = "Label",
                state = InputShellState.DISABLED,
                itemCount = options.size,
                onSearchOption = {},
                fetchItem = { index -> options[index] },
                useDropDown = false,
                onResetButtonClicked = {
                    selectedItem3 = null
                },
                onItemSelected = { _, item ->
                    selectedItem3 = item
                },
                selectedItem = selectedItem3,
                loadOptions = {
                    /*no-op*/
                },
            )
        }

        ColumnComponentContainer("Input Dropdown with 5000 items ") {
            val dropdownItems = mutableListOf<DropdownItem>()
            for (i in 1..5000) {
                dropdownItems.add(DropdownItem("$i"))
            }

            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                itemCount = dropdownItems.size,
                onSearchOption = {},
                fetchItem = { index -> options[index] },
                useDropDown = false,
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = { _, item ->
                    selectedItem = item
                },
                selectedItem = selectedItem,
                loadOptions = {
                    /*no-op*/
                },
            )
        }
    }
}
