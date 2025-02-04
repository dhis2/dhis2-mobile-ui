package org.hisp.dhis.mobile.ui.designsystem.component.internal.modifiers

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope

@Composable
internal expect fun Modifier.draggableList(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scrollState: LazyListState,
): Modifier
