package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter

internal actual fun NativePaint.paintBlur(blur: Float): NativePaint =
    apply {
        imageFilter =
            ImageFilter.makeBlur(
                blur,
                blur,
                FilterTileMode.DECAL,
            )
    }
