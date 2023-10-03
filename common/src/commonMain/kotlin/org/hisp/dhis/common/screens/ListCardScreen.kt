package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.additionalItemList
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ListAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.ListAvatarStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun ListCardScreen() {
    ColumnComponentContainer(title = "List Card Components") {
        ListCard(
            listAvatar = {
                ListAvatar(
                    textAvatar = "X",
                    style = ListAvatarStyle.TEXT,
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
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            onCardClick = {},
        )
    }
}
