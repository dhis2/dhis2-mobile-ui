package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import androidx.compose.foundation.layout.PaddingValues
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.CellStyle
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableDimensions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics.HEADER_CELL

data class ItemColumnHeaderUiState(
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
