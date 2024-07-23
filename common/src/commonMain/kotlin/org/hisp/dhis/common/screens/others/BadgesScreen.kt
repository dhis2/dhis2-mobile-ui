package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.Badge
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ErrorBadge

@Composable
fun BadgesScreen() {
    ColumnScreenContainer(title = "Badge component") {
        ColumnComponentContainer("Basic badges") {
            Badge()
            Badge(text = "3")
            Badge(text = "32")
            Badge(text = "321")
            Badge(text = "4321")
        }
        ColumnComponentContainer("Error badges") {
            ErrorBadge()
            ErrorBadge(text = "3")
            ErrorBadge(text = "32")
            ErrorBadge(text = "321")
            ErrorBadge(text = "4321")
        }
    }
}
