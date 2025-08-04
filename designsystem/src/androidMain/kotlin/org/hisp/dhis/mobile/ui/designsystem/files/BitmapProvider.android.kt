package org.hisp.dhis.mobile.ui.designsystem.files

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import java.io.File

@Composable
actual fun buildPainterForFile(filePath: String): Painter {
    try {
        val file = File(filePath)
        return BitmapPainter(
            BitmapFactory.decodeFile(file.absolutePath).asImageBitmap(),
        )
    } catch (_: Exception) {
        return provideDHIS2Icon("dhis2_image_not_supported")
    }
}
