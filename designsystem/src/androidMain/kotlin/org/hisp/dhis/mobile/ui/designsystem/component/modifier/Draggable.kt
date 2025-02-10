package org.hisp.dhis.mobile.ui.designsystem.component.modifier

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope

@Composable
actual fun Modifier.draggableList(
    coroutineScope: CoroutineScope,
    scrollState: LazyListState,
): Modifier {
    return this
}
