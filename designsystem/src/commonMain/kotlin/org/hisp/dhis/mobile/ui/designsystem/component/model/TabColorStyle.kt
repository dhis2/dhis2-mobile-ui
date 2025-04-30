package org.hisp.dhis.mobile.ui.designsystem.component.model

sealed class TabColorStyle() {
    data object Tonal : TabColorStyle()
    data object Primary : TabColorStyle()
}
