package org.hisp.dhis.showcaseapp.screens.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.LocationBar
import org.hisp.dhis.mobile.ui.designsystem.component.OnSearchAction
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel
import org.hisp.dhis.showcaseapp.screens.components.GroupComponentDropDown

@Composable
fun LocationSearchBarScreen(
    onSearchLocation: (
        locationQuery: String,
        locationSearchCallback: (List<LocationItemModel>) -> Unit,
    ) -> Unit,
) {
    var itemList: List<LocationItemModel> by remember {
        mutableStateOf(defaultLocationItems)
    }

    val currentScreen = remember { mutableStateOf(LocationSearchBarOptions.DEFAULT_BEHAVIOUR) }
    val screenDropdownItemList = mutableListOf<DropdownItem>()
    LocationSearchBarOptions.entries.forEach {
        screenDropdownItemList.add(DropdownItem(it.label))
    }

    val scope = rememberCoroutineScope()

    GroupComponentDropDown(
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = {
            itemList = defaultLocationItems
            currentScreen.value = getCurrentScreen(it.label)
        },
        onResetButtonClicked = {
            itemList = defaultLocationItems
            currentScreen.value = LocationSearchBarOptions.DEFAULT_BEHAVIOUR
        },
        selectedItem = DropdownItem(currentScreen.value.label),
    )

    when (currentScreen.value) {
        LocationSearchBarOptions.DEFAULT_BEHAVIOUR -> {
            var searching by remember { mutableStateOf(false) }

            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp),
                contentAlignment = TopCenter,
            ) {
                LocationBar(
                    currentResults = itemList,
                    onBackClicked = {},
                    onClearLocation = {},
                    onSearchLocation = { locationQuery ->
                        searching = true
                        scope.launch {
                            delay(3000)
                            onSearchLocation(locationQuery) {
                                itemList =
                                    it.takeIf { locationQuery.isNotBlank() } ?: defaultLocationItems
                                searching = false
                            }
                        }
                    },
                    onLocationSelected = { locationItemModel ->
                    },
                    searching = searching,
                )
            }
        }

        LocationSearchBarOptions.AUTOSELECT_ON_ONE_ITEM_FOUND -> {
            var searching by remember { mutableStateOf(false) }

            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp),
                contentAlignment = TopCenter,
            ) {
                LocationBar(
                    currentResults = itemList,
                    searchAction = OnSearchAction.OnOneItemSelect,
                    onBackClicked = {},
                    onClearLocation = {},
                    onSearchLocation = { locationQuery ->
                        searching = true
                        scope.launch {
                            onSearchLocation(locationQuery) {
                                itemList =
                                    it.take(1).takeIf { locationQuery.isNotBlank() }
                                        ?: defaultLocationItems
                                searching = false
                            }
                        }
                    },
                    onLocationSelected = { locationItemModel ->
                    },
                    searching = searching,
                )
            }
        }
    }
}

private val defaultLocationItems =
    listOf(
        LocationItemModel.StoredResult(
            storedTitle = "Location #1",
            storedSubtitle = "Location description, location description, location description",
            storedLatitude = 0.0,
            storedLongitude = 0.0,
        ),
        LocationItemModel.StoredResult(
            storedTitle = "Location #2",
            storedSubtitle = "Location description, location description, location description",
            storedLatitude = 0.0,
            storedLongitude = 0.0,
        ),
    )

private enum class LocationSearchBarOptions(
    val label: String,
) {
    DEFAULT_BEHAVIOUR("Default behaviour"),
    AUTOSELECT_ON_ONE_ITEM_FOUND("Autoselect on one item found"),
}

private fun getCurrentScreen(label: String): LocationSearchBarOptions =
    LocationSearchBarOptions.entries.firstOrNull { it.label == label }
        ?: LocationSearchBarOptions.DEFAULT_BEHAVIOUR
