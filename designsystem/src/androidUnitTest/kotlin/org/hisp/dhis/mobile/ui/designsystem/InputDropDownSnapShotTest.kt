package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.junit.Rule
import org.junit.Test

class InputDropDownSnapShotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInputDropDown() {
        paparazzi.snapshot {
            ColumnComponentContainer {
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
                Title("Input Dropdown", textColor = TextColor.OnSurfaceVariant)

                SubTitle("Basic Input Dropdown with < 7 inputs", textColor = TextColor.OnSurfaceVariant)
                var selectedItem by remember { mutableStateOf<DropdownItem?>(null) }
                val focusRequester = remember { FocusRequester() }

                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }

                InputDropDown(
                    modifier = Modifier.focusRequester(focusRequester),
                    title = "Label",
                    state = InputShellState.FOCUSED,
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

                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Basic Input Dropdown with >= 7 inputs", textColor = TextColor.OnSurfaceVariant)
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

                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Basic Input Dropdown with content ", textColor = TextColor.OnSurfaceVariant)
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
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Error Input Dropdown ", textColor = TextColor.OnSurfaceVariant)
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
                Spacer(Modifier.size(Spacing.Spacing18))

                SubTitle("Disabled Input Dropdown with content ", textColor = TextColor.OnSurfaceVariant)
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
                Spacer(Modifier.size(Spacing.Spacing18))
            }
        }
    }
}
