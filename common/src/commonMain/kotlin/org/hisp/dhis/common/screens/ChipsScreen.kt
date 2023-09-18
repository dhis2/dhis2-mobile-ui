package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.Chip
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer

@Composable
fun ChipsScreen() {
    ColumnComponentContainer(title = "Filter Chips") {
        Chip(label = "Label", selected = true)
        Chip(label = "Label", selected = false)
    }
    ColumnComponentContainer(title = "With badges") {
        Chip(label = "Label", selected = true, badge = "3")
        Chip(label = "Label", selected = false, badge = "3")
    }
}
