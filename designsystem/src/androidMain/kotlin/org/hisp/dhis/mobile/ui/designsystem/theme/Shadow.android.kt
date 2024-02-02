package org.hisp.dhis.mobile.ui.designsystem.theme

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb

internal actual fun Modifier.listCardShadow(modifier: Modifier): Modifier = this.then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()

            frameworkPaint.maskFilter = (BlurMaskFilter(Spacing.Spacing10.toPx(), BlurMaskFilter.Blur.NORMAL))

            frameworkPaint.color = SurfaceColor.Container.toArgb()

            val leftPixel = Spacing.Spacing0.toPx()
            val topPixel = Spacing.Spacing4.toPx()
            val rightPixel = size.width + topPixel
            val bottomPixel = size.height + leftPixel

            canvas.drawRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                paint = paint,
            )
        }
    },
)
