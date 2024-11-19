package org.hisp.dhis.mobile.ui.designsystem.component.model

sealed class LocationItemModel(
    val title: String,
    val subtitle: String,
    val latitude: Double,
    val longitude: Double,
) {
    /**
     * UiModel used for Location Items which are stored in cache or local database.
     * @param storedTitle: the label to display.
     * @param storedSubtitle: the subtitle to display.
     * @param storedLatitude: the latitude of the location.
     * @param storedLongitude the longitude of the location.
     */
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

    /**
     * UiModel used for Location Items which are provided by external apis.
     * @param searchedTitle: the label to display.
     * @param searchedSubtitle: the subtitle to display.
     * @param searchedLatitude: the latitude of the location.
     * @param searchedLongitude the longitude of the location.
     */
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
