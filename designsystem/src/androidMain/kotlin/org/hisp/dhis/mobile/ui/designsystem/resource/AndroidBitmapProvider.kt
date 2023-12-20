package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalView
import kotlin.math.roundToInt

@Composable
actual fun Rect.createBitmap(): ImageBitmap {
    val view = LocalView.current
    val rect = deflate(2f)
    val imageBitmap = ImageBitmap(
        rect.width.roundToInt(),
        rect.height.roundToInt(),
        ImageBitmapConfig.Argb8888,
    )
    val canvas = Canvas(imageBitmap)
        .apply {
            translate(-rect.left, -rect.top)
        }
    view.draw(canvas.nativeCanvas)
    return imageBitmap
}

