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
import java.util.Locale

private const val DEFAULT_SPREAD = 1.5f
private const val DEFAULT_SIZE = 1.5f
private const val DEFAULT_PADDING = 1.5f

internal fun Modifier.iconButtonshadow(
    color: Color,
    borderRadius: Dp = Spacing.Spacing0,
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
            val topPixel = 0f
            val rightPixel = (this.size.width - Spacing.Spacing0_5.toPx())
            val bottomPixel = (this.size.height + spreadPixel)

            frameworkPaint.color = color.toArgb()
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

internal fun Modifier.buttonShadow(
    color: MutableState<Color> = mutableStateOf(SurfaceColor.ContainerHighest),
    borderRadius: Dp = Spacing.Spacing0,
    hasIcon: Boolean = true,
    modifier: Modifier = Modifier,
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = if (hasIcon) Spacing.Spacing0.toPx() else Spacing.Spacing1_5.toPx()
            frameworkPaint.color = color.value.toArgb()
            var leftPixel = 0f
            var topPixel = 15f
            var rightPixel = (this.size.width)
            var bottomPixel = (this.size.height - spreadPixel)
            val platform = System.getProperty("os.name").lowercase(Locale.getDefault())

            if (platform.contains("win")) {
                leftPixel = 0.5f
                topPixel = 10f
                rightPixel = (this.size.width - 0.5F)
                bottomPixel = (this.size.height - spreadPixel)
            }
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
