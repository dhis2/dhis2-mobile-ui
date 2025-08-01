package org.hisp.dhis.mobile.ui.designsystem.files

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.jetbrains.skia.Image
import java.io.File

@Composable
actual fun buildPainterForFile(filePath: String): Painter {
    try {
        val file = File(filePath)
        val bytes = file.readBytes()
        return BitmapPainter(Image.makeFromEncoded(bytes).toComposeImageBitmap())
    } catch (_: Exception) {
        return provideDHIS2Icon("dhis2_image_not_supported")
    }
}
