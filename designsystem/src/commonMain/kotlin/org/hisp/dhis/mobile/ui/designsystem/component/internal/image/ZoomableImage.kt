package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize

@Composable
internal fun ZoomableImage(
    painter: Painter,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    var isDoubleTapped by remember { mutableStateOf(false) }
    val transition = updateTransition(isDoubleTapped)

    val animatedScale by transition.animateFloat(
        transitionSpec = { getAnimationSpec(isDoubleTapped) },
    ) { scale }

    val animatedOffset by transition.animateOffset(
        transitionSpec = { getAnimationSpec(isDoubleTapped) },
    ) { offset }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = contentScale,
        modifier =
            modifier
                .clipToBounds()
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, _ ->
                        isDoubleTapped = false
                        scale = maxOf(1f, scale * zoom)
                        offset =
                            offset.calculateNewOffset(
                                centroid,
                                pan,
                                scale,
                                zoom,
                                size,
                            )
                    }
                }.pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { tapOffset ->
                            isDoubleTapped = true
                            val srcSize = Size(painter.intrinsicSize.width, painter.intrinsicSize.height)
                            scale = calculateDoubleTapScale(scale, srcSize, size)
                            offset = calculateDoubleTapOffset(scale, size, tapOffset)
                        },
                    )
                }.graphicsLayer {
                    translationX = -animatedOffset.x * animatedScale
                    translationY = -animatedOffset.y * animatedScale
                    scaleX = animatedScale
                    scaleY = animatedScale
                    transformOrigin = TransformOrigin(0f, 0f)
                },
    )
}

@Composable
private fun <T> getAnimationSpec(showAnimation: Boolean): FiniteAnimationSpec<T> =
    if (showAnimation) {
        spring(stiffness = Spring.StiffnessLow)
    } else {
        tween(0)
    }

private fun Offset.calculateNewOffset(
    centroid: Offset,
    pan: Offset,
    scale: Float,
    gestureZoom: Float,
    size: IntSize,
): Offset {
    val newScale = maxOf(1f, scale * gestureZoom)
    val newOffset = (this + centroid / scale) - (centroid / newScale + pan / scale)
    return Offset(
        newOffset.x.coerceIn(0f, (size.width / scale) * (scale - 1f)),
        newOffset.y.coerceIn(0f, (size.height / scale) * (scale - 1f)),
    )
}

private fun calculateDoubleTapOffset(
    scale: Float,
    size: IntSize,
    tapOffset: Offset,
): Offset {
    val newOffset = Offset(tapOffset.x, tapOffset.y)
    return Offset(
        newOffset.x.coerceIn(0f, (size.width / scale) * (scale - 1f) / 2f),
        newOffset.y.coerceIn(0f, (size.height / scale) * (scale - 1f) / 2f),
    )
}

private fun calculateDoubleTapScale(
    currentScale: Float,
    srcSize: Size,
    size: IntSize,
): Float {
    val scaleToNormal = currentScale > 1f
    return if (scaleToNormal) {
        1f
    } else {
        val scaleFactor = ContentScale.FillBounds.computeScaleFactor(srcSize, size.toSize())
        maxOf(
            maxOf(scaleFactor.scaleX, scaleFactor.scaleY) / minOf(scaleFactor.scaleX, scaleFactor.scaleY),
            2f,
        )
    }
}
