package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PhoneEnabled
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.CardDetail
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBarData
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.junit.Rule
import org.junit.Test

class CardDetailSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    val lorem =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam. Lorem ipsum dolor sit amet, consectetur adipiscing elit."

    val teiDetailList =
        listOf(
            AdditionalInfoItem(key = "National ID:", value = "001-224-789"),
            AdditionalInfoItem(
                key = lorem,
                value = "+234 554",
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
            AdditionalInfoItem(key = lorem, value = lorem),
            AdditionalInfoItem(key = "Enrolled in:", value = "PHC Blueberry", isConstantItem = true),
            AdditionalInfoItem(
                value = "PHC Blueberry",
                isConstantItem = true,
            ),
            AdditionalInfoItem(
                key = lorem,
                value =
                    "Tuberculosis, Nutrition, " +
                        "Assistance Program, Malaria Diagnosis",
                action = {},
                color = SurfaceColor.Primary,
                isConstantItem = true,
            ),
            AdditionalInfoItem(
                key = lorem,
                value =
                    "Tuberculosis, Nutrition, " +
                        "Assistance Program, Malaria Diagnosis",
                action = {},
                color = SurfaceColor.Primary,
            ),
        )

    @Test
    fun launchCardDetail() {
        paparazzi.snapshot {
            ColumnScreenContainer(title = "Card Detail and Info Bar") {
                InfoBar(
                    InfoBarData(
                        text = "Not Synced",
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Sync,
                                contentDescription = "not synced",
                                tint = TextColor.OnSurfaceLight,
                            )
                        },
                        actionText = "Sync",
                        onClick = {},
                        color = TextColor.OnSurfaceLight,
                        backgroundColor = Color(0xFFEFF6FA),
                    ),
                )
                InfoBar(
                    InfoBarData(
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
                    ),
                )

                CardDetail(
                    avatar = null,
                    title = "Narayan Khanna, M, 32",
                    additionalInfoList = teiDetailList,
                    expandLabelText = "Show more",
                    shrinkLabelText = "Show less",
                )
            }
        }
    }
}
