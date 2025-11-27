package org.hisp.dhis.mobile.ui.designsystem.component.internal.clipboard

import androidx.compose.ui.platform.ClipEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable

actual suspend fun ClipEntry.getText(): String? =
    withContext(Dispatchers.IO) {
        try {
            val transferable = nativeClipEntry as? Transferable
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                transferable.getTransferData(DataFlavor.stringFlavor) as? String
            } else {
                null
            }
        } catch (_: Exception) {
            null
        }
    }
