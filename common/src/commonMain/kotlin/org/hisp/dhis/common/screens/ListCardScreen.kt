package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.EventBusy
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SyncProblem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoColumn
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ListAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.ListAvatarStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ListCardScreen() {
    ColumnComponentContainer(title = "List Card Components") {
        val additionalhideableItemList = listOf(
            AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
            AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
            AdditionalInfoItem(key = "Address:", value = "134 Main Road, Behind the temple, Citytown, Basil District, Granite State"),
            AdditionalInfoItem(key = "Enrolled in:", value = "12/12/1945"),
            AdditionalInfoItem(
                key = "Programs:",
                value = "Tuberculosis, Nutrition \n" +
                    "Assistance Program, Malaria Diagnosis",
            ),
        )
        val additionalConstantItemList = listOf(

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
            ),
            AdditionalInfoItem(
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.SyncProblem,
                        contentDescription = "Icon Button",
                        tint = TextColor.OnWarning,
                    )
                },
                value = "Sync warning",
                color = TextColor.OnWarning,
            ),
            AdditionalInfoItem(
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.EventBusy,
                        contentDescription = "Icon Button",
                        tint = SurfaceColor.Error,
                    )
                },
                value = "Baby post natal overdue 6 days",
                color = SurfaceColor.Error,
            ),
        )

        ListCard(
            listAvatar = {
                ListAvatar(
                    textAvatar = "X",
                    style = ListAvatarStyle.TEXT,
                )
            },
            title = "Anita Mathews, F, 72",
            lastUpdated = "5 hours",
            additionalInfo = { AdditionalInfoColumn(hideableItems = additionalhideableItemList, constantItems = additionalConstantItemList) },
            actionButton = {
                Button(
                    style = ButtonStyle.TONAL,
                    text = "Retry sync",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = "Icon Button",
                            tint = TextColor.OnPrimaryContainer,
                        )
                    },
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            },
        )
    }
}
