@file:Suppress("DEPRECATION")

package org.hisp.dhis.showcaseapp.screens.cards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncDisabled
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.showcaseapp.screens.previews.LOREM

@Composable
fun ExpandableListCardScreen() {
    val items = listOf("Program number 1", "Program number 2")
    ExpandableItemColumn(
        modifier = Modifier.fillMaxSize(),
        itemList = items,
    ) { item, verticalPadding, onSizeChanged ->
        val index = items.indexOf(item)
        val additionalInfoList =
            buildList {
                if (index != 0) {
                    add(
                        AdditionalInfoItem(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.SyncDisabled,
                                    contentDescription = "Sync disabled",
                                    tint = DHIS2Theme.extendedColors.onSurfaceLight,
                                )
                            },
                            value = "Not synced",
                            color = DHIS2Theme.extendedColors.onSurfaceLight,
                        ),
                    )
                }
                add(
                    AdditionalInfoItem(
                        value = LOREM,
                        color = DHIS2Theme.extendedColors.onSurfaceLight,
                        truncate = false,
                    ),
                )
            }
        VerticalInfoListCard(
            listCardState =
                rememberListCardState(
                    title = ListCardTitleModel(text = item),
                    description = ListCardDescriptionModel(text = "200 patients"),
                    lastUpdated = "12 min",
                    additionalInfoColumnState =
                        rememberAdditionalInfoColumnState(
                            additionalInfoList = additionalInfoList,
                            syncProgressItem =
                                AdditionalInfoItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Sync,
                                            contentDescription = "Icon Button",
                                            tint = DHIS2Theme.colorScheme.primary,
                                        )
                                    },
                                    value = "Syncing...",
                                    color = DHIS2Theme.colorScheme.primary,
                                    isConstantItem = false,
                                ),
                            shrinkLabelText = "Hide description",
                            expandLabelText = "Show description",
                            minItemsToShow =
                                when (additionalInfoList.size) {
                                    1 -> 0
                                    else -> 1
                                },
                        ),
                    loading = false,
                    shadow = true,
                    expandable = true,
                    itemVerticalPadding = verticalPadding,
                ),
            listAvatar = {
                Avatar(
                    style =
                        AvatarStyleData.Metadata(
                            imageCardData =
                                ImageCardData.IconCardData(
                                    uid = "",
                                    label = "",
                                    iconRes = "dhis2_microscope_outline",
                                    iconTint = DHIS2Theme.colorScheme.primary,
                                ),
                            avatarSize = MetadataAvatarSize.L(),
                            tintColor = DHIS2Theme.colorScheme.primary,
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
                                tint = DHIS2Theme.colorScheme.onPrimaryContainer,
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
