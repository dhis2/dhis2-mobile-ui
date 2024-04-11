package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.Badge
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ErrorBadge

@Composable
fun BadgesScreen() {
    ColumnComponentContainer(title = "Badges") {
        Badge()
        Badge(text = "32")
    }
    ColumnComponentContainer(title = "Error badges") {
        ErrorBadge()
        ErrorBadge(text = "32")
    }
}
