package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.ui.graphics.ImageBitmap

internal actual fun provideImage(filePath: String): ImageBitmap? {
    // File path access is not available in wasm. Returning null.
    return null
}
