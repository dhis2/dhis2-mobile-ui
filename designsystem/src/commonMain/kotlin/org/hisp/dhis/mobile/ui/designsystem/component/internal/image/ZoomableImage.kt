package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize

@Composable
fun ZoomableImage(
    painter: Painter,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .clipToBounds()
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, _ ->
                    scale = maxOf(1f, scale * zoom)
                    offset = offset.calculateNewOffset(
                        centroid, pan, scale, zoom, size
                    )
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { tapOffset ->
                        scale = if (scale > 1f) 1f else 2f
                        offset = calculateDoubleTapOffset(scale, size, tapOffset)
                    }
                )
            }
            .graphicsLayer {
                translationX = -offset.x * scale
                translationY = -offset.y * scale
                scaleX = scale
                scaleY = scale
                transformOrigin = TransformOrigin(0f, 0f)
            }
    )
}

fun Offset.calculateNewOffset(
    centroid: Offset,
    pan: Offset,
    scale: Float,
    gestureZoom: Float,
    size: IntSize
): Offset {
    val newScale = maxOf(1f, scale * gestureZoom)
    val newOffset = (this + centroid / scale) -
            (centroid / newScale + pan / scale)
    return Offset(
        newOffset.x.coerceIn(0f, (size.width / scale) * (scale - 1f)),
        newOffset.y.coerceIn(0f, (size.height / scale) * (scale - 1f))
    )
}


fun calculateDoubleTapOffset(
    scale: Float,
    size: IntSize,
    tapOffset: Offset
): Offset {
    val newOffset = Offset(tapOffset.x, tapOffset.y)
    return Offset(
        newOffset.x.coerceIn(0f, (size.width / scale) * (scale - 1f) / 2f),
        newOffset.y.coerceIn(0f, (size.height / scale) * (scale - 1f) / 2f)
    )
}