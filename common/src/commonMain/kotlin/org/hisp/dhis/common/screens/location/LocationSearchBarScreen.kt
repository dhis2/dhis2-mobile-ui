package org.hisp.dhis.common.screens.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.LocationBar
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel

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
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = TopCenter,
    ) {
        LocationBar(
            currentResults = itemList,
            onBackClicked = {},
            onClearLocation = {},
            onSearchLocation = { locationQuery ->
                onSearchLocation(locationQuery) {
                    itemList = it.takeIf { locationQuery.isNotBlank() } ?: defaultLocationItems
                }
            },
            onLocationSelected = { locationItemModel ->
            },
        )
    }
}

private val defaultLocationItems = listOf(
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
