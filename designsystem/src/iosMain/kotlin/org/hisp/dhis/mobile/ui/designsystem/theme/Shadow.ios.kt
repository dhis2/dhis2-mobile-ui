package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.graphics.NativePaint
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
internal actual fun NativePaint.paintBlur(blur: Float): NativePaint =
    apply {
        imageFilter =
            ImageFilter.makeBlur(
                blur,
                blur,
                FilterTileMode.DECAL,
            )
    }
