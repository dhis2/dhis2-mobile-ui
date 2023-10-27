package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    placeHolderText: String = "Search",
    onActiveChange: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    onClear: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    Box() {
        SearchBar(
            modifier = modifier,
            query = text,
            onQueryChange = {
                text = it
                onQueryChange.invoke(it)
            },
            onSearch = {
                onSearch(it)
            },
            active = false,
            onActiveChange = {
                onActiveChange.invoke()
            },
            placeholder = { Text(placeHolderText) },
            trailingIcon = {
                if (text != "") {

                    IconButton(
                        modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + CLEAR_BUTTON_TEST_TAG),
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = "cancel button",
                            )
                        },
                        onClick = {
                            text = ""
                            onClear.invoke()
                        },
                    )
                } else {
                    IconButton(
                        modifier = Modifier.testTag(INPUT_FILE_TEST_TAG + CLEAR_BUTTON_TEST_TAG),
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "search button",
                            )
                        },
                        onClick = {},
                    )

                }},
            content = {}
        )
    }
}
