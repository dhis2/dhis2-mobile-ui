package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.io.File

actual fun provideImage(filePath: String): ImageBitmap? =
    try {
        Image.makeFromEncoded(File(filePath).readBytes()).toComposeImageBitmap()
    } catch (_: Exception) {
        null
    }
