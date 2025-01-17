package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a cell in the table header.
 *
 * @property value The value displayed in the header cell.
 */
@Serializable
data class TableHeaderCell(val value: String)
