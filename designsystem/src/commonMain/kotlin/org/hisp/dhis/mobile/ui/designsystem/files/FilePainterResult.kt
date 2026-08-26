package org.hisp.dhis.mobile.ui.designsystem.files

import androidx.compose.ui.graphics.painter.Painter

/**
 * @param painter: the painter to render, either the decoded file or the fallback icon.
 * @param isUnsupported: true when [painter] is the fallback "not supported" icon
 * because the file could not be read or decoded.
 */
data class FilePainterResult(
    val painter: Painter,
    val isUnsupported: Boolean,
)
