package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SearchBar
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun SearchBarScreen() {
    ColumnComponentContainer {
        var text by rememberSaveable {
            mutableStateOf("")
        }

        var text2 by rememberSaveable {
            mutableStateOf("Input")
        }

        Title("Search bar component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Search bar", textColor = TextColor.OnSurfaceVariant)

        SearchBar(
            text = text,
            onQueryChange = {
                text = it
            },
        )

        Title("Search bar component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Search bar", textColor = TextColor.OnSurfaceVariant)

        SearchBar(
            text = text2,
            onQueryChange = {
                text2 = it
            },
        )
    }
}
