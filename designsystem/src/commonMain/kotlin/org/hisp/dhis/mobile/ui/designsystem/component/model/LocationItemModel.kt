package org.hisp.dhis.mobile.ui.designsystem.component.model

sealed class LocationItemModel(
    val title: String,
    val subtitle: String,
    val latitude: Double,
    val longitude: Double,
) {
    data class StoredResult(
        private val storedTitle: String,
        private val storedSubtitle: String,
        private val storedLatitude: Double,
        private val storedLongitude: Double,
    ) : LocationItemModel(
        title = storedTitle,
        subtitle = storedSubtitle,
        latitude = storedLatitude,
        longitude = storedLongitude,
    )

    data class SearchResult(
        private val searchedTitle: String,
        private val searchedSubtitle: String,
        private val searchedLatitude: Double,
        private val searchedLongitude: Double,
    ) : LocationItemModel(
        title = searchedTitle,
        subtitle = searchedSubtitle,
        latitude = searchedLatitude,
        longitude = searchedLongitude,
    )
}
