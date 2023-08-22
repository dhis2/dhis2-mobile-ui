package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font

@Composable
actual fun fontResources(
    font: String
): FontFamily = FontFamily(
    Font(resource = "font/$font.ttf")
)
