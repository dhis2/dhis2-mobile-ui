package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.skiaPaint
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter

internal actual val leftPixel: Float
    get() = 0f
internal actual val topPixel: Float
    get() = 15f
internal actual val spreadPixel: Float
    get() = 3f

internal actual fun Paint.paintBlur(blur: Float) {
    skiaPaint.imageFilter =
        ImageFilter.makeBlur(
            blur,
            blur,
            FilterTileMode.DECAL,
        )
}
