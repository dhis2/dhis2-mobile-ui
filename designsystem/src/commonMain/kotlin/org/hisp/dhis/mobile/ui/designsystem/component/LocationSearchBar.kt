package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.modifiers.clickableWithRipple
import org.hisp.dhis.mobile.ui.designsystem.component.model.DraggableType
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel
import org.hisp.dhis.mobile.ui.designsystem.component.modifier.draggableList
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2SCustomTextStyles
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing16
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 Location Bar modes
 * BUTTON: the Location Bar is displayed as a button and shows current search query if available.
 * SEARCH: The Location Bar is displayed as an input and displays available location items.
 */
enum class SearchBarMode {
    BUTTON,
    SEARCH,
}

/**
 * DHIS2 Location Bar search actions
 * Default: The user will need to select a result to mark it as selected
 * OnOneItemSelect: If only one item is available, it will be selected automatically
 * */
enum class OnSearchAction {
    Default,
    OnOneItemSelect,
}

/**
 * DHIS2 Location Bar.
 * @param currentResults: the available location items to display before/after search.
 * @param mode: the initial mode for the composable.
 * @param searchAction: How the search result selection is carried out.
 * @param searching: whether the search is currently in progress.
 * @param onBackClicked: callback for when the back button is clicked.
 * @param onClearLocation: callback for when the clear location button is clicked.
 * @param onSearchLocation: callback for when the search location button is clicked.
 * @param onLocationSelected: callback for when a location item is selected.
 * @param onModeChanged: optional callback for when the mode is changed.
 */
@Composable
fun LocationBar(
    currentResults: List<LocationItemModel>,
    mode: SearchBarMode = SearchBarMode.BUTTON,
    searchAction: OnSearchAction = OnSearchAction.Default,
    searching: Boolean,
    onBackClicked: () -> Unit,
    onClearLocation: () -> Unit,
    onSearchLocation: (query: String) -> Unit,
    onLocationSelected: (LocationItemModel) -> Unit,
    onModeChanged: (currentMode: SearchBarMode) -> Unit = {},
) {
    var currentMode by remember { mutableStateOf(mode) }
    var currentSearch: String by remember { mutableStateOf("") }
    fun selectItem(item: LocationItemModel) {
        currentSearch = item.title
        currentMode = SearchBarMode.BUTTON
        onLocationSelected(item)
    }

    LaunchedEffect(currentMode) {
        onModeChanged(currentMode)
    }

    LaunchedEffect(searchAction, currentResults) {
        if (searchAction == OnSearchAction.OnOneItemSelect) {
            currentResults.filterIsInstance<LocationItemModel.SearchResult>()
                .takeIf { it.size == 1 }?.let {
                    selectItem(it.first())
                }
        }
    }

    when (currentMode) {
        SearchBarMode.BUTTON -> LocationSearchBarButton(
            currentSearch = currentSearch,
            onBackClicked = onBackClicked,
            onClearLocation = {
                currentSearch = ""
                onClearLocation()
            },
            onClick = {
                currentMode = SearchBarMode.SEARCH
            },
        )

        SearchBarMode.SEARCH -> LocationSearchBar(
            currentSearch = currentSearch,
            currentResults = currentResults,
            activeSearching = searching,
            onSearchChanged = {
                currentSearch = it
                onSearchLocation(currentSearch)
            },
            onBackClicked = {
                currentMode = SearchBarMode.BUTTON
            },
            onSearch = { searchQuery ->
                currentMode = SearchBarMode.BUTTON
            },
            onLocationSelected = {
                selectItem(it)
                currentSearch = it.title
                currentMode = SearchBarMode.BUTTON
                onLocationSelected(it)
            },
        )
    }
}

@Composable
private fun LocationSearchBarButton(
    currentSearch: String = "",
    onBackClicked: () -> Unit,
    onClearLocation: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .testTag("SEARCH_BAR_BUTTON")
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(Shape.Full)
            .clickable(onClick = onClick)
            .background(
                color = SurfaceColor.ContainerLow,
                shape = Shape.Full,
            )
            .padding(Spacing.Spacing4),
        verticalAlignment = CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
    ) {
        IconButton(
            style = IconButtonStyle.STANDARD,
            onClick = onBackClicked,
            icon = {
                Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
            },
        )
        Text(
            modifier = Modifier.weight(1f),
            text = currentSearch.takeIf { it.isNotBlank() } ?: "Search location",
            style = MaterialTheme.typography.bodyLarge,
            color = if (currentSearch.isBlank()) {
                TextColor.OnDisabledSurface
            } else {
                TextColor.OnSurface
            },
        )
        if (currentSearch.isEmpty()) {
            IconButton(
                style = IconButtonStyle.STANDARD,
                onClick = {},
                icon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                },
            )
        } else {
            IconButton(
                style = IconButtonStyle.STANDARD,
                onClick = onClearLocation,
                icon = {
                    Icon(imageVector = Icons.Outlined.Cancel, contentDescription = null)
                },
            )
        }
    }
}

@Composable
private fun LocationSearchBar(
    currentSearch: String = "",
    currentResults: List<LocationItemModel>,
    activeSearching: Boolean,
    onSearchChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onBackClicked: () -> Unit,
    onLocationSelected: (LocationItemModel) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var needsToFocus by remember { mutableStateOf(true) }
    var searching by remember(currentSearch) { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = spacedBy(Spacing.Spacing16),
    ) {
        SearchBar(
            text = currentSearch,
            placeHolderText = provideStringResource("search_location"),
            onActiveChange = {},
            onQueryChange = onSearchChanged,
            onSearch = onSearch,
            leadingIcon = {
                IconButton(
                    style = IconButtonStyle.STANDARD,
                    onClick = onBackClicked,
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = null,
                        )
                    },
                )
            },
            focusRequester = focusRequester,
        )

        LaunchedEffect(scrollState.isScrollInProgress, needsToFocus) {
            if (scrollState.isScrollInProgress) {
                focusRequester.freeFocus()
                keyboardController?.hide()
            } else if (needsToFocus) {
                focusRequester.requestFocus()
                needsToFocus = false
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .draggableList(
                    scrollState = scrollState,
                    draggableType = DraggableType.Vertical,
                ),
            state = scrollState,
        ) {
            when {
                activeSearching ->
                    item {
                        SearchProgressMessage()
                    }

                currentResults.isNotEmpty() ->
                    itemsIndexed(items = currentResults) { index, locationItemModel ->
                        SearchResultLocationItem(
                            modifier = Modifier.testTag("LOCATION_ITEM_$index"),
                            locationItemModel,
                        ) {
                            onLocationSelected(locationItemModel)
                        }
                    }

                else ->
                    item {
                        NoResultsMessage(isSearching = currentSearch.isNotBlank())
                    }
            }

            item {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Outline.Medium,
                    modifier = Modifier.padding(vertical = Spacing16),
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        style = ButtonStyle.TONAL,
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Map,
                                contentDescription = "touch app",
                            )
                        },
                        text = provideStringResource("select_in_map"),
                        onClick = onBackClicked,
                    )
                }
            }
        }
    }
}

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    locationItemModel: LocationItemModel,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(Shape.Small)
            .clickableWithRipple(onClick = onClick)
            .padding(Spacing.Spacing8),
        horizontalArrangement = spacedBy(Spacing.Spacing16),
        verticalAlignment = Top,
    ) {
        icon()

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = locationItemModel.title,
                style = DHIS2SCustomTextStyles.titleMediumBold,
                color = TextColor.OnPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = locationItemModel.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.OnSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun SearchResultLocationItem(
    modifier: Modifier = Modifier,
    locationItemModel: LocationItemModel,
    onClick: () -> Unit,
) {
    val icon = when (locationItemModel) {
        is LocationItemModel.StoredResult -> Icons.Outlined.WatchLater
        is LocationItemModel.SearchResult -> Icons.Outlined.Place
    }
    val tintedColor = when (locationItemModel) {
        is LocationItemModel.StoredResult -> SurfaceColor.Warning
        is LocationItemModel.SearchResult -> SurfaceColor.Primary
    }

    val bgColor = when (locationItemModel) {
        is LocationItemModel.StoredResult -> SurfaceColor.WarningContainer
        is LocationItemModel.SearchResult -> SurfaceColor.PrimaryContainer
    }

    LocationItem(
        modifier = modifier,
        locationItemModel = locationItemModel,
        icon = {
            LocationItemIcon(
                icon = icon,
                tintedColor = tintedColor,
                bgColor = bgColor,
            )
        },
        onClick = onClick,
    )
}

/**
 * DHIS2 Location Item icon.
 * @param icon: the ImageVector to display as an icon.
 * @param tintedColor: the color to tint the icon with.
 * @param bgColor: the color for the background.
 */
@Composable
fun LocationItemIcon(
    icon: ImageVector,
    tintedColor: Color,
    bgColor: Color,
) {
    Box(
        modifier = Modifier.size(Spacing.Spacing40)
            .clip(Shape.Full)
            .background(color = bgColor, shape = Shape.Full),
        contentAlignment = Center,
    ) {
        Icon(
            imageVector = icon,
            tint = tintedColor,
            contentDescription = "location icon",
        )
    }
}

@Composable
private fun NoResultsMessage(isSearching: Boolean) {
    val message = if (!isSearching) {
        provideStringResource("no_recent_results")
    } else {
        provideStringResource("no_results")
    }

    Column(
        modifier = Modifier
            .testTag(if (!isSearching) "NO_RECENT_RESULTS" else "NO_RESULTS")
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        verticalArrangement = spacedBy(16.dp),
        horizontalAlignment = CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.TravelExplore,
            tint = SurfaceColor.ContainerHighest,
            contentDescription = "Travel explore",
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = TextColor.OnSurfaceVariant,
        )
    }
}

@Composable
private fun SearchProgressMessage() {
    val message = provideStringResource("searching_location")

    Column(
        modifier = Modifier
            .testTag("SEARCHING_LOCATION")
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        verticalArrangement = spacedBy(16.dp),
        horizontalAlignment = CenterHorizontally,
    ) {
        ProgressIndicator(type = ProgressIndicatorType.CIRCULAR)
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = TextColor.OnSurfaceVariant,
        )
    }
}
