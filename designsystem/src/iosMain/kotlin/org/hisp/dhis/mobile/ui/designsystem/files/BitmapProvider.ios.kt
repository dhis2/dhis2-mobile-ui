package org.hisp.dhis.mobile.ui.designsystem.files

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.jetbrains.skia.Image
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.posix.memcpy

@Composable
actual fun buildPainterForFile(filePath: String): Painter {
    try {
        // 1. Get NSData from the file path
        // UIImage needs data from a file URL or NSData.
        // NSFileManager is used to check if the file exists and get its contents.
        val fileManager = NSFileManager.defaultManager
        if (!fileManager.fileExistsAtPath(filePath)) {
            throw Exception("file not found")
        }

        // Create an NSURL from the file path.
        // Note: Using fileURLWithPath is generally safer for local file paths.
        val fileURL = NSURL.fileURLWithPath(filePath)
        val imageData = NSData.dataWithContentsOfURL(fileURL) // This can return null

        if (imageData == null) {
            throw Exception("file not found")
        }

        // 2. Create UIImage from NSData
        val uiImage = UIImage.imageWithData(imageData)

        return if (uiImage != null) {
            // 3. Convert UIImage to Compose ImageBitmap
            // (You'll need an expect/actual or a helper for this conversion if one doesn't exist directly)
            // Assuming you have a way to convert UIImage to ImageBitmap, for example:
            BitmapPainter(uiImage.toComposeImageBitmap()) // This is a hypothetical extension
        } else {
            throw Exception("file not found")
        }
    } catch (_: Exception) {
        // Log the exception if needed: println("Error loading image: ${e.message}")
        return provideDHIS2Icon("dhis2_image_not_supported")
    }
}

@OptIn(ExperimentalForeignApi::class)
fun UIImage.toComposeImageBitmap(): androidx.compose.ui.graphics.ImageBitmap {
    val data = UIImagePNGRepresentation(this) ?: error("Could not get PNG representation")
    return Image.makeFromEncoded(data.toByteArray()).toComposeImageBitmap()
}

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray {
    val bytes = this.bytes ?: error("NSData has no bytes")
    val length = this.length.toInt()
    val byteArray = ByteArray(length)
    memcpy(byteArray.refTo(0), bytes, length.toULong())
    return byteArray
}
