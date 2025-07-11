package org.hisp.dhis.mobile.ui.designsystem.component.model

sealed class TabStyle {
    data object LabelOnly : TabStyle()

    data object IconOnly : TabStyle()

    data object IconAndLabel : TabStyle()
}
