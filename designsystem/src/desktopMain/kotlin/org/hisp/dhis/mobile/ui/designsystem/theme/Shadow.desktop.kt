package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.skiaPaint
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter

internal actual fun Paint.paintBlur(blur: Float) {
    skiaPaint.imageFilter =
        ImageFilter.makeBlur(
            blur,
            blur,
            FilterTileMode.DECAL,
        )
}
