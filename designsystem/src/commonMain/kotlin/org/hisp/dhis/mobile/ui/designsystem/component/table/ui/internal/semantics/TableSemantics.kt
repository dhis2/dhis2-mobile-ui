package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

internal const val ROW_TEST_TAG = "ROW_TEST_TAG_"
internal const val ROW_HEADER_TEST_TAG = "ROW_HEADER_TEST_TAG_"
internal const val ROW_VALUES_TEST_TAG = "ROW_VALUES_TEST_TAG_"
internal const val CELL_TEST_TAG = "CELL_TEST_TAG_"
internal const val INFO_ICON = "infoIcon"
internal const val HEADER_CELL = "HEADER_CELL"
internal const val MANDATORY_ICON_TEST_TAG = "MANDATORY_ICON_TEST_TAG"
internal const val CELL_VALUE_TEST_TAG = "CELL_VALUE_TEST_TAG"
internal const val CELL_ERROR_UNDERLINE_TEST_TAG = "CELL_ERROR_UNDERLINE_TEST_TAG"

const val TEST_TAG_CORNER = "TEST_TAG_CORNER"
const val TEST_TAG_COLUMN_HEADERS = "TEST_TAG_COLUMN_HEADERS"
const val TEST_TAG_COLUMN_HEADER_ROW = "TEST_TAG_COLUMN_HEADER_ROW"
const val TEST_TAG_COLUMN_HEADER = "TEST_TAG_COLUMN_HEADER"
const val TEST_TAG_TABLE_ROW = "TEST_TAG_TABLE_ROW"
const val TEST_TAG_ROW_HEADER = "TEST_TAG_ROW_HEADER"
const val TEST_TAG_CELL = "TEST_TAG_CELL"

/*Table*/
val TableId = SemanticsPropertyKey<String>("TableId")
var SemanticsPropertyReceiver.tableIdSemantic by TableId

/*Corner*/
fun cornerTestTag(tableId: String) = "$TEST_TAG_CORNER$tableId"

/* Row*/
internal val InfoIconId = SemanticsPropertyKey<String>("InfoIconId")
internal var SemanticsPropertyReceiver.infoIconId by InfoIconId
internal val RowIndex = SemanticsPropertyKey<Int?>("RowIndex")
internal var SemanticsPropertyReceiver.rowIndexSemantic by RowIndex
internal val RowBackground = SemanticsPropertyKey<Color>("RowBackground")
internal var SemanticsPropertyReceiver.rowBackground by RowBackground
fun rowTestTag(tableId: String, rowHeaderId: String) = "$ROW_TEST_TAG$tableId$rowHeaderId"
fun rowHeaderTestTag(tableId: String, rowHeaderId: String) = "$ROW_HEADER_TEST_TAG$tableId$rowHeaderId"
fun rowValuesTestTag(tableId: String, rowHeaderId: String) = "$ROW_VALUES_TEST_TAG$tableId$rowHeaderId"

/* Column*/
val ColumnHeaderRow = SemanticsPropertyKey<Int>("ColumnHeaderRow")
var SemanticsPropertyReceiver.columnHeaderRow: Int by ColumnHeaderRow
val ColumnHeaderColumn = SemanticsPropertyKey<Int>("ColumnHeaderColumn")
var SemanticsPropertyReceiver.columnHeaderColumn: Int by ColumnHeaderColumn
internal val ColumnBackground = SemanticsPropertyKey<Color>("ColumnBackground")
internal var SemanticsPropertyReceiver.columnBackground by ColumnBackground
internal val ColumnIndexHeader = SemanticsPropertyKey<Int>("ColumnIndexHeader")
internal var SemanticsPropertyReceiver.columnIndexHeader by ColumnIndexHeader
internal val RowIndexHeader = SemanticsPropertyKey<Int>("RowIndexHeader")
internal var SemanticsPropertyReceiver.rowIndexHeader by RowIndexHeader
internal val TableIdColumnHeader = SemanticsPropertyKey<String>("TableIdColumnHeader")
internal var SemanticsPropertyReceiver.tableIdColumnHeader by TableIdColumnHeader
fun headersTestTag(tableId: String) = "$TEST_TAG_COLUMN_HEADERS$tableId"
fun headerRowTestTag(tableId: String, headerRowIndex: Int) = "$TEST_TAG_COLUMN_HEADER_ROW$tableId$headerRowIndex"
fun headerTestTag(tableId: String, headerRowIndex: Int, columnIndex: Int) = "$TEST_TAG_COLUMN_HEADER$tableId$headerRowIndex$columnIndex"

/* Cell */
internal val CellSelected = SemanticsPropertyKey<Boolean>("CellSelected")
internal var SemanticsPropertyReceiver.cellSelected by CellSelected
internal val HasError = SemanticsPropertyKey<Boolean>("HasError")
internal var SemanticsPropertyReceiver.hasError by HasError
internal val IsBlocked = SemanticsPropertyKey<Boolean>("IsBlocked")
internal var SemanticsPropertyReceiver.isBlocked by IsBlocked
fun cellTestTag(tableId: String, cellId: String) = "$CELL_TEST_TAG$tableId$cellId"
