package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.extensions

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListState
// todo review whether this method is still needed or not
suspend fun LazyListState.animateScrollToVisibleItems() {
    animateScrollBy(layoutInfo.viewportSize.height / 2f)
}
