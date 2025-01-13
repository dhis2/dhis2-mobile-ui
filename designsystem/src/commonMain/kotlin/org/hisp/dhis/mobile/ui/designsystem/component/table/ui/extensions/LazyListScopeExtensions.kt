package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

@ExperimentalFoundationApi
internal fun LazyListScope.fixedStickyHeader(
    fixHeader: Boolean = true,
    key: Any? = null,
    contentType: Any? = null,
    content: @Composable LazyItemScope.() -> Unit,
) {
    if (fixHeader) {
        stickyHeader("${key}_sticky", contentType = contentType, content = content)
    } else {
        item("${key}_non_sticky", contentType = contentType, content = content)
    }
}
