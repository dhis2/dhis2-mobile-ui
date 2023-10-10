package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputDropDownScreen() {
    ColumnComponentContainer {
        val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "Option 7")
        var expanded by rememberSaveable { mutableStateOf(false) }

        Title("Input Dropdown", textColor = TextColor.OnSurfaceVariant)

        SubTitle("Basic Input Dropdown ", textColor = TextColor.OnSurfaceVariant)
        var selectedItem by rememberSaveable { mutableStateOf<String?>(null) }
        InputDropDown(
            title = "Label",
            state = InputShellState.UNFOCUSED,
            selectedItem = selectedItem,
            onResetButtonClicked = {
                selectedItem = null
            },
            onArrowDropDownButtonClicked = {
                expanded = !expanded
            },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        selectedItem = it
                        expanded = false
                    },
                )
            }
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Basic Input Dropdown with content ", textColor = TextColor.OnSurfaceVariant)
        var selectedItem1 by rememberSaveable { mutableStateOf<String?>(options[0]) }
        InputDropDown(
            title = "Label",
            state = InputShellState.UNFOCUSED,
            selectedItem = selectedItem1,
            onResetButtonClicked = {
                selectedItem1 = null
            },
            onArrowDropDownButtonClicked = {
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Error Input Dropdown ", textColor = TextColor.OnSurfaceVariant)
        var selectedItem2 by rememberSaveable { mutableStateOf<String?>(null) }
        InputDropDown(
            title = "Label",
            state = InputShellState.ERROR,
            selectedItem = selectedItem2,
            onResetButtonClicked = {
                selectedItem2 = null
            },
            onArrowDropDownButtonClicked = {
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Disabled Input Dropdown with content ", textColor = TextColor.OnSurfaceVariant)
        var selectedItem3 by rememberSaveable { mutableStateOf<String?>(options[1]) }
        InputDropDown(
            title = "Label",
            state = InputShellState.DISABLED,
            selectedItem = selectedItem3,
            onResetButtonClicked = {
                selectedItem3 = null
            },
            onArrowDropDownButtonClicked = {
                expanded = !expanded
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))
    }
}
