package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
actual fun captureBitmap(
    content: @Composable () -> Unit,
): () -> ImageBitmap {
    fun captureBitmap(): ImageBitmap {
        return ImageBitmap(200, 200)
    }

    content.invoke()

    return ::captureBitmap
}
