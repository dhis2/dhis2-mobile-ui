package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.graphics.NativePaint

internal actual fun NativePaint.paintBlur(blur: Float): NativePaint {
    // No-op for wasm
    return this
}
