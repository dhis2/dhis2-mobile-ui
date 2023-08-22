package org.hisp.dhis.mobile.ui.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

@Composable
actual fun fontResources(font: String): FontFamily {
    val context = LocalContext.current
    val name = font.substringBefore(".")
    val fontRes = context.resources.getIdentifier(name, "font", context.packageName)
    return FontFamily(Font(fontRes))
}
