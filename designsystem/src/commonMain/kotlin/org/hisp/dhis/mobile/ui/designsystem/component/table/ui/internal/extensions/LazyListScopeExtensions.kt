package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

@ExperimentalFoundationApi
internal fun LazyListScope.fixedStickyHeader(
    key: Any? = null,
    contentType: Any? = null,
    content: @Composable LazyItemScope.(index: Int) -> Unit,
) {
    stickyHeader("${key}_sticky", contentType = contentType, content = content)
}
