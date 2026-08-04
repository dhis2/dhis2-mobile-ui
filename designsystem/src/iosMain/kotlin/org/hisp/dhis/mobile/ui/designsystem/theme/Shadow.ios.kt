package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.skiaPaint
import kotlinx.cinterop.ExperimentalForeignApi
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter

actual val leftPixel: Float
    get() = 0f
actual val topPixel: Float
    get() = 15f
actual val spreadPixel: Float
    get() = 3f

@OptIn(ExperimentalForeignApi::class)
internal actual fun Paint.paintBlur(blur: Float) {
    skiaPaint.imageFilter =
        ImageFilter.makeBlur(
            blur,
            blur,
            FilterTileMode.DECAL,
        )
}
