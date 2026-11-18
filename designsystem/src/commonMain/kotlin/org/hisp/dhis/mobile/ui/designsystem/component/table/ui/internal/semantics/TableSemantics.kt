package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

internal const val ROW_TEST_TAG = "ROW_TEST_TAG_"
internal const val ROW_VALUES_TEST_TAG = "ROW_VALUES_TEST_TAG_"
internal const val HEADER_CELL = "HEADER_CELL"
internal const val MANDATORY_ICON_TEST_TAG = "MANDATORY_ICON_TEST_TAG"
internal const val CELL_VALUE_TEST_TAG = "CELL_VALUE_TEST_TAG"
internal const val CELL_ERROR_UNDERLINE_TEST_TAG = "CELL_ERROR_UNDERLINE_TEST_TAG"

internal const val TEST_TAG_CORNER = "TEST_TAG_CORNER"
internal const val TEST_TAG_COLUMN_HEADER_ROW = "TEST_TAG_COLUMN_HEADER_ROW"
internal const val TEST_TAG_TABLE_ROW = "TEST_TAG_TABLE_ROW"
internal const val TEST_TAG_ROW_HEADER = "TEST_TAG_ROW_HEADER"
internal const val TEST_TAG_CELL = "TEST_TAG_CELL"

// Table
internal val TableId = SemanticsPropertyKey<String>("TableId")
internal var SemanticsPropertyReceiver.tableIdSemantic by TableId

// Corner
internal fun cornerTestTag(tableId: String) = "$TEST_TAG_CORNER$tableId"

// Row
internal val InfoIconId = SemanticsPropertyKey<String>("InfoIconId")
internal var SemanticsPropertyReceiver.infoIconId by InfoIconId
internal val RowIndex = SemanticsPropertyKey<Int?>("RowIndex")
internal var SemanticsPropertyReceiver.rowIndexSemantic by RowIndex

internal fun rowTestTag(
    tableId: String,
    rowHeaderId: String,
) = "$ROW_TEST_TAG$tableId$rowHeaderId"

internal fun rowValuesTestTag(
    tableId: String,
    rowHeaderId: String,
) = "$ROW_VALUES_TEST_TAG$tableId$rowHeaderId"

// Column
internal val ColumnHeaderRow = SemanticsPropertyKey<Int>("ColumnHeaderRow")
internal var SemanticsPropertyReceiver.columnHeaderRow: Int by ColumnHeaderRow
internal val ColumnHeaderColumn = SemanticsPropertyKey<Int>("ColumnHeaderColumn")
internal var SemanticsPropertyReceiver.columnHeaderColumn: Int by ColumnHeaderColumn
internal val ColumnBackground = SemanticsPropertyKey<Color>("ColumnBackground")
internal var SemanticsPropertyReceiver.columnBackground by ColumnBackground
internal val ColumnIndexHeader = SemanticsPropertyKey<Int>("ColumnIndexHeader")
internal var SemanticsPropertyReceiver.columnIndexHeader by ColumnIndexHeader
internal val RowIndexHeader = SemanticsPropertyKey<Int>("RowIndexHeader")
internal var SemanticsPropertyReceiver.rowIndexHeader by RowIndexHeader
internal val TableIdColumnHeader = SemanticsPropertyKey<String>("TableIdColumnHeader")
internal var SemanticsPropertyReceiver.tableIdColumnHeader by TableIdColumnHeader

internal fun headerRowTestTag(
    tableId: String,
    headerRowIndex: Int,
) = "$TEST_TAG_COLUMN_HEADER_ROW$tableId$headerRowIndex"

// Cell
internal val HasError = SemanticsPropertyKey<Boolean>("HasError")
internal var SemanticsPropertyReceiver.hasError by HasError
internal val IsBlocked = SemanticsPropertyKey<Boolean>("IsBlocked")
internal var SemanticsPropertyReceiver.isBlocked by IsBlocked
