package org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions

interface TableResizeActions {
    fun onTableWidthChanged(width: Int) = run {}
    fun onRowHeaderResize(tableId: String, newValue: Float) = run {}
    fun onColumnHeaderResize(tableId: String, column: Int, newValue: Float) = run {}
    fun onTableDimensionResize(tableId: String, newValue: Float) = run {}
    fun onTableDimensionReset(tableId: String) = run {}
}
