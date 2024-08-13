package org.hisp.dhis.common.screens.others

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.common.getAvailableMetadataAvatarSizes
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun MetadataAvatarScreen() {
    ColumnScreenContainer(title = "Metadata Avatar component") {
        RowComponentContainer {
            getAvailableMetadataAvatarSizes().forEach { metadataAvatarSize ->
                MetadataAvatar(
                    icon = { expectedSize ->
                        Icon(
                            modifier = Modifier.size(expectedSize),
                            painter = provideDHIS2Icon("dhis2_microscope_outline"),
                            contentDescription = "",
                        )
                    },
                    iconTint = SurfaceColor.Primary,
                    size = metadataAvatarSize,
                )
            }
        }
        RowComponentContainer {
            getAvailableMetadataAvatarSizes().forEach { metadataAvatarSize ->
                MetadataAvatar(
                    icon = { expectedSize ->
                        Icon(
                            modifier = Modifier.size(expectedSize),
                            painter = provideDHIS2Icon("dhis2_nurse_positive"),
                            contentDescription = "",
                        )
                    },
                    iconTint = SurfaceColor.CustomGreen,
                    size = metadataAvatarSize,
                )
            }
        }
        RowComponentContainer {
            getAvailableMetadataAvatarSizes().forEach { metadataAvatarSize ->
                MetadataAvatar(
                    icon = { expectedSize ->
                        Icon(
                            modifier = Modifier.size(expectedSize),
                            painter = provideDHIS2Icon("dhis2_medicines_negative"),
                            contentDescription = "",
                        )
                    },
                    iconTint = Color(0xFFE12F58),
                    size = metadataAvatarSize,
                )
            }
        }
    }
}
