package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val DEFAULT_SPREAD = InternalFloatValues.One_5
private val DEFAULT_SIZE = InternalFloatValues.One_5
private val DEFAULT_PADDING = InternalFloatValues.One_5

internal fun Modifier.iconButtonshadow(
    color: MutableState<Color> = mutableStateOf(SurfaceColor.ContainerHighest),
    borderRadius: Dp = Radius.NoRounding,
    spread: Dp = DEFAULT_SPREAD.dp,
    modifier: Modifier = Modifier,
    size: Dp = DEFAULT_SIZE.dp,
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = DEFAULT_PADDING
            val topPixel = InternalFloatValues.Zero
            val rightPixel = (this.size.width - InternalFloatValues.Point_5)
            val bottomPixel = (this.size.height + spreadPixel)

            frameworkPaint.color = color.value.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint,
            )
        }
    }.size(size),
)

expect val leftPixel: Float
expect val topPixel: Float
expect val spreadPixel: Float
internal fun Modifier.buttonShadow(
    color: MutableState<Color> = mutableStateOf(SurfaceColor.ContainerHighest),
    borderRadius: Dp = Radius.NoRounding,
    modifier: Modifier = Modifier,
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = color.value.toArgb()

            val rightPixel = (this.size.width - leftPixel)
            val bottomPixel = (this.size.height - spreadPixel)
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint,
            )
        }
    },
)
