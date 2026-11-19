package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

/*
 * Semantics and test tags exposed so that consumers can locate and assert table elements in UI tests.
 * Each public key or tag builder lives next to the internal setter or constant it relies on.
 */

/** Test tag prefix of the column headers container. See [headersTestTag]. */
const val TEST_TAG_COLUMN_HEADERS = "TEST_TAG_COLUMN_HEADERS"
internal const val TEST_TAG_COLUMN_HEADER = "TEST_TAG_COLUMN_HEADER"

fun headersTestTag(tableId: String) = "$TEST_TAG_COLUMN_HEADERS$tableId"

fun headerTestTag(
    tableId: String,
    headerRowIndex: Int,
    columnIndex: Int,
) = "$TEST_TAG_COLUMN_HEADER$tableId$headerRowIndex$columnIndex"

// Row headers
internal const val ROW_HEADER_TEST_TAG = "ROW_HEADER_TEST_TAG_"

val RowBackground = SemanticsPropertyKey<Color>("RowBackground")
internal var SemanticsPropertyReceiver.rowBackground by RowBackground

fun rowHeaderTestTag(
    tableId: String,
    rowHeaderId: String,
) = "$ROW_HEADER_TEST_TAG$tableId$rowHeaderId"

// Cells
internal const val CELL_TEST_TAG = "CELL_TEST_TAG_"

val CellSelected = SemanticsPropertyKey<Boolean>("CellSelected")
internal var SemanticsPropertyReceiver.cellSelected by CellSelected

fun cellTestTag(
    tableId: String,
    cellId: String,
) = "$CELL_TEST_TAG$tableId$cellId"
