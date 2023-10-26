package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    placeHolderText: String = "Search",
    onActiveChange: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
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
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "cancel icon",
                    )
                } else {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "search icon"
                    )
                }},
            content = {}
        )
    }
}
