package org.hisp.dhis.mobile.ui.designsystem.component.internal.clipboard

import androidx.compose.ui.platform.ClipEntry

actual suspend fun ClipEntry.getText(): String? =
    try {
        val itemCount = clipData.itemCount
        var textFull = ""
        for (i in 0..<itemCount) {
            val item = clipData.getItemAt(i)
            val text = item?.text
            if (text != null) {
                textFull += text
            }
        }
        textFull.ifEmpty { null }
    } catch (_: Exception) {
        null
    }
