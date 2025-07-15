package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.Icon
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.Avatar
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarStyleData
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.SelectionState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.Rule
import org.junit.Test

class ListCardSelectableSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun launchListCard() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewContextConfigurationEffect()
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.Spacing4),
                modifier = Modifier.padding(horizontal = Spacing.Spacing8),
            ) {
                SelectionState.entries.forEach { selectionState ->
                    ListCard(
                        listCardState =
                            rememberListCardState(
                                title = ListCardTitleModel(text = "Kunal Choudary, M, 55"),
                                lastUpdated = "24 min",
                                additionalInfoColumnState =
                                    rememberAdditionalInfoColumnState(
                                        additionalInfoList = emptyList(),
                                        syncProgressItem =
                                            AdditionalInfoItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Outlined.Sync,
                                                        contentDescription = "Icon Button",
                                                        tint = SurfaceColor.Primary,
                                                    )
                                                },
                                                value = "Syncing...",
                                                color = SurfaceColor.Primary,
                                                isConstantItem = false,
                                            ),
                                    ),
                                selectionState = selectionState,
                                loading = true,
                            ),
                        listAvatar = {
                            Avatar(
                                style = AvatarStyleData.Image(provideDHIS2Icon("dhis2_microscope_outline")),
                            )
                        },
                        onCardClick = {},
                    )
                }
            }
        }
    }
}
