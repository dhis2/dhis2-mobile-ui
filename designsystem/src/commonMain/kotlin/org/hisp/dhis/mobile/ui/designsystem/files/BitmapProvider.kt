package org.hisp.dhis.mobile.ui.designsystem.files

import androidx.compose.runtime.Composable

@Composable
expect fun buildPainterForFile(filePath: String): FilePainterResult
