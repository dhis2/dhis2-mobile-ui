package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.extensions

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState

//todo Update methods to reflect possible new bottomsheet they currently cause exceptions
@OptIn(ExperimentalMaterial3Api::class)
suspend fun SheetState.collapseIfExpanded(onCollapse: () -> Unit) {
    if (this.hasExpandedState) {
        collapseIfExpanded { onCollapse() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
suspend fun SheetState.expandIfCollapsed(onExpand: () -> Unit) {
    if (this.hasExpandedState.not()) {
        onExpand()
        expand()
    }
}
