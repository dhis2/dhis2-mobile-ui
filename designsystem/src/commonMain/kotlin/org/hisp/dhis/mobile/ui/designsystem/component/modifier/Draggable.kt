package org.hisp.dhis.mobile.ui.designsystem.component.modifier

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType

@Composable
expect fun Modifier.draggableList(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scrollState: ScrollableState,
    draggableType: DraggableType,
): Modifier