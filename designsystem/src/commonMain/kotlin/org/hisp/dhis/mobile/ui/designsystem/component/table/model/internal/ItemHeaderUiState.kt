package org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal

import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.RowHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableDialogModel
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.CellStyle

/**
 * Data class representing the UI state of an item header in a table.
 *
 * @property tableId The unique identifier of the table.
 * @property rowHeader The header information for the row.
 * @property cellStyle The style applied to the cell.
 * @property width The width of the item header.
 * @property maxLines The maximum number of lines for the text in the header.
 * @property onCellSelected Callback function invoked when a cell is selected.
 * @property onDecorationClick Callback function invoked when the decoration icon is clicked.
 * @property onHeaderResize Callback function invoked when the header is resized.
 * @property onResizing Callback function invoked during the resizing of the header.
 */
internal data class ItemHeaderUiState(
    val tableId: String,
    val rowHeader: RowHeader,
    val cellStyle: CellStyle,
    val width: Dp,
    val maxLines: Int,
    val onCellSelected: (Int?) -> Unit,
    val onDecorationClick: (dialogModel: TableDialogModel) -> Unit,
    val onHeaderResize: (Float) -> Unit,
    val onResizing: (ResizingCell?) -> Unit,
)
