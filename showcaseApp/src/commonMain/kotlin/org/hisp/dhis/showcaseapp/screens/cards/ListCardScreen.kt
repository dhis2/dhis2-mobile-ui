package org.hisp.dhis.showcaseapp.screens.cards

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
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.Avatar
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarStyleData
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ImageCardData
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.SelectionState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.showcaseapp.screens.previews.basicAdditionalItemList
import org.hisp.dhis.showcaseapp.screens.previews.basicAdditionalItemListWithLongKeyText
import org.hisp.dhis.showcaseapp.screens.previews.basicAdditionalItemListWithLongValue
import org.hisp.dhis.showcaseapp.screens.previews.basicAdditionalItemListWithMediumKeyText
import org.hisp.dhis.showcaseapp.screens.previews.enrollmentCompletedList
import org.hisp.dhis.showcaseapp.screens.previews.fullItemList
import org.hisp.dhis.showcaseapp.screens.previews.largeItemList
import org.hisp.dhis.showcaseapp.screens.previews.lorem

const val LAST_UPDATED_MINS = "24 min"
const val LAST_UPDATED_HOURS = "5 hours"
const val RETRY_SYNC = "Retry sync"
const val DUE_DATE = "In 60 days"
const val DEFAULT_NAME = "Palak Khanna, F, 61"
const val ICON_BUTTON_DESCRIPTION = "Icon Button"

@Composable
fun ListCardScreen(horizontal: Boolean) {
    ColumnScreenContainer(title = if (horizontal) Cards.LIST_CARD_HORIZONTAL.label else Cards.LIST_CARD.label) {
        if (horizontal) {
            LazyRow(
                modifier = Modifier.heightIn(0.dp, 500.dp),
                horizontalArrangement = spacedBy(4.dp),
                verticalAlignment = Alignment.Top,
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 16.dp),
            ) {
                items(count = 4) { index ->
                    ListCard(
                        listCardState = rememberListCardState(
                            title = ListCardTitleModel(text = DEFAULT_NAME),
                            lastUpdated = LAST_UPDATED_HOURS,
                            additionalInfoColumnState = rememberAdditionalInfoColumnState(
                                additionalInfoList = largeItemList,
                                syncProgressItem = syncProgressItem(),
                                scrollableContent = true,
                            ),
                            loading = false,
                        ),
                        modifier = Modifier.fillParentMaxWidth(),
                        listAvatar = {
                            Avatar(
                                style = AvatarStyleData.Text("$index"),
                            )
                        },
                        onCardClick = {},
                    )
                }
            }
        } else {
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
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "Kunal Choudary, M, 55"),
                        lastUpdated = LAST_UPDATED_MINS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = enrollmentCompletedList.toMutableList(),
                            syncProgressItem = AdditionalInfoItem(
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Sync,
                                        contentDescription = ICON_BUTTON_DESCRIPTION,
                                        tint = SurfaceColor.Primary,
                                    )
                                },
                                value = "Syncing...",
                                color = SurfaceColor.Primary,
                                isConstantItem = false,
                            ),
                        ),
                        loading = true,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Image(provideDHIS2Icon("dhis2_microscope_outline")),
                        )
                    },
                    onCardClick = {},
                )

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = DEFAULT_NAME),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemList.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading1,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading1 = !showLoading1 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                )
                var showLoading2 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = DEFAULT_NAME),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemListWithLongKeyText.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading2,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading2 = !showLoading2 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                )
                var showLoading3 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = DEFAULT_NAME),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemListWithMediumKeyText.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading3,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading3 = !showLoading3 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                )
                var showLoading4 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = DEFAULT_NAME),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemListWithLongValue.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading4,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading4 = !showLoading4 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                )
                var showLoading5 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "Kunal Choudary, M, 55"),
                        lastUpdated = LAST_UPDATED_MINS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = enrollmentCompletedList.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading5,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Image(provideDHIS2Icon("dhis2_microscope_outline")),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading5 = !showLoading5 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                )
                SubTitle(text = "Long title with overflow")
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = lorem, allowOverflow = true),
                        lastUpdated = LAST_UPDATED_MINS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = enrollmentCompletedList.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading5,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Image(provideDHIS2Icon("dhis2_microscope_outline")),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading5 = !showLoading5 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                )

                SubTitle(text = "Long title without overflow")
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = lorem, allowOverflow = false),
                        lastUpdated = LAST_UPDATED_MINS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = enrollmentCompletedList.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading5,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Image(provideDHIS2Icon("dhis2_microscope_outline")),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading5 = !showLoading5 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
                )
            }

            ColumnComponentContainer("Without shadow") {
                var showLoading6 by remember {
                    mutableStateOf(false)
                }
                ListCard(
                    listCardState = rememberListCardState(
                        shadow = false,
                        title = ListCardTitleModel(text = "Anita Mathews, F, 72"),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = fullItemList.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading6,
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
                                avatarSize = MetadataAvatarSize.S(),
                                tintColor = SurfaceColor.Primary,
                            ),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
                                    tint = TextColor.OnPrimaryContainer,
                                )
                            },
                            onClick = { showLoading6 = !showLoading6 },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onCardClick = {},
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
                            contentDescription = ICON_BUTTON_DESCRIPTION,
                            tint = AdditionalInfoItemColor.ERROR.color,
                        )
                    },
                    value = "Sync warning",
                    color = AdditionalInfoItemColor.ERROR.color,
                    isConstantItem = true,
                )

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "Aditi Singh, F, 61"),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = errorList,
                            syncProgressItem = syncProgressItem(),
                        ),
                        loading = showLoading7,
                    ),
                    listAvatar = {
                        Avatar(style = AvatarStyleData.Text("A"))
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
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
                )
            }

            ColumnComponentContainer("Single events list with shadow") {
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "12/18/2021"),
                        lastUpdated = "now",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemList,
                            syncProgressItem = syncProgressItem(),
                        ),
                    ),
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
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
                    listCardState = rememberListCardState(
                        shadow = false,
                        title = ListCardTitleModel(text = "12/18/2021"),
                        lastUpdated = "now",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemList,
                            syncProgressItem = syncProgressItem(),
                        ),
                    ),
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
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
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "12/18/2021 at 16:30"),
                        lastUpdated = "now",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = eventsTimelineTeiDashboardList,
                            syncProgressItem = syncProgressItem(),
                        ),
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Metadata(
                                imageCardData = ImageCardData.IconCardData(
                                    uid = "",
                                    label = "",
                                    iconRes = "dhis2_baby_male_0203m_positive",
                                    iconTint = Color(0xFF11D9D9),
                                ),
                                avatarSize = MetadataAvatarSize.M(),
                                tintColor = Color(0xFF11D9D9),
                            ),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.TONAL,
                            text = RETRY_SYNC,
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Sync,
                                    contentDescription = ICON_BUTTON_DESCRIPTION,
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
                    listCardState = rememberListCardState(
                        shadow = false,
                        title = ListCardTitleModel(text = "12/18/2021 at 16:30"),
                        description = ListCardDescriptionModel(text = "Birth"),
                        lastUpdated = "now",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = eventsTimelineTeiDashboardList,
                            syncProgressItem = syncProgressItem(),
                        ),
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Metadata(
                                imageCardData = ImageCardData.IconCardData(
                                    uid = "",
                                    label = "",
                                    iconRes = "dhis2_baby_male_0203m_positive",
                                    iconTint = Color(0xFF11D9D9),
                                ),
                                avatarSize = MetadataAvatarSize.M(),
                                tintColor = Color(0xFF11D9D9),
                            ),
                        )
                    },
                    onCardClick = {},
                )
            }

            ColumnComponentContainer("ListCards for events  displayed in TEI dashboard:") {
                val eventsInTeiDashboardTitleStyle = MaterialTheme.typography.titleSmall

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(
                            text = "Scheduled for 09/18/2021",
                            style = eventsInTeiDashboardTitleStyle,
                            color = TextColor.OnSurface,
                        ),
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = listOf(
                                AdditionalInfoItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Event,
                                            contentDescription = ICON_BUTTON_DESCRIPTION,
                                            tint = AdditionalInfoItemColor.SUCCESS.color,
                                        )
                                    },
                                    value = DUE_DATE,
                                    color = AdditionalInfoItemColor.SUCCESS.color,
                                    isConstantItem = true,
                                ),
                            ),
                            syncProgressItem = syncProgressItem(),
                        ),
                    ),
                    onCardClick = {},
                )

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(
                            text = "09/18/2021",
                            style = eventsInTeiDashboardTitleStyle,
                            color = TextColor.OnSurface,
                        ),
                        description = ListCardDescriptionModel(text = "Treatment visits"),
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = listOf(
                                AdditionalInfoItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Event,
                                            contentDescription = ICON_BUTTON_DESCRIPTION,
                                            tint = AdditionalInfoItemColor.SUCCESS.color,
                                        )
                                    },
                                    value = DUE_DATE,
                                    color = AdditionalInfoItemColor.SUCCESS.color,
                                    isConstantItem = true,
                                ),
                                AdditionalInfoItem(
                                    key = "Drug resistance",
                                    value = "Monoresistance",
                                ),
                                AdditionalInfoItem(
                                    key = "treatment",
                                    value = "Initial regiment- first-line drugs",
                                ),
                            ),
                            syncProgressItem = syncProgressItem(),
                        ),
                    ),
                    onCardClick = {},
                )
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(
                            text = "Scheduled for 09/18/2021",
                            style = eventsInTeiDashboardTitleStyle,
                            color = TextColor.OnSurface,
                        ),
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = listOf(
                                AdditionalInfoItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Event,
                                            contentDescription = ICON_BUTTON_DESCRIPTION,
                                            tint = AdditionalInfoItemColor.SUCCESS.color,
                                        )
                                    },
                                    value = DUE_DATE,
                                    color = AdditionalInfoItemColor.SUCCESS.color,
                                    isConstantItem = true,
                                ),
                                AdditionalInfoItem(
                                    key = "Drug resistance",
                                    value = "Monoresistance",
                                ),
                                AdditionalInfoItem(
                                    key = "treatment",
                                    value = "Initial regiment- first-line drugs",
                                ),
                            ),
                            syncProgressItem = syncProgressItem(),
                        ),
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
                                avatarSize = MetadataAvatarSize.M(),
                                tintColor = SurfaceColor.Primary,
                            ),
                        )
                    },
                    onCardClick = {},
                )
            }

            ColumnComponentContainer("Selectable list cards") {
                var selectionState by remember {
                    mutableStateOf(SelectionState.NONE)
                }

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = DEFAULT_NAME),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemList.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        selectionState = selectionState,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
                    onCardClick = {},
                    onCardSelected = { selectionState = it },
                )
                var selectionState2 by remember {
                    mutableStateOf(SelectionState.NONE)
                }

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = DEFAULT_NAME),
                        lastUpdated = LAST_UPDATED_HOURS,
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemListWithLongKeyText.toMutableList(),
                            syncProgressItem = syncProgressItem(),
                        ),
                        selectionState = selectionState2,
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
                    onCardClick = {},
                    onCardSelected = { selectionState2 = it },
                )
            }
        }
    }
}

private fun syncProgressItem() = AdditionalInfoItem(
    icon = {
        Icon(
            imageVector = Icons.Outlined.Sync,
            contentDescription = ICON_BUTTON_DESCRIPTION,
            tint = SurfaceColor.Primary,
        )
    },
    value = "Syncing...",
    color = SurfaceColor.Primary,
    isConstantItem = false,
)
