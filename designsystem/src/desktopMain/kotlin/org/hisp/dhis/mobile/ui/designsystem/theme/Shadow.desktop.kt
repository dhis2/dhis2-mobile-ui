package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter

internal actual fun NativePaint.paintBlur(blur: Float): NativePaint = apply {
    // Apply blur to the Paint
    imageFilter = ImageFilter.makeBlur(
        blur, blur,
        FilterTileMode.DECAL,
    )
}
