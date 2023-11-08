package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.remember
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
    onActiveChange: (Boolean) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    SearchBar(
        modifier = modifier.testTag("SEARCH_INPUT"),
        query = text,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = false,
        onActiveChange = onActiveChange,
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
                Icon(
                    modifier = Modifier.testTag("CANCEL_BUTTON").clickable {
                        onQueryChange.invoke("")
                    },
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "cancel button",
                )
            } else {
                Icon(
                    modifier = Modifier.testTag("SEARCH_BUTTON"),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search button",
                )
            }
        },
        interactionSource = interactionSource,
        content = {},
    )
}
