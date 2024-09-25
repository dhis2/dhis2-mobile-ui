package org.hisp.dhis.mobile.ui.designsystem.component.internal.modifiers

import androidx.compose.foundation.ScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.min

internal fun Modifier.fadingEdges(
    scrollState: ScrollState,
    topEdgeHeight: Dp = 72.dp,
    bottomEdgeHeight: Dp = 72.dp,
): Modifier = this.then(
    Modifier
        // adding layer fixes issue with blending gradient and content
        .graphicsLayer { alpha = 0.99F }
        .drawWithContent {
            drawContent()

            val topColors = listOf(Color.Transparent, Color.Black)
            val topStartY = scrollState.value.toFloat()
            val topGradientHeight = min(topEdgeHeight.toPx(), topStartY)
            drawRect(
                brush = Brush.verticalGradient(
                    colors = topColors,
                    startY = topStartY,
                    endY = topStartY + topGradientHeight,
                ),
                blendMode = BlendMode.DstIn,
            )

            val bottomColors = listOf(Color.Black, Color.Transparent)
            val bottomEndY = size.height - scrollState.maxValue + scrollState.value
            val bottomGradientHeight =
                min(bottomEdgeHeight.toPx(), scrollState.maxValue.toFloat() - scrollState.value)
            if (bottomGradientHeight != 0f) {
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = bottomColors,
                        startY = bottomEndY - bottomGradientHeight,
                        endY = bottomEndY,
                    ),
                    blendMode = BlendMode.DstIn,
                )
            }
        },
)
