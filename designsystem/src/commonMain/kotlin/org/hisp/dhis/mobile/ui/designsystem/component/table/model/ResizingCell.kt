package org.hisp.dhis.mobile.ui.designsystem.component.table.model

import androidx.compose.ui.geometry.Offset

data class ResizingCell(
    val initialPosition: Offset,
    val draggingOffsetX: Float,
)
