package org.hisp.dhis.mobile.ui.designsystem.component.table.model

/**
 * Data class representing the UI state of the table corner.
 *
 * @property isSelected Indicates if the table corner is selected.
 * @property onTableResize Callback function invoked when the table is resized.
 * @property onResizing Callback function invoked during the resizing of the table.
 * @property singleValueTable Indicates if the table contains a single value.
 */
internal data class TableCornerUiState(
    val isSelected: Boolean = false,
    val onTableResize: (Float) -> Unit,
    val onResizing: (ResizingCell?) -> Unit,
    val singleValueTable: Boolean = false,
)
