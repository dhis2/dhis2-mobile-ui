package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font

actual val fontResource: FontFamily
    get() = FontFamily(
        Font(
            resource = "font/roboto_regular.ttf"
        )
    )
