package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.Chip
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer

@Composable
fun ChipsScreen() {
    ColumnComponentContainer(title = "Filter Chips") {
        var isSelected by remember { mutableStateOf(false) }
        Chip(label = "Label", selected = isSelected, onSelected = { isSelected = it })
        Chip(label = "Label", selected = !isSelected, onSelected = { isSelected = !it })
    }
    ColumnComponentContainer(title = "With badges") {
        Chip(label = "Label", selected = true, badge = "3")
        Chip(label = "Label", selected = false, badge = "3")
    }
}
