package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image
import java.io.File

actual fun provideImage(file: File): ImageBitmap? {
    return try {
        Image.makeFromEncoded(file.readBytes()).toComposeImageBitmap()
    } catch (ex: Exception) {
        null
    }
}
