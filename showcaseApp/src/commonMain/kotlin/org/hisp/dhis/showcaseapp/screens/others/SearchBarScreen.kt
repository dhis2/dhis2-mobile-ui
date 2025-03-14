package org.hisp.dhis.showcaseapp.screens.others

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SearchBar

@Composable
fun SearchBarScreen() {
    ColumnScreenContainer(title = "Search bar component") {
        ColumnComponentContainer("Search bar") {
            var text by rememberSaveable {
                mutableStateOf("")
            }
            SearchBar(
                text = text,
                onQueryChange = {
                    text = it
                },
            )
        }

        ColumnComponentContainer("Search bar") {
            var text2 by rememberSaveable {
                mutableStateOf("Input")
            }
            SearchBar(
                text = text2,
                onQueryChange = {
                    text2 = it
                },
            )
        }
    }
}
