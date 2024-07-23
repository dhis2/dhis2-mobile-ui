package org.hisp.dhis.common.screens.others

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun MetadataAvatarScreen() {
    ColumnScreenContainer(title = "Metadata Avatar component") {
        RowComponentContainer {
            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("dhis2_microscope_outline"),
                        contentDescription = "",
                    )
                },
                iconTint = SurfaceColor.Primary,
                size = AvatarSize.Normal,
            )
            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("dhis2_microscope_outline"),
                        contentDescription = "",
                    )
                },
                iconTint = SurfaceColor.Primary,
                size = AvatarSize.Large,
            )
        }
        RowComponentContainer {
            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("dhis2_nurse_positive"),
                        contentDescription = "",
                    )
                },
                iconTint = SurfaceColor.CustomGreen,
                size = AvatarSize.Normal,
            )
            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("dhis2_nurse_positive"),
                        contentDescription = "",
                    )
                },
                iconTint = SurfaceColor.CustomGreen,
                size = AvatarSize.Large,
            )
        }
        RowComponentContainer {
            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("dhis2_medicines_negative"),
                        contentDescription = "",
                    )
                },
                iconTint = Color(0xFFE12F58),
                size = AvatarSize.Normal,
            )
            MetadataAvatar(
                icon = {
                    Icon(
                        painter = provideDHIS2Icon("dhis2_medicines_negative"),
                        contentDescription = "",
                    )
                },
                iconTint = Color(0xFFE12F58),
                size = AvatarSize.Large,
            )
        }
    }
}
