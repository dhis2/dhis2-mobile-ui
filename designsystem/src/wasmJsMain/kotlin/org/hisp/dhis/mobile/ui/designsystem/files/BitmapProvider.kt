package org.hisp.dhis.mobile.ui.designsystem.files

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter

@Composable
actual fun buildPainterForFile(filePath: String): Painter {
    // File path access is not available in wasm. Returning a placeholder.
    return ColorPainter(Color.Transparent)
}
