package org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal

import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.RowHeader
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.CellStyle

/**
 * Data class representing the UI state of an item header in a table.
 *
 * @property tableId The unique identifier of the table.
 * @property rowHeader The header information for the row.
 * @property cellStyle The style applied to the cell.
 * @property width The width of the item header.
 * @property maxLines The maximum number of lines for the text in the header.
 */
internal data class ItemHeaderUiState(
    val tableId: String,
    val rowHeader: RowHeader,
    val cellStyle: CellStyle,
    val width: Dp,
    val maxLines: Int,
    val totalColumns: Int,
    val headerIndexes: List<Int>,
)
