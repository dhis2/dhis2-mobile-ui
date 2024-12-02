package org.hisp.dhis.mobile.ui.designsystem.component.composetable.model

data class TableCornerUiState(
    val isSelected: Boolean = false,
    val onTableResize: (Float) -> Unit,
    val onResizing: (ResizingCell?) -> Unit,
    val singleValueTable: Boolean = false,
)
