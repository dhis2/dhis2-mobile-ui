package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneEnabled
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.EventBusy
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncProblem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.Avatar
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarStyle
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.junit.Rule
import org.junit.Test

class ListCardSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchListCard() {
        paparazzi.snapshot {
            val lorem =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val loremShort =
                "Lorem ipsum dolor sit amet"
            val loremMedium =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            val basicAdditionalItemList = listOf(
                AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1933"),
            )
            val basicAdditionalItemListWithLongKeyText = listOf(
                AdditionalInfoItem(key = lorem, value = lorem),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1933"),
            )
            val basicAdditionalItemListWithMediumKeyText = listOf(
                AdditionalInfoItem(key = loremMedium, value = lorem),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1933"),
            )
            val basicAdditionalItemListWithLongValue = listOf(
                AdditionalInfoItem(key = loremShort, value = lorem),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1933"),
            )
            val enrollmentCompletedList = listOf(
                AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
                AdditionalInfoItem(key = "Address:", value = "134 Main Road, Behind the temple, Citytown, Basil District, Granite State"),
                AdditionalInfoItem(key = "Enrolled in:", value = "12/12/1945"),
                AdditionalInfoItem(
                    key = lorem,
                    value = "Tuberculosis, Nutrition \n" +
                        "Assistance Program, Malaria Diagnosis",
                ),
                AdditionalInfoItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = "Icon Button",
                            tint = AdditionalInfoItemColor.SUCCESS.color,
                        )
                    },
                    value = "Enrollment completed",
                    color = AdditionalInfoItemColor.SUCCESS.color,
                    isConstantItem = true,
                ),

            )

            val teiDetailList = listOf(
                AdditionalInfoItem(key = "National ID:", value = "001-224-789"),
                AdditionalInfoItem(
                    key = "Phone:",
                    value = "+234 123 111 6785",
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.PhoneEnabled,
                            contentDescription = "Icon Button",
                            tint = SurfaceColor.Primary,
                        )
                    },
                    color = SurfaceColor.Primary,
                    action = {},
                ),
                AdditionalInfoItem(key = "Address:", value = "134 Main Road, Behind the temple, Citytown, Basil District, Granite State"),
                AdditionalInfoItem(key = "Enrolled in:", value = "PHC Blueberry"),
                AdditionalInfoItem(
                    key = lorem,
                    value = "Tuberculosis, Nutrition," +
                        "Assistance Program, Malaria Diagnosis",
                    action = {},
                    color = SurfaceColor.Primary,
                ),
            )
            val fullItemList = listOf(
                AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
                AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
                AdditionalInfoItem(key = "Enrolled in:", value = "12/12/1945"),
                AdditionalInfoItem(
                    key = "Programs:",
                    value = "Tuberculosis, Nutrition \n" +
                        "Assistance Program, Malaria Diagnosis",
                ),
                AdditionalInfoItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Icon Button",
                            tint = TextColor.OnDisabledSurface,
                        )
                    },
                    value = "Enrollment cancelled",
                    color = TextColor.OnDisabledSurface,
                    isConstantItem = true,
                ),
                AdditionalInfoItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.EventBusy,
                            contentDescription = "Icon Button",
                            tint = AdditionalInfoItemColor.ERROR.color,
                        )
                    },
                    value = "Baby post natal overdue 6 days",
                    color = AdditionalInfoItemColor.ERROR.color,
                    isConstantItem = true,
                ),
                AdditionalInfoItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.SyncProblem,
                            contentDescription = "Icon Button",
                            tint = AdditionalInfoItemColor.WARNING.color,
                        )
                    },
                    value = "Sync warning",
                    color = AdditionalInfoItemColor.WARNING.color,
                    isConstantItem = true,
                ),
            )
            var showLoading1 by remember {
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
            SubTitle("Without shadow")

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
            Spacer(Modifier.size(Spacing.Spacing16))
            SubTitle("Single events list:")
            SubTitle("With shadow:")

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
            Spacer(Modifier.size(Spacing.Spacing16))
            SubTitle("Without shadow:")

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
            Spacer(Modifier.size(Spacing.Spacing16))
            SubTitle("Events in timeline in TEI dashboard:")
            SubTitle("With shadow:")

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
            Spacer(Modifier.size(Spacing.Spacing16))

            SubTitle("Without shadow:")
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
                lastUpdated = "now",
                additionalInfoList = eventsTimelineTeiDashboardList,
                onCardClick = {},
            )

            SubTitle("ListCards for events  displayed in TEI dashboard:")

            val eventsInTeiDashboardTitleStyle = MaterialTheme.typography.titleSmall

            ListCard(

                title = ListCardTitleModel(text = "Scheduled for 09/18/2021", style = eventsInTeiDashboardTitleStyle, color = TextColor.OnSurface),
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

                title = ListCardTitleModel(text = "09/18/2021", style = eventsInTeiDashboardTitleStyle, color = TextColor.OnSurface),
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
                    AdditionalInfoItem(key = "Drug resistance:", value = "Monoresistance"),
                    AdditionalInfoItem(key = "treatment:", value = "Initial regiment- first-line drugs"),
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
                title = ListCardTitleModel(text = "Scheduled for 09/18/2021", style = eventsInTeiDashboardTitleStyle, color = TextColor.OnSurface),
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
                    AdditionalInfoItem(key = "Drug resistance:", value = "Monoresistance"),
                    AdditionalInfoItem(key = "treatment:", value = "Initial regiment- first-line drugs"),
                ),
                onCardClick = {},
            )
            Spacer(Modifier.size(Spacing.Spacing16))
        }
    }
}
