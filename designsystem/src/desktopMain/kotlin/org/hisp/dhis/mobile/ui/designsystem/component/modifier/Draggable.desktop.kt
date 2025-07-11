package org.hisp.dhis.mobile.ui.designsystem.component.modifier

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType

@Composable
actual fun Modifier.draggableList(
    coroutineScope: CoroutineScope,
    scrollState: ScrollableState,
    draggableType: DraggableType,
): Modifier =
    this.then(
        Modifier.pointerInput(Unit) {
            when (draggableType) {
                is DraggableType.Vertical ->
                    detectVerticalDragGestures(
                        onVerticalDrag = { _, dragAmount ->
                            coroutineScope.launch {
                                scrollState.scrollBy(-dragAmount)
                            }
                        },
                    )

                is DraggableType.Horizontal ->
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            coroutineScope.launch {
                                scrollState.scrollBy(-dragAmount)
                            }
                        },
                    )
            }
        },
    )
