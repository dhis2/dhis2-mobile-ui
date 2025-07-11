package org.hisp.dhis.mobile.ui.designsystem.component.model

sealed class DraggableType {
    data object Horizontal : DraggableType()

    data object Vertical : DraggableType()
}
