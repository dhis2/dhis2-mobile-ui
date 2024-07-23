package org.hisp.dhis.common.screens.cards

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.common.screens.previews.teiDetailList
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItemColor
import org.hisp.dhis.mobile.ui.designsystem.component.CardDetail
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBarData
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun CardDetailScreen() {
    ColumnScreenContainer(title = Cards.CARD_DETAIL.label) {
        ColumnComponentContainer("Info Bar") {
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
        }

        ColumnComponentContainer("Card Detail") {
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
