package org.hisp.dhis.mobile.ui.designsystem.files

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.jetbrains.skia.Image
import java.io.File

@Composable
actual fun buildPainterForFile(filePath: String): FilePainterResult =
    try {
        val file = File(filePath)
        val bytes = file.readBytes()
        FilePainterResult(
            painter = BitmapPainter(Image.makeFromEncoded(bytes).toComposeImageBitmap()),
            isUnsupported = false,
        )
    } catch (_: Exception) {
        FilePainterResult(
            painter = provideDHIS2Icon("dhis2_image_not_supported"),
            isUnsupported = true,
        )
    }
