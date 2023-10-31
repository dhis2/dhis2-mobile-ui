package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import org.hisp.dhis.mobile.ui.designsystem.theme.Ripple
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
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf(text) }
    var interactionSource = remember { MutableInteractionSource() }


    Box {
            SearchBar(
                modifier = modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {}
                ),
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
                colors = SearchBarDefaults.colors(containerColor = SurfaceColor.ContainerLow),
                placeholder = {
                    Text(
                        text = placeHolderText,
                        color = TextColor.OnDisabledSurface
                    )
                },
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

                    }
                },
                interactionSource = interactionSource,
                content = {}
            )
    }
}
