package org.hisp.dhis.mobile.ui.designsystem.files

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import java.io.File

@Composable
actual fun buildPainterForFile(filePath: String): FilePainterResult =
    try {
        val file = File(filePath)
        FilePainterResult(
            painter = BitmapPainter(BitmapFactory.decodeFile(file.absolutePath).asImageBitmap()),
            isUnsupported = false,
        )
    } catch (_: Exception) {
        FilePainterResult(
            painter = provideDHIS2Icon("dhis2_image_not_supported"),
            isUnsupported = true,
        )
    }
