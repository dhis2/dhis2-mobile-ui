package org.hisp.dhis.mobile.ui.designsystem.files

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun buildPainterForFile(filePath: String): Painter
