package org.hisp.dhis.mobile.ui.designsystem.component.table.actions

/**
 * Interface for handling table resize actions.
 * Implementations of this interface should provide specific logic for handling table resize events.
 */
interface TableResizeActions {
    /**
     * Called when the table width changes.
     * @param width The new width of the table.
     */
    fun onTableWidthChanged(width: Int) = run {}

    /**
     * Called when a row header is resized.
     * @param tableId The ID of the table.
     * @param newValue The new value of the row header.
     */
    fun onRowHeaderResize(
        tableId: String,
        newValue: Float,
    ) = run {}

    /**
     * Called when a column header is resized.
     * @param tableId The ID of the table.
     * @param column The index of the column.
     * @param newValue The new value of the column header.
     */
    fun onColumnHeaderResize(
        tableId: String,
        column: Int,
        newValue: Float,
    ) = run {}

    /**
     * Called when a table dimension is resized.
     * @param tableId The ID of the table.
     * @param newValue The new value of the table dimension.
     */
    fun onTableDimensionResize(
        tableId: String,
        newValue: Float,
    ) = run {}

    /**
     * Called when a table dimension is reset.
     * @param tableId The ID of the table.
     */
    fun onTableDimensionReset(tableId: String) = run {}
}
