package org.hisp.dhis.mobile.ui.designsystem.theme

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

internal actual fun Modifier.shadow(
    elevation: Dp,
    blur: Dp,
    radius: Dp,
    spotColor: Color,
): Modifier = this.then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()

            frameworkPaint.maskFilter = (BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL))

            frameworkPaint.color = spotColor.toArgb()

            val leftPixel = Spacing.Spacing0.toPx()
            val topPixel = elevation.toPx()
            val rightPixel = size.width + leftPixel
            val bottomPixel = size.height + topPixel

            canvas.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                paint = paint,
                radiusX = radius.toPx(),
                radiusY = radius.toPx(),
            )
        }
    },
)
