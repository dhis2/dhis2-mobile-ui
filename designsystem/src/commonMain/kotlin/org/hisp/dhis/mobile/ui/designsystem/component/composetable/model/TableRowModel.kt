package org.hisp.dhis.mobile.ui.designsystem.component.composetable.model

import kotlinx.serialization.Serializable

@Serializable
data class TableRowModel(
    val rowHeader: RowHeader,
    val values: Map<Int, TableCell>,
    val isLastRow: Boolean = false,
    val maxLines: Int = 3,
    val dropDownOptions: List<DropdownOption>? = null,
)
