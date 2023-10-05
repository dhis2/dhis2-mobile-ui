package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncProblem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.common.screens.previews.basicAdditionalItemList
import org.hisp.dhis.common.screens.previews.enrollmentCompletedList
import org.hisp.dhis.common.screens.previews.fullItemList
import org.hisp.dhis.common.screens.previews.teiDetailList
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.Avatar
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarStyle
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.CardDetail
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ListCardScreen() {
    ColumnComponentContainer(title = "List Card Components") {
        InfoBar(
            text = "Not Synced",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Sync,
                    contentDescription = "not synced",
                    tint = TextColor.OnSurfaceLight,
                )
            },
            onClick = {},
            color = TextColor.OnSurfaceLight,
            backgroundColor = Color(0xFFEFF6FA),
        )
        InfoBar(
            text = "Enrollment completed",
            icon = {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "not synced",
                    tint = AdditionalInfoItemColor.SUCCESS.color,
                )
            },
            color = AdditionalInfoItemColor.SUCCESS.color,
            backgroundColor = AdditionalInfoItemColor.SUCCESS.color.copy(alpha = 0.1f),
        )

        CardDetail(
            avatar = {
                Avatar(
                    imagePainter = provideImage("dog"),
                    style = AvatarStyle.IMAGE,
                )
            },
            title = "Narayan Khanna, M, 32",
            additionalInfoList = teiDetailList,
        )
        var showLoading1 by remember {
            mutableStateOf(false)
        }
        SubTitle("Tei list:")
        ListCard(
            listAvatar = {
                Avatar(
                    textAvatar = "P",
                    style = AvatarStyle.TEXT,
                )
            },
            title = "Palak Khanna, F, 61",
            lastUpdated = "5 hours",
            additionalInfoList = basicAdditionalItemList.toMutableList(),
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
                Avatar(
                    imagePainter = provideImage("dog"),
                    style = AvatarStyle.IMAGE,
                )
            },
            title = "Kunal Choudary, M, 55",
            lastUpdated = "24 min",
            additionalInfoList = enrollmentCompletedList.toMutableList(),
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
                Avatar(
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
                    style = AvatarStyle.METADATA,
                )
            },
            title = "Anita Mathews, F, 72",
            lastUpdated = "5 hours",
            additionalInfoList = fullItemList.toMutableList(),
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

        var showLoading4 by remember {
            mutableStateOf(false)
        }
        val errorList = remember {
            mutableStateListOf(
                AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
            )
        }
        val eventsTimelineTeiDashboardList = remember {
            mutableStateListOf(
                AdditionalInfoItem(value = "Birth"),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
            )
        }

        val errorItem = AdditionalInfoItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.SyncProblem,
                    contentDescription = "Icon Button",
                    tint = AdditionalInfoItemColor.ERROR.color,
                )
            },
            value = "Sync warning",
            color = AdditionalInfoItemColor.ERROR.color,
            isConstantItem = true,
        )

        ListCard(
            listAvatar = {
                Avatar(
                    textAvatar = "A",
                    style = AvatarStyle.TEXT,
                )
            },
            title = "Aditi Singh, F, 61",
            lastUpdated = "5 hours",
            additionalInfoList = errorList,
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
                    onClick = {
                        if (!showLoading4) {
                            errorList.remove(errorItem)
                        } else {
                            errorList.add(errorItem)
                        }
                        showLoading4 = !showLoading4
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onCardClick = {},
            showLoading = showLoading4,
        )
        Spacer(Modifier.size(Spacing.Spacing16))
        SubTitle("Single events list:")

        ListCard(

            title = "12/18/2021",
            lastUpdated = "now",
            additionalInfoList = basicAdditionalItemList,
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
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onCardClick = {},
        )
        Spacer(Modifier.size(Spacing.Spacing16))
        SubTitle("Events in timeline in TEI dashboard:")

        ListCard(
            listAvatar = {
                Avatar(
                    metadataAvatar = {
                        MetadataAvatar(
                            icon = {
                                Icon(
                                    painter = provideDHIS2Icon("dhis2_baby_male_0203m_positive"),
                                    contentDescription = "Button",

                                )
                            },
                            iconTint = Color(0xFF11D9D9),
                            size = AvatarSize.Large,
                        )
                    },
                    style = AvatarStyle.METADATA,
                )
            },
            title = "12/18/2021 at 16:30",
            lastUpdated = "now",
            additionalInfoList = eventsTimelineTeiDashboardList,
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
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onCardClick = {},
        )
    }
}
