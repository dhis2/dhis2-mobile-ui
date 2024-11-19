package org.hisp.dhis.mobile.ui.designsystem

import org.hisp.dhis.mobile.ui.designsystem.component.LocationBar
import org.junit.Rule
import org.junit.Test

class LocationBarButtonSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchSearchBarButtonTest() {
        paparazzi.snapshot {
            LocationBar(
                currentResults = emptyList(),
                onBackClicked = {},
                onClearLocation = {},
                onSearchLocation = {},
                onLocationSelected = {},
                searching = false,
            )
        }
    }
}
