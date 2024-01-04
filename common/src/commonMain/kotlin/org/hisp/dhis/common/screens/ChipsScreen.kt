package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.FilterChip
import org.hisp.dhis.mobile.ui.designsystem.component.InputChip
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun ChipsScreen() {
    ColumnComponentContainer {
        SubTitle("Input Chips")
        var isSelected by remember { mutableStateOf(false) }
        var isSelected1 by remember { mutableStateOf(true) }
        var isSelected2 by remember { mutableStateOf(false) }
        var isSelected3 by remember { mutableStateOf(true) }
        InputChip(label = "Label", selected = isSelected, onSelected = { isSelected = it })
        InputChip(label = "Label", selected = !isSelected1, onSelected = { isSelected1 = !it })
        InputChip(
            label = "Label",
            selected = !isSelected2,
            withTrailingIcon = true,
            onSelected = { isSelected2 = !it },
            onIconSelected = {},
        )
        InputChip(
            label = "Label",
            selected = !isSelected3,
            withTrailingIcon = true,
            onSelected = { isSelected3 = !it },
            onIconSelected = {},
        )
        InputChip(
            label = "Label",
            enabled = false,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Input Chips With badges")
        InputChip(label = "Label", selected = false, badge = "3")
        InputChip(label = "Label", selected = true, badge = "3")
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Filter Chips")
        var isSelected4 by remember { mutableStateOf(false) }
        FilterChip(label = "Label", selected = isSelected4, onSelected = { isSelected4 = it })
        FilterChip(label = "Label", selected = !isSelected4, onSelected = { isSelected4 = !it })
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Filter Chips With badges")
        FilterChip(label = "Label", selected = true, badge = "3")
        FilterChip(label = "Label", selected = false, badge = "3")
    }
}
