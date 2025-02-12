package org.hisp.dhis.mobile.ui.designsystem.component.modifier

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType

@Composable
actual fun Modifier.draggableList(
    coroutineScope: CoroutineScope,
    scrollState: ScrollableState,
    draggableType: DraggableType,
): Modifier = this
