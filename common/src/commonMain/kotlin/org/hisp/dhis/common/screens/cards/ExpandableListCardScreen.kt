package org.hisp.dhis.common.screens.cards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncDisabled
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.lorem_medium
import org.hisp.dhis.mobile.ui.designsystem.Avatar
import org.hisp.dhis.mobile.ui.designsystem.AvatarStyleData
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ExpandableItemColumn
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.VerticalInfoListCard
import org.hisp.dhis.mobile.ui.designsystem.component.internal.ImageCardData
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ExpandableListCardScreen() {
    val items = listOf("Program number 1", "Program number 2")
    ExpandableItemColumn(
        modifier = Modifier.fillMaxSize(),
        itemList = items,
    ) { item, verticalPadding, onSizeChanged ->
        val index = items.indexOf(item)
        VerticalInfoListCard(
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
            title = ListCardTitleModel(text = item),
            lastUpdated = "12 min",
            description = ListCardDescriptionModel(text = "200 patients"),
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
                        value = lorem_medium,
                        color = TextColor.OnSurfaceLight,
                    ),
                )
            },
            loading = false,
            expandLabelText = "Show description",
            shrinkLabelText = "Hide description",
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
            expandable = true,
            itemVerticalPadding = verticalPadding,
            onSizeChanged = onSizeChanged,
        )
    }
}
