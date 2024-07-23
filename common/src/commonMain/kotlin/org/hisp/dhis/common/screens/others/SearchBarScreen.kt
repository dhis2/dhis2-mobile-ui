package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SearchBar

@Composable
fun SearchBarScreen() {
    ColumnComponentContainer(title = "Search bar component") {
        ColumnComponentItemContainer("Search bar") {
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

        ColumnComponentItemContainer("Search bar") {
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
