package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import androidx.compose.foundation.layout.PaddingValues
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.CellStyle
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableDimensions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics.HEADER_CELL

/**
 * Data class representing the UI state of an item column header in a table.
 *
 * @property tableId The unique identifier of the table.
 * @property rowIndex The index of the row.
 * @property columnIndex The index of the column.
 * @property headerCell The header cell information.
 * @property headerMeasures The measures of the header.
 * @property paddingValues The padding values for the header cell.
 * @property cellStyle The style applied to the cell.
 * @property onCellSelected Callback function invoked when a cell is selected.
 * @property onHeaderResize Callback function invoked when the header is resized.
 * @property onResizing Callback function invoked during the resizing of the header.
 * @property isLastRow Indicates if this is the last row.
 * @property checkMaxCondition Function to check the maximum condition for resizing.
 */
internal data class ItemColumnHeaderUiState(
    val tableId: String?,
    val rowIndex: Int,
    val columnIndex: Int,
    val headerCell: TableHeaderCell,
    val headerMeasures: HeaderMeasures,
    val paddingValues: PaddingValues,
    val cellStyle: CellStyle,
    val onCellSelected: (Int) -> Unit,
    val onHeaderResize: (Int, Float) -> Unit,
    val onResizing: (ResizingCell?) -> Unit,
    val isLastRow: Boolean,
    val checkMaxCondition: (TableDimensions, Float) -> Boolean,
) {
    val testTag = "$HEADER_CELL$tableId$rowIndex$columnIndex"
}
