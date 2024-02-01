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

        MultiSelectInput(
            items = emptyList(),
            title = "Multi Select Empty",
            state = InputShellState.UNFOCUSED,
            onItemSelected = { _ ->
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
            onItemSelected = { checkBoxData ->
                val index = multiSelect1Items.indexOfFirst { it.uid == checkBoxData.uid }
                multiSelect1Items[index] = checkBoxData
            },
            onClearItemSelection = {
                multiSelect1Items.replaceAll { it.copy(checked = false) }
            },
        )

        MultiSelectInput(
            items = multiSelect1Items,
            title = "Multi Select 1 Error",
            state = InputShellState.ERROR,
            onItemSelected = { checkBoxData ->
                val index = multiSelect1Items.indexOfFirst { it.uid == checkBoxData.uid }
                multiSelect1Items[index] = checkBoxData
            },
            onClearItemSelection = {
                multiSelect1Items.replaceAll { it.copy(checked = false) }
            },
        )

        MultiSelectInput(
            items = multiSelect1Items,
            title = "Multi Select 1 Disabled",
            state = InputShellState.DISABLED,
            onItemSelected = { checkBoxData ->
                val index = multiSelect1Items.indexOfFirst { it.uid == checkBoxData.uid }
                multiSelect1Items[index] = checkBoxData
            },
            onClearItemSelection = {
                multiSelect1Items.replaceAll { it.copy(checked = false) }
            },
        )
    }
}
