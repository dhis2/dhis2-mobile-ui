package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String = "",
    placeHolderText: String = "Search",
    onActiveChange: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    onClear: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var text by rememberSaveable { mutableStateOf(text) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    SearchBar(
        modifier = modifier.testTag("SEARCH_INPUT"),
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
        colors = if (!isPressed) {
            SearchBarDefaults.colors(containerColor = SurfaceColor.ContainerLow)
        } else {
            SearchBarDefaults.colors(containerColor = SurfaceColor.Container)
        },
        placeholder = {
            Text(
                text = placeHolderText,
                color = TextColor.OnDisabledSurface,
            )
        },
        trailingIcon = {
            if (text != "") {
                IconButton(
                    modifier = Modifier.testTag("CANCEL_BUTTON"),
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
                    modifier = Modifier.testTag("SEARCH_BUTTON"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search button",
                        )
                    },
                    onClick = {},
                )
            }
        },
        interactionSource = interactionSource,
        content = {},
    )
}
