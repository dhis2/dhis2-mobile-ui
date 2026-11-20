package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.layout.FadingLazyColumn
import org.hisp.dhis.mobile.ui.designsystem.component.layout.FadingLazyRow
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.junit.Rule
import org.junit.Test

private const val ITEM_COUNT = 20
private const val MIDDLE_ITEM = 8
private const val LAST_ITEM = ITEM_COUNT - 1

class FadingLazyListSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun fadingLazyRow() {
        paparazzi.snapshot {
            ColumnComponentContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
                listOf(0, MIDDLE_ITEM, LAST_ITEM).forEach { initialItem ->
                    FadingLazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        state = rememberLazyListState(initialFirstVisibleItemIndex = initialItem),
                        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                    ) {
                        items(ITEM_COUNT) { index -> FadingListItem(index) }
                    }
                }
            }
        }
    }

    @Test
    fun fadingLazyColumn() {
        paparazzi.snapshot {
            Row(
                modifier = Modifier.padding(Spacing.Spacing10),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
            ) {
                listOf(0, MIDDLE_ITEM, LAST_ITEM).forEach { initialItem ->
                    FadingLazyColumn(
                        modifier = Modifier.width(Spacing.Spacing80).height(Spacing.Spacing200),
                        state = rememberLazyListState(initialFirstVisibleItemIndex = initialItem),
                        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                    ) {
                        items(ITEM_COUNT) { index -> FadingListItem(index) }
                    }
                }
            }
        }
    }

    /**
     * All rows start at the first item, so only the edge with more content fades:
     * RTL -> left, reverseLayout -> left, RTL + reverseLayout -> right.
     */
    @Test
    fun fadingLazyRowMirrored() {
        paparazzi.snapshot {
            ColumnComponentContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
                listOf(
                    LayoutDirection.Rtl to false,
                    LayoutDirection.Ltr to true,
                    LayoutDirection.Rtl to true,
                ).forEach { (layoutDirection, reverseLayout) ->
                    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                        FadingLazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            reverseLayout = reverseLayout,
                            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                        ) {
                            items(ITEM_COUNT) { index -> FadingListItem(index) }
                        }
                    }
                }
            }
        }
    }

    /**
     * With reverseLayout the first item sits at the bottom, so the top edge fades at the start
     * and the bottom edge fades once scrolled to the end.
     */
    @Test
    fun fadingLazyColumnReversed() {
        paparazzi.snapshot {
            Row(
                modifier = Modifier.padding(Spacing.Spacing10),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
            ) {
                listOf(0, MIDDLE_ITEM, LAST_ITEM).forEach { initialItem ->
                    FadingLazyColumn(
                        modifier = Modifier.width(Spacing.Spacing80).height(Spacing.Spacing200),
                        state = rememberLazyListState(initialFirstVisibleItemIndex = initialItem),
                        reverseLayout = true,
                        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                    ) {
                        items(ITEM_COUNT) { index -> FadingListItem(index) }
                    }
                }
            }
        }
    }
}

@Composable
private fun FadingListItem(index: Int) {
    Box(
        modifier =
            Modifier
                .size(Spacing.Spacing56)
                .background(SurfaceColor.Primary, Shape.Small),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = index.toString(), color = TextColor.OnPrimary)
    }
}
