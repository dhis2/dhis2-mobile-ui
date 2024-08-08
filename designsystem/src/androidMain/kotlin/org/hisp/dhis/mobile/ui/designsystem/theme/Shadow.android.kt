package org.hisp.dhis.mobile.ui.designsystem.theme

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.NativePaint

internal actual fun NativePaint.paintBlur(blur: Float): NativePaint = apply {
    // Apply blur to the Paint
    maskFilter = BlurMaskFilter(blur, BlurMaskFilter.Blur.NORMAL)
}
