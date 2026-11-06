package org.hisp.dhis.mobile.ui.designsystem.theme

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativePaint

internal actual fun Paint.paintBlur(blur: Float) {
    nativePaint.maskFilter = BlurMaskFilter(blur, BlurMaskFilter.Blur.NORMAL)
}
