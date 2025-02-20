package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import kotlinx.serialization.Serializable

/**
 * Data class representing the header of a row in a table.
 *
 * @property id The unique identifier of the row header.
 * @property title The title of the row header.
 * @property row The row index of the header.
 * @property showDecoration A flag indicating whether the decoration icon should be shown.
 * @property description The description of the row header.
 */
@Serializable
data class RowHeader(
    val id: String,
    val title: String,
    val row: Int,
    val showDecoration: Boolean = false,
    val description: String? = null,
)
