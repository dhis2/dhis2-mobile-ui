package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.MultiSelectInput

@Composable
fun MultiSelectInputScreen() {
    ColumnComponentContainer {
        val multiSelect1Items = mutableStateListOf(
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
        )

        val multiSelect2Items = mutableStateListOf(
            CheckBoxData(
                uid = "uid-1",
                checked = true,
                enabled = true,
                textInput = "Option 1",
            ),
            CheckBoxData(
                uid = "uid-2",
                checked = true,
                enabled = true,
                textInput = "Option 2",
            ),
            CheckBoxData(
                uid = "uid-3",
                checked = true,
                enabled = true,
                textInput = "Opt. 3",
            ),
            CheckBoxData(
                uid = "uid-4",
                checked = false,
                enabled = true,
                textInput = "Option 4",
            ),
            CheckBoxData(
                uid = "uid-5",
                checked = false,
                enabled = true,
                textInput = "Option 5",
            ),
            CheckBoxData(
                uid = "uid-6",
                checked = false,
                enabled = true,
                textInput = "Opt. 6",
            ),
            CheckBoxData(
                uid = "uid-7",
                checked = false,
                enabled = true,
                textInput = "Opt. 7",
            ),
        )

        MultiSelectInput(
            items = emptyList(),
            title = "Multi Select Empty",
            state = InputShellState.UNFOCUSED,
            onItemsSelected = { _ ->
                // no-op
            },
            onClearItemSelection = {
                // no-op
            },
        )

        MultiSelectInput(
            items = multiSelect1Items,
            title = "Multi Select 1",
            state = InputShellState.UNFOCUSED,
            onItemsSelected = { selectedItems ->
                selectedItems.forEach { selectedItem ->
                    val index = multiSelect1Items.indexOfFirst { it.uid == selectedItem.uid }
                    multiSelect1Items[index] = selectedItem
                }
            },
            onClearItemSelection = {
                multiSelect1Items.replaceAll { it.copy(checked = false) }
            },
        )

        MultiSelectInput(
            items = multiSelect1Items,
            title = "Multi Select 1 Error",
            state = InputShellState.ERROR,
            onItemsSelected = { selectedItems ->
                selectedItems.forEach { selectedItem ->
                    val index = multiSelect1Items.indexOfFirst { it.uid == selectedItem.uid }
                    multiSelect1Items[index] = selectedItem
                }
            },
            onClearItemSelection = {
                multiSelect1Items.replaceAll { it.copy(checked = false) }
            },
        )

        MultiSelectInput(
            items = multiSelect1Items,
            title = "Multi Select 1 Disabled",
            state = InputShellState.DISABLED,
            onItemsSelected = { selectedItems ->
                selectedItems.forEach { selectedItem ->
                    val index = multiSelect1Items.indexOfFirst { it.uid == selectedItem.uid }
                    multiSelect1Items[index] = selectedItem
                }
            },
            onClearItemSelection = {
                multiSelect1Items.replaceAll { it.copy(checked = false) }
            },
        )

        MultiSelectInput(
            items = multiSelect2Items,
            title = "Multi Select 2",
            state = InputShellState.UNFOCUSED,
            onItemsSelected = { selectedItems ->
                selectedItems.forEach { selectedItem ->
                    val index = multiSelect2Items.indexOfFirst { it.uid == selectedItem.uid }
                    multiSelect2Items[index] = selectedItem
                }
            },
            onClearItemSelection = {
                multiSelect2Items.replaceAll { it.copy(checked = false) }
            },
        )

        MultiSelectInput(
            items = multiSelect2Items,
            title = "Multi Select 2 Disabled",
            state = InputShellState.DISABLED,
            onItemsSelected = { selectedItems ->
                selectedItems.forEach { selectedItem ->
                    val index = multiSelect2Items.indexOfFirst { it.uid == selectedItem.uid }
                    multiSelect2Items[index] = selectedItem
                }
            },
            onClearItemSelection = {
                multiSelect2Items.replaceAll { it.copy(checked = false) }
            },
        )
    }
}
