package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.ScrollState

internal sealed class HorizontalScrollConfig {
    data class Grouped(val scrollState: ScrollState) : HorizontalScrollConfig()
    data class Individual(val scrollStates: List<ScrollState>) : HorizontalScrollConfig()

    fun getScrollState(index: Int): ScrollState {
        return when (this) {
            is Grouped -> scrollState
            is Individual -> scrollStates[index]
        }
    }
}
