package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncProblem
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.Avatar
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarStyleData
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.junit.Rule
import org.junit.Test

class ListCardSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    val loremShort =
        "Lorem ipsum dolor sit amet"
    val lorem =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    val loremMedium =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit."

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
        AdditionalInfoItem(key = loremShort, value = lorem),

        AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945", isConstantItem = true),
        AdditionalInfoItem(
            key = "Address:",
            value = "134 Main Road, Behind the temple, Citytown, Basil District, Granite State",
        ),
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
        AdditionalInfoItem(
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
        ),

    )

    @Test
    fun launchListCard() {
        paparazzi.snapshot {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.Spacing4),
                modifier = Modifier.padding(horizontal = Spacing.Spacing8),
            ) {
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "Kunal Choudary, M, 55"),
                        lastUpdated = "24 min",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = enrollmentCompletedList.toMutableList(),
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
                        title = ListCardTitleModel(text = "Palak Khanna, F, 61"),
                        lastUpdated = "5 hours",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemListWithMediumKeyText.toMutableList(),
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
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
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
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "Palak Khanna, F, 61"),
                        lastUpdated = "5 hours",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = basicAdditionalItemListWithLongValue.toMutableList(),
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
                    ),
                    listAvatar = {
                        Avatar(
                            style = AvatarStyleData.Text("P"),
                        )
                    },
                    onCardClick = {},
                )
            }
        }
    }
}
