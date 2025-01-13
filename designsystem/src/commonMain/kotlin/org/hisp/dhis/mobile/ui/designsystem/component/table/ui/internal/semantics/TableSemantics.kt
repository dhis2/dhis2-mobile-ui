package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

internal const val ROW_TEST_TAG = "ROW_TEST_TAG_"
internal const val CELL_TEST_TAG = "CELL_TEST_TAG_"
internal const val INFO_ICON = "infoIcon"
internal const val HEADER_CELL = "HEADER_CELL"
internal const val MANDATORY_ICON_TEST_TAG = "MANDATORY_ICON_TEST_TAG"
internal const val CELL_VALUE_TEST_TAG = "CELL_VALUE_TEST_TAG"
internal const val CELL_ERROR_UNDERLINE_TEST_TAG = "CELL_ERROR_UNDERLINE_TEST_TAG"

/* Row Header Cell */
internal val InfoIconId = SemanticsPropertyKey<String>("InfoIconId")
internal var SemanticsPropertyReceiver.infoIconId by InfoIconId
internal val TableId = SemanticsPropertyKey<String>("TableId")
internal var SemanticsPropertyReceiver.tableIdSemantic by TableId
internal val RowIndex = SemanticsPropertyKey<Int?>("RowIndex")
internal var SemanticsPropertyReceiver.rowIndexSemantic by RowIndex
internal val RowBackground = SemanticsPropertyKey<Color>("RowBackground")
internal var SemanticsPropertyReceiver.rowBackground by RowBackground

/* Column Header Cell */
internal val ColumnBackground = SemanticsPropertyKey<Color>("ColumnBackground")
internal var SemanticsPropertyReceiver.columnBackground by ColumnBackground
internal val ColumnIndexHeader = SemanticsPropertyKey<Int>("ColumnIndexHeader")
internal var SemanticsPropertyReceiver.columnIndexHeader by ColumnIndexHeader
internal val RowIndexHeader = SemanticsPropertyKey<Int>("RowIndexHeader")
internal var SemanticsPropertyReceiver.rowIndexHeader by RowIndexHeader
internal val TableIdColumnHeader = SemanticsPropertyKey<String>("TableIdColumnHeader")
internal var SemanticsPropertyReceiver.tableIdColumnHeader by TableIdColumnHeader

/* Cell */
internal val CellSelected = SemanticsPropertyKey<Boolean>("CellSelected")
internal var SemanticsPropertyReceiver.cellSelected by CellSelected
internal val HasError = SemanticsPropertyKey<Boolean>("HasError")
internal var SemanticsPropertyReceiver.hasError by HasError
internal val IsBlocked = SemanticsPropertyKey<Boolean>("IsBlocked")
internal var SemanticsPropertyReceiver.isBlocked by IsBlocked
