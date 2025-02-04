package org.hisp.dhis.mobile.ui.designsystem.component.internal.modifiers

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal actual fun Modifier.draggableList(
    coroutineScope: CoroutineScope,
    scrollState: LazyListState,
): Modifier {
    return this.then(
        Modifier.pointerInput(Unit) {
            detectVerticalDragGestures(
                onVerticalDrag = { _, dragAmount ->
                    coroutineScope.launch {
                        scrollState.scrollBy(-dragAmount)
                    }
                },
            )
        },
    )
}
