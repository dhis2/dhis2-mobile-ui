package org.hisp.dhis.mobile.ui.designsystem

import org.hisp.dhis.mobile.ui.designsystem.component.LocationBar
import org.hisp.dhis.mobile.ui.designsystem.component.SearchBarMode
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel
import org.junit.Rule
import org.junit.Test

class LocationBarSearchSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchSearchBarButtonTest() {
        paparazzi.snapshot {
            LocationBar(
                currentResults = listOf(
                    LocationItemModel.StoredResult(
                        "Location Item title",
                        "Location Item address",
                        0.0,
                        0.0,
                    ),
                    LocationItemModel.SearchResult(
                        "Location Item title 2",
                        "Location Item address 2",
                        0.0,
                        0.0,
                    ),
                ),
                mode = SearchBarMode.SEARCH,
                onBackClicked = {},
                onClearLocation = {},
                onSearchLocation = {},
                onLocationSelected = {},
            )
        }
    }
}
