package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import androidx.compose.ui.geometry.Offset

/**
 * Data class representing the resizing cell.
 *
 * @property initialPosition The initial position of the cell.
 * @property draggingOffsetX The dragging offset of the cell.
 */
internal data class ResizingCell(
    val initialPosition: Offset,
    val draggingOffsetX: Float,
)
