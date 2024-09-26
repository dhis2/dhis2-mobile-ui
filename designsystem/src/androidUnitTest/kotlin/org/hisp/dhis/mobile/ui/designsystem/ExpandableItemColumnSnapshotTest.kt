package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncDisabled
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.Avatar
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarStyleData
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ExpandableItemColumn
import org.hisp.dhis.mobile.ui.designsystem.component.ImageCardData
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.VerticalInfoListCard
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.junit.Rule
import org.junit.Test

class ExpandableItemColumnSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchAvatarTest() {
        paparazzi.snapshot {
            val items = listOf("Program number 1", "Program number 2")
            ExpandableItemColumn(
                modifier = Modifier.fillMaxSize().graphicsLayer { clip = false },
                itemList = items,
            ) { item, verticalPadding, onSizeChanged ->
                val index = items.indexOf(item)
                VerticalInfoListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = item),
                        lastUpdated = "12 min",
                        description = ListCardDescriptionModel(text = "200 patients"),
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = buildList {
                                if (index != 0) {
                                    add(
                                        AdditionalInfoItem(
                                            icon = {
                                                Icon(
                                                    imageVector = Icons.Outlined.SyncDisabled,
                                                    contentDescription = "Sync disabled",
                                                    tint = TextColor.OnSurfaceLight,
                                                )
                                            },
                                            value = "Not synced",
                                            color = TextColor.OnSurfaceLight,
                                        ),
                                    )
                                }
                                add(
                                    AdditionalInfoItem(
                                        value = "this is a description",
                                        color = TextColor.OnSurfaceLight,
                                    ),
                                )
                            },
                            expandLabelText = "Show description",
                            shrinkLabelText = "Hide description",
                            syncProgressItem = AdditionalInfoItem(
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
                        loading = false,
                        expandable = true,
                        itemVerticalPadding = verticalPadding,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Metadata(
                                imageCardData = ImageCardData.IconCardData(
                                    uid = "",
                                    label = "",
                                    iconRes = "dhis2_microscope_outline",
                                    iconTint = SurfaceColor.Primary,
                                ),
                                avatarSize = MetadataAvatarSize.L(),
                                tintColor = SurfaceColor.Primary,
                            ),
                        )
                    },
                    onCardClick = {},
                    actionButton = {
                        if (index != 0) {
                            Button(
                                style = ButtonStyle.TONAL,
                                text = "Retry sync",
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Sync,
                                        contentDescription = "Icon Button",
                                        tint = TextColor.OnPrimaryContainer,
                                    )
                                },
                                onClick = {},
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    },
                    onSizeChanged = onSizeChanged,
                )
            }
        }
    }
}
