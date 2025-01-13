package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.DropdownOption

/**
 * Composable function to display a dropdown menu with options.
 *
 * @param expanded Indicates whether the dropdown menu is expanded.
 * @param options The list of options to be displayed in the dropdown menu.
 * @param onDismiss The callback to be invoked when the dropdown menu is dismissed.
 * @param onSelected The callback to be invoked when an option is selected, with the option's code and label.
 */
@Composable
internal fun DropDownOptions(
    expanded: Boolean,
    options: List<DropdownOption>,
    onDismiss: () -> Unit,
    onSelected: (code: String, label: String) -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                onClick = {
                    onSelected.invoke(option.code, option.name)
                },
                text = {
                    Text(text = option.name)
                },
            )
        }
    }
}
