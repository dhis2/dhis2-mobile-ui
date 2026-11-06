package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.junit.Rule
import org.junit.Test

class MetadataAvatarSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchAvatarTest() {
        paparazzi.snapshot {
            RowComponentContainer {
                listOf(
                    MetadataAvatarSize.XS(),
                    MetadataAvatarSize.S(),
                    MetadataAvatarSize.M(),
                    MetadataAvatarSize.L(),
                    MetadataAvatarSize.XL(),
                ).forEach { metadataAvatarSize ->
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
        }
    }
}
