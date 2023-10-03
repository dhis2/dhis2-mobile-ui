package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.additionalItemList
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ListAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.ListAvatarStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.resource.provideImage
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ListCardScreen() {
    ColumnComponentContainer(title = "List Card Components") {
        var showLoading1 by remember {
            mutableStateOf(false)
        }

        ListCard(
            listAvatar = {
                ListAvatar(
                    textAvatar = "X",
                    style = ListAvatarStyle.TEXT,
                    onImageClick = {},
                )
            },
            title = "Anita Mathews, F, 72",
            lastUpdated = "5 hours",
            additionalInfoList = additionalItemList,
            actionButton = {
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
                    onClick = { showLoading1 = !showLoading1 },
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onCardClick = {},
            showLoading = showLoading1,
        )
        var showLoading2 by remember {
            mutableStateOf(false)
        }
        ListCard(
            listAvatar = {
                ListAvatar(
                    imagePainter = provideImage("dog"),
                    style = ListAvatarStyle.IMAGE,
                )
            },
            title = "Anita Mathews, F, 72",
            lastUpdated = "5 hours",
            additionalInfoList = additionalItemList,
            actionButton = {
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
                    onClick = { showLoading2 = !showLoading2 },
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onCardClick = {},
            showLoading = showLoading2,
        )

        var showLoading3 by remember {
            mutableStateOf(false)
        }
        ListCard(
            listAvatar = {
                ListAvatar(
                    metadataAvatar = {
                        MetadataAvatar(
                            icon = {
                                Icon(
                                    painter = provideDHIS2Icon("dhis2_microscope_outline"),
                                    contentDescription = "Button",
                                    tint = SurfaceColor.Primary,
                                )
                            },
                        )
                    },
                    style = ListAvatarStyle.METADATA,
                )
            },
            title = "Anita Mathews, F, 72",
            lastUpdated = "5 hours",
            additionalInfoList = additionalItemList,
            actionButton = {
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
                    onClick = { showLoading3 = !showLoading3 },
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onCardClick = {},
            showLoading = showLoading3,
        )
    }
}
