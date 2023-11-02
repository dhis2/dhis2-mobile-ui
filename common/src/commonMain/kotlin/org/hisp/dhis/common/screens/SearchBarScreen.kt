package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SearchBar
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun SearchBarScreen() {
    ColumnComponentContainer {
        Title("Search bar component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Search bar", textColor = TextColor.OnSurfaceVariant)

        SearchBar()

        Title("Search bar component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Search bar", textColor = TextColor.OnSurfaceVariant)

        SearchBar(text = "Input")
    }
}
