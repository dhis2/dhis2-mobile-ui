package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel
import org.junit.Rule
import org.junit.Test

class LocationSearchBarTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldDisplaySearchBarWithNoRecentResults() {
        rule.setContent {
            LocationBar(
                currentResults = emptyList(),
                onBackClicked = {},
                onClearLocation = {},
                onSearchLocation = {},
                onLocationSelected = {},
            )
        }

        with(rule) {
            onNodeWithTag("SEARCH_BAR_BUTTON")
                .assertIsDisplayed()
                .performClick()
            onNodeWithTag("SEARCH_INPUT")
                .assertIsDisplayed()
            onNodeWithTag("NO_RECENT_RESULTS")
                .assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplaySearchBarWithResults() {
        rule.setContent {
            LocationBar(
                currentResults = listOf(
                    LocationItemModel.StoredResult(
                        "title",
                        "subtitle",
                        0.0,
                        0.0,
                    ),
                ),
                onBackClicked = {},
                onClearLocation = {},
                onSearchLocation = {},
                onLocationSelected = {},
            )
        }

        with(rule) {
            onNodeWithTag("SEARCH_BAR_BUTTON")
                .assertIsDisplayed()
                .performClick()
            onNodeWithTag("SEARCH_INPUT")
                .assertIsDisplayed()
            onNodeWithTag("LOCATION_ITEM_0")
                .assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplayNoResultsMessage() {
        rule.setContent {
            var items: List<LocationItemModel> by remember {
                mutableStateOf(
                    listOf(
                        LocationItemModel.StoredResult(
                            "title",
                            "subtitle",
                            0.0,
                            0.0,
                        ),
                    ),
                )
            }
            LocationBar(
                currentResults = items,
                onBackClicked = {},
                onClearLocation = {},
                onSearchLocation = { querySearch ->
                    items = listOf()
                },
                onLocationSelected = {},
            )
        }

        with(rule) {
            onNodeWithTag("SEARCH_BAR_BUTTON")
                .assertIsDisplayed()
                .performClick()
            onNodeWithTag("SEARCH_INPUT")
                .assertIsDisplayed()
                .performTextInput("Hospital la")
            waitForIdle()
            onNodeWithTag("NO_RESULTS")
                .assertIsDisplayed()
        }
    }

    @Test
    fun shouldDisplaySearchResults() {
        rule.setContent {
            var items: List<LocationItemModel> by remember {
                mutableStateOf(
                    listOf(
                        LocationItemModel.StoredResult(
                            "title",
                            "subtitle",
                            0.0,
                            0.0,
                        ),
                    ),
                )
            }

            LocationBar(
                currentResults = items,
                onBackClicked = {},
                onClearLocation = {},
                onSearchLocation = {
                    items = listOf(
                        LocationItemModel.SearchResult(
                            "title search result",
                            "subtitle search result",
                            0.0,
                            0.0,
                        ),
                    )
                },
                onLocationSelected = {},
            )
        }

        with(rule) {
            onNodeWithTag("SEARCH_BAR_BUTTON")
                .assertIsDisplayed()
                .performClick()
            onNodeWithTag("SEARCH_INPUT")
                .assertIsDisplayed()
                .performTextInput("Hospital la")
            waitForIdle()
            onNodeWithTag("LOCATION_ITEM_0", useUnmergedTree = true)
                .assertIsDisplayed()
                .assert(hasAnyDescendant(hasText("title search result")))
        }
    }

    @Test
    fun shouldDisplaySelectedLocationInfo() {
        rule.setContent {
            var items: List<LocationItemModel> by remember {
                mutableStateOf(
                    listOf(
                        LocationItemModel.StoredResult(
                            "title",
                            "subtitle",
                            0.0,
                            0.0,
                        ),
                    ),
                )
            }

            LocationBar(
                currentResults = items,
                onBackClicked = {},
                onClearLocation = {},
                onSearchLocation = {
                    items = listOf(
                        LocationItemModel.SearchResult(
                            "title search result",
                            "subtitle search result",
                            0.0,
                            0.0,
                        ),
                    )
                },
                onLocationSelected = {},
            )
        }

        with(rule) {
            onNodeWithTag("SEARCH_BAR_BUTTON")
                .assertIsDisplayed()
                .performClick()
            onNodeWithTag("SEARCH_INPUT")
                .assertIsDisplayed()
                .performTextInput("Hospital la")
            waitForIdle()
            onNodeWithTag("LOCATION_ITEM_0")
                .assertIsDisplayed()
                .performClick()
            onNodeWithTag("SEARCH_BAR_BUTTON", useUnmergedTree = true)
                .assertIsDisplayed()
                .assert(hasAnyDescendant(hasText("title search result")))
        }
    }
}
