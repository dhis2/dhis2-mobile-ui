package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a row in a table.
 *
 * @property rowHeader The header of the row.
 * @property values The values of the row.
 * @property maxLines The maximum number of lines to display in the cell.
 */
@Serializable
data class TableRowModel(
    val rowHeader: RowHeader,
    val values: Map<Int, TableCell>,
    val maxLines: Int = 3,
)
