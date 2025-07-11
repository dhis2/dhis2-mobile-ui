package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import org.hisp.dhis.mobile.ui.designsystem.component.LocationBar
import org.hisp.dhis.mobile.ui.designsystem.component.SearchBarMode
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.Rule
import org.junit.Test

class LocationBarSearchSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun launchSearchBarButtonTest() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewContextConfigurationEffect()
            }
            LocationBar(
                currentResults =
                    listOf(
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
                searching = false,
            )
        }
    }
}
