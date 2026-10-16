package org.hisp.dhis.mobile.ui.designsystem.component.layout

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun FadingLazyRow(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    overscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
    edgeWidth: Dp = 8.dp,
    content: LazyListScope.() -> Unit,
) {
    LazyRow(
        modifier =
            modifier
                .horizontalFadingEdges(
                    state = state,
                    edgeWidth = edgeWidth,
                    reverseLayout = reverseLayout,
                ),
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        overscrollEffect = overscrollEffect,
        content = content,
    )
}

@Composable
fun FadingLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    overscrollEffect: OverscrollEffect? = rememberOverscrollEffect(),
    edgeWidth: Dp = 8.dp,
    content: LazyListScope.() -> Unit,
) {
    LazyColumn(
        modifier =
            modifier
                .verticalFadingEdges(
                    state = state,
                    edgeHeight = edgeWidth,
                    reverseLayout = reverseLayout,
                ),
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        overscrollEffect = overscrollEffect,
        content = content,
    )
}

/**
* Fades out the start/end edges of a horizontally scrollable list when there is more content
* to scroll towards that side. Uses a DstIn mask so it works over any background.
*/
private fun Modifier.horizontalFadingEdges(
    state: LazyListState,
    edgeWidth: Dp,
    reverseLayout: Boolean,
) = graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
    .drawWithContent {
        drawContent()
        val edgePx = edgeWidth.toPx().coerceAtMost(size.width / 2)
        // RTL and reverseLayout each mirror the scroll direction; applying both cancels out.
        val isMirrored = (layoutDirection == LayoutDirection.Rtl) != reverseLayout
        val fadeLeft = if (isMirrored) state.canScrollForward else state.canScrollBackward
        val fadeRight = if (isMirrored) state.canScrollBackward else state.canScrollForward

        if (fadeLeft) {
            drawRect(
                brush =
                    Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startX = 0f,
                        endX = edgePx,
                    ),
                size = Size(edgePx, size.height),
                blendMode = BlendMode.DstIn,
            )
        }
        if (fadeRight) {
            drawRect(
                brush =
                    Brush.horizontalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startX = size.width - edgePx,
                        endX = size.width,
                    ),
                topLeft = Offset(size.width - edgePx, 0f),
                size = Size(edgePx, size.height),
                blendMode = BlendMode.DstIn,
            )
        }
    }

/**
* Fades out the top/bottom edges of a vertically scrollable list when there is more content
* to scroll towards that side. Uses a DstIn mask so it works over any background.
*/
private fun Modifier.verticalFadingEdges(
    state: LazyListState,
    edgeHeight: Dp,
    reverseLayout: Boolean,
) = graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
    .drawWithContent {
        drawContent()
        val edgePx = edgeHeight.toPx().coerceAtMost(size.height / 2)
        val fadeTop = if (reverseLayout) state.canScrollForward else state.canScrollBackward
        val fadeBottom = if (reverseLayout) state.canScrollBackward else state.canScrollForward

        if (fadeTop) {
            drawRect(
                brush =
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = edgePx,
                    ),
                size = Size(size.width, edgePx),
                blendMode = BlendMode.DstIn,
            )
        }
        if (fadeBottom) {
            drawRect(
                brush =
                    Brush.verticalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startY = size.height - edgePx,
                        endY = size.height,
                    ),
                topLeft = Offset(0f, size.height - edgePx),
                size = Size(size.width, edgePx),
                blendMode = BlendMode.DstIn,
            )
        }
    }
