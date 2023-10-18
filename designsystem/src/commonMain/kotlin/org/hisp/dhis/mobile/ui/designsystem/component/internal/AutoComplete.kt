package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AutoComplete(
    enabled: Boolean = true,
    inputText: String? = null,
    modifier: Modifier = Modifier,
    onValueChanged: ((String?) -> Unit)? = null,
    onClick: () -> Unit,
    options: List<String>,
    inputField: @Composable () -> Unit,
) {
    var selectedOptionText by remember(inputText) { mutableStateOf(inputText ?: "") }
    var expanded by remember { mutableStateOf(false) }
    onValueChanged?.let {
        selectedOptionText = it.toString()
    }
// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier,
    ) {
        inputField()
        val filteringOptions =
            options.filter { it.contains(selectedOptionText, ignoreCase = true) }
        if (filteringOptions.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        text = {
                            Text(text = selectionOption)
                        },
                        enabled = true,
                        interactionSource = remember { MutableInteractionSource() },
                    )
                }
            }
        }
    }
}
