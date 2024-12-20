package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.DropdownOption

@Composable
fun DropDownOptions(
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
