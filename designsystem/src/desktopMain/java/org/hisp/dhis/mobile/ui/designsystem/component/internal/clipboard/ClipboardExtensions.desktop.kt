package org.hisp.dhis.mobile.ui.designsystem.component.internal.clipboard

import kotlinx.coroutines.withContext
import org.hisp.dhis.mobile.ui.designsystem.platform.dispatcher.ioDispatcher
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

internal fun copyToClipboard(text: String) {
    val selection = StringSelection(text)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}

internal suspend fun pasteFromClipboard(): String? {
    return withContext(ioDispatcher) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            clipboard.getData(DataFlavor.stringFlavor) as? String
        } else {
            null
        }
    }
}
