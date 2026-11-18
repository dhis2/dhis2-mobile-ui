package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.semantics

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.ROW_HEADER_TEST_TAG
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.TEST_TAG_COLUMN_HEADER

/**
 * Semantics and test tags exposed so that consumers can locate and assert table elements in UI tests.
 */

const val TEST_TAG_COLUMN_HEADERS = "TEST_TAG_COLUMN_HEADERS"

val RowBackground = SemanticsPropertyKey<Color>("RowBackground")

val CellSelected = SemanticsPropertyKey<Boolean>("CellSelected")

fun headersTestTag(tableId: String) = "$TEST_TAG_COLUMN_HEADERS$tableId"

fun headerTestTag(
    tableId: String,
    headerRowIndex: Int,
    columnIndex: Int,
) = "$TEST_TAG_COLUMN_HEADER$tableId$headerRowIndex$columnIndex"

fun rowHeaderTestTag(
    tableId: String,
    rowHeaderId: String,
) = "$ROW_HEADER_TEST_TAG$tableId$rowHeaderId"

fun cellTestTag(
    tableId: String,
    cellId: String,
) = "$CELL_TEST_TAG$tableId$cellId"
