package org.hisp.dhis.common.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData

@Composable
fun InputDropDownScreen() {
    ColumnComponentContainer(title = ToggleableInputs.INPUT_DROPDOWN.label) {
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

        ColumnComponentItemContainer("Basic Input Dropdown with < 7 inputs") {
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                dropdownItems = options.take(6),
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = {
                    selectedItem = it
                },
                selectedItem = selectedItem,
            )

            InputDropDown(
                title = "Label - With supporting text",
                state = InputShellState.UNFOCUSED,
                dropdownItems = options.take(6),
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = {
                    selectedItem = it
                },
                selectedItem = selectedItem,
                supportingTextData = listOf(
                    SupportingTextData(text = "Options"),
                ),
            )

            InputDropDown(
                title = "Label - Parameter Style",
                inputStyle = InputStyle.ParameterInputStyle(),
                state = InputShellState.UNFOCUSED,
                dropdownItems = options.take(6),
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = {
                    selectedItem = it
                },
                selectedItem = selectedItem,
            )
        }

        ColumnComponentItemContainer("Basic Input Dropdown with >= 7 inputs") {
            var selectedItem4 by remember { mutableStateOf<DropdownItem?>(null) }
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                dropdownItems = options,
                onResetButtonClicked = {
                    selectedItem4 = null
                },
                onItemSelected = {
                    selectedItem4 = it
                },
                selectedItem = selectedItem4,
            )
        }

        ColumnComponentItemContainer("Basic Input Dropdown with content ") {
            var selectedItem1 by remember { mutableStateOf<DropdownItem?>(options[0]) }
            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                dropdownItems = options,
                onResetButtonClicked = {
                    selectedItem1 = null
                },
                onItemSelected = {
                    selectedItem1 = it
                },
                selectedItem = selectedItem1,
            )
        }

        ColumnComponentItemContainer("Error Input Dropdown ") {
            var selectedItem2 by remember { mutableStateOf<DropdownItem?>(null) }
            InputDropDown(
                title = "Label",
                state = InputShellState.ERROR,
                dropdownItems = options,
                onResetButtonClicked = {
                    selectedItem2 = null
                },
                onItemSelected = {
                    selectedItem2 = it
                },
                selectedItem = selectedItem2,
            )
        }

        ColumnComponentItemContainer("Disabled Input Dropdown with content ") {
            var selectedItem3 by remember { mutableStateOf<DropdownItem?>(options[1]) }
            InputDropDown(
                title = "Label",
                state = InputShellState.DISABLED,
                dropdownItems = options,
                onResetButtonClicked = {
                    selectedItem3 = null
                },
                onItemSelected = {
                    selectedItem3 = it
                },
                selectedItem = selectedItem3,
            )
        }

        ColumnComponentItemContainer("Input Dropdown with 5000 items ") {
            val dropdownItems = mutableListOf<DropdownItem>()
            for (i in 1..5000) {
                dropdownItems.add(DropdownItem("$i"))
            }

            InputDropDown(
                title = "Label",
                state = InputShellState.UNFOCUSED,
                dropdownItems = dropdownItems,
                onResetButtonClicked = {
                    selectedItem = null
                },
                onItemSelected = {
                    selectedItem = it
                },
                selectedItem = selectedItem,
            )
        }
    }
}
