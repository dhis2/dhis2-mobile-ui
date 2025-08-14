package org.hisp.dhis.mobile.ui.designsystem.component.internal.image

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.Image.Companion.makeFromEncoded
import platform.Foundation.NSData
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.posix.memcpy

internal actual fun provideImage(filePath: String): ImageBitmap? {
    return try {
        val uiImage = UIImage.imageWithContentsOfFile(filePath) ?: return null
        val nsData = UIImagePNGRepresentation(uiImage) ?: return null
        nsData.toImageBitmap()
    } catch (_: Exception) {
        null
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toImageBitmap(): ImageBitmap {
    val byteArray = ByteArray(this@toImageBitmap.length.toInt())
    byteArray.usePinned { pinned ->
        memcpy(pinned.addressOf(0), this@toImageBitmap.bytes, this@toImageBitmap.length)
    }
    return makeFromEncoded(byteArray).toComposeImageBitmap()
}
