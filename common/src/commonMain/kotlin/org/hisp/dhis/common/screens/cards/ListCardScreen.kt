package org.hisp.dhis.common.screens.cards

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncProblem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.previews.basicAdditionalItemList
import org.hisp.dhis.common.screens.previews.basicAdditionalItemListWithLongKeyText
import org.hisp.dhis.common.screens.previews.basicAdditionalItemListWithLongValue
import org.hisp.dhis.common.screens.previews.basicAdditionalItemListWithMediumKeyText
import org.hisp.dhis.common.screens.previews.enrollmentCompletedList
import org.hisp.dhis.common.screens.previews.fullItemList
import org.hisp.dhis.common.screens.previews.largeItemList
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.Avatar
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarStyle
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ListCardScreen(horizontal: Boolean) {
    if (horizontal) {
        LazyRow(
            modifier = Modifier.heightIn(0.dp, 500.dp),
            horizontalArrangement = spacedBy(4.dp),
            verticalAlignment = Alignment.Top,
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 16.dp),
        ) {
            items(count = 4) { index ->
                ListCard(
                    modifier = Modifier.fillParentMaxWidth(),
                    listAvatar = {
                        Avatar(
                            textAvatar = "$index",
                            style = AvatarStyle.TEXT,
                        )
                    },
                    title = ListCardTitleModel(text = "Palak Khanna, F, 61"),
                    lastUpdated = "5 hours",
                    additionalInfoList = largeItemList,
                    onCardClick = {},
                    scrollableContent = true,
                )
            }
        }
    } else {
        ColumnScreenContainer(title = Cards.LIST_CARD.label) {
            var showLoading1 by remember {
                mutableStateOf(false)
            }
            val eventsTimelineTeiDashboardList = remember {
                mutableStateListOf(
                    AdditionalInfoItem(key = "Date of birth", value = "12/12/1945"),
                )
            }
            ColumnComponentContainer("Tei list with shadow") {
                ListCard(
                    listAvatar = {
                        Avatar(
                            textAvatar = "P",
                            style = AvatarStyle.TEXT,
                        )
                    },
                    title = ListCardTitleModel(text = "Palak Khanna, F, 61"),
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
                    loading = showLoading1,
                )
                var showLoading2 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listAvatar = {
                        Avatar(
                            textAvatar = "P",
                            style = AvatarStyle.TEXT,
                        )
                    },
                    title = ListCardTitleModel(text = "Palak Khanna, F, 61"),
                    lastUpdated = "5 hours",
                    additionalInfoList = basicAdditionalItemListWithLongKeyText.toMutableList(),
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
                    loading = showLoading2,
                )
                var showLoading3 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listAvatar = {
                        Avatar(
                            textAvatar = "P",
                            style = AvatarStyle.TEXT,
                        )
                    },
                    title = ListCardTitleModel(text = "Palak Khanna, F, 61"),
                    lastUpdated = "5 hours",
                    additionalInfoList = basicAdditionalItemListWithMediumKeyText.toMutableList(),
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
                    loading = showLoading3,
                )
                var showLoading4 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listAvatar = {
                        Avatar(
                            textAvatar = "P",
                            style = AvatarStyle.TEXT,
                        )
                    },
                    title = ListCardTitleModel(text = "Palak Khanna, F, 61"),
                    lastUpdated = "5 hours",
                    additionalInfoList = basicAdditionalItemListWithLongValue.toMutableList(),
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
                            onClick = { showLoading4 = !showLoading4 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                    loading = showLoading4,
                )
                var showLoading5 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listAvatar = {
                        Avatar(
                            imagePainter = provideDHIS2Icon("dhis2_microscope_outline"),
                            style = AvatarStyle.IMAGE,
                        )
                    },
                    title = ListCardTitleModel(text = "Kunal Choudary, M, 55"),
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
                            onClick = { showLoading5 = !showLoading5 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                    loading = showLoading5,
                )
            }

            ColumnComponentContainer("Without shadow") {
                var showLoading6 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    shadow = false,
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
                    title = ListCardTitleModel(text = "Anita Mathews, F, 72"),
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
                            onClick = { showLoading6 = !showLoading6 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                    loading = showLoading6,
                )

                var showLoading7 by remember {
                    mutableStateOf(false)
                }
                val errorList = remember {
                    mutableStateListOf(
                        AdditionalInfoItem(key = "Phone", value = "+234 123 111 6785"),
                        AdditionalInfoItem(key = "Date of birth", value = "12/12/1945"),
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
                    title = ListCardTitleModel(text = "Aditi Singh, F, 61"),
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
                                if (!showLoading7) {
                                    errorList.remove(errorItem)
                                } else {
                                    errorList.add(errorItem)
                                }
                                showLoading7 = !showLoading7
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                    loading = showLoading7,
                )
            }

            ColumnComponentContainer("Single events list with shadow") {
                ListCard(
                    title = ListCardTitleModel(text = "12/18/2021"),
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
            }

            ColumnComponentContainer("Single events list without shadow:") {
                ListCard(
                    shadow = false,
                    title = ListCardTitleModel(text = "12/18/2021"),
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
            }

            ColumnComponentContainer("Events in timeline in TEI dashboard with shadow") {
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
                    title = ListCardTitleModel(text = "12/18/2021 at 16:30"),
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

            ColumnComponentContainer("Events in timeline in TEI dashboard without shadow:") {
                ListCard(
                    shadow = false,
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
                    title = ListCardTitleModel(text = "12/18/2021 at 16:30"),
                    description = ListCardDescriptionModel(text = "Birth"),
                    lastUpdated = "now",
                    additionalInfoList = eventsTimelineTeiDashboardList,
                    onCardClick = {},
                )
            }

            ColumnComponentContainer("ListCards for events  displayed in TEI dashboard:") {
                val eventsInTeiDashboardTitleStyle = MaterialTheme.typography.titleSmall

                ListCard(

                    title = ListCardTitleModel(
                        text = "Scheduled for 09/18/2021",
                        style = eventsInTeiDashboardTitleStyle,
                        color = TextColor.OnSurface
                    ),
                    additionalInfoList = listOf(
                        AdditionalInfoItem(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Event,
                                    contentDescription = "Icon Button",
                                    tint = AdditionalInfoItemColor.SUCCESS.color,
                                )
                            },
                            value = "In 60 days",
                            color = AdditionalInfoItemColor.SUCCESS.color,
                            isConstantItem = true,
                        ),
                    ),
                    onCardClick = {},
                )

                ListCard(

                    title = ListCardTitleModel(
                        text = "09/18/2021",
                        style = eventsInTeiDashboardTitleStyle,
                        color = TextColor.OnSurface
                    ),
                    description = ListCardDescriptionModel(text = "Treatment visits"),
                    additionalInfoList = listOf(
                        AdditionalInfoItem(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Event,
                                    contentDescription = "Icon Button",
                                    tint = AdditionalInfoItemColor.SUCCESS.color,
                                )
                            },
                            value = "In 60 days",
                            color = AdditionalInfoItemColor.SUCCESS.color,
                            isConstantItem = true,
                        ),
                        AdditionalInfoItem(key = "Drug resistance", value = "Monoresistance"),
                        AdditionalInfoItem(
                            key = "treatment",
                            value = "Initial regiment- first-line drugs"
                        ),
                    ),

                    onCardClick = {},
                )
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
                    title = ListCardTitleModel(
                        text = "Scheduled for 09/18/2021",
                        style = eventsInTeiDashboardTitleStyle,
                        color = TextColor.OnSurface
                    ),
                    additionalInfoList = listOf(
                        AdditionalInfoItem(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Event,
                                    contentDescription = "Icon Button",
                                    tint = AdditionalInfoItemColor.SUCCESS.color,
                                )
                            },
                            value = "In 60 days",
                            color = AdditionalInfoItemColor.SUCCESS.color,
                            isConstantItem = true,
                        ),
                        AdditionalInfoItem(key = "Drug resistance", value = "Monoresistance"),
                        AdditionalInfoItem(
                            key = "treatment",
                            value = "Initial regiment- first-line drugs"
                        ),
                    ),
                    onCardClick = {},
                )
            }
        }
    }
}
