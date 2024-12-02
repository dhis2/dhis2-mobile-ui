package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.DropdownOption
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.MultiSelectBottomSheet
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

@Composable
fun MultiOptionSelector(
    options: List<DropdownOption>,
    cell: TableCell,
    title: String,
    onSave: (String, String) -> Unit,
    onDismiss: () -> Unit,
) {
    MultiSelectBottomSheet(
        items = options.map { option ->
            CheckBoxData(
                uid = option.code,
                checked = cell.value?.split(", ")?.contains(option.name) == true,
                enabled = cell.editable,
                textInput = option.name,
            )
        },
        title = title,
        noResultsFoundString = provideStringResource("no_results_found"),
        searchToFindMoreString = provideStringResource("search_to_see_more"),
        doneButtonText = provideStringResource("done"),
        onItemsSelected = { checkBoxes ->
            val checkedCodes = checkBoxes
                .filter { item -> item.checked }
                .joinToString(", ") { it.uid }
            val checkedValues = checkBoxes
                .filter { item -> item.checked }
                .joinToString(", ") { it.textInput?.text.orEmpty() }
            onSave(checkedCodes, checkedValues)
        },
        onDismiss = onDismiss,
    )
}
