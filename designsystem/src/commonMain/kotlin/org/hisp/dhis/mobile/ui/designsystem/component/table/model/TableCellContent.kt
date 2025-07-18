package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

/**
 * Sealed class representing different types of content a table cell can contain.
 */
@Serializable
sealed class TableCellContent {
    @Serializable
    data class Text(
        val value: String?,
    ) : TableCellContent()

    @Serializable
    data class Checkbox(
        val isChecked: Boolean,
    ) : TableCellContent()
}
