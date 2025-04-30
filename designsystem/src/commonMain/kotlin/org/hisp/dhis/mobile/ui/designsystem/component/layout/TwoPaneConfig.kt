package org.hisp.dhis.mobile.ui.designsystem.component.layout

import androidx.compose.ui.unit.Dp

sealed class TwoPaneConfig {
    data class Weight(val primaryPaneWeight: Float) : TwoPaneConfig()
    data class PrimaryPaneFixedSize(val size: Dp) : TwoPaneConfig()
    data class SecondaryPaneFixedSize(val size: Dp) : TwoPaneConfig()
}
