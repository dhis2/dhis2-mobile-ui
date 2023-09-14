package org.hisp.dhis.common.screens

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.AvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconCardData
import org.hisp.dhis.mobile.ui.designsystem.component.IconCardInput
import org.hisp.dhis.mobile.ui.designsystem.component.IconCardLayoutType
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatar
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun IconCardsScreen() {
    var matrixSelectedItem by remember { mutableStateOf<IconCardData?>(null) }
    var sequentialSelectedItem by remember { mutableStateOf<IconCardData?>(null) }

    val inputCardData = remember {
        mutableStateListOf(
            IconCardData(
                uid = "3eea3133-8a2f-4bba-a259-8b4b457d5ad0",
                label = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam.",
                iconRes = "dhis2_microscope_positive",
                iconTint = SurfaceColor.Primary,
            ),
            IconCardData(
                uid = "7e0cb105-c276-4f12-9f56-a26af8314121",
                label = "Stethoscope",
                iconRes = "dhis2_stethoscope_positive",
                iconTint = Color(0xFFFF8400),
            ),
            IconCardData(
                uid = "72269f6b-6b99-4d2e-a667-09f20c2097e0",
                label = "Medicines",
                iconRes = "dhis2_medicines_positive",
                iconTint = Color(0xFFEB0085),
            ),
            IconCardData(
                uid = "37b81748-e9b4-4f74-a50a-59b945e54aa4",
                label = "Sayana press",
                iconRes = "dhis2_sayana_press_positive",
                iconTint = Color(0xFF1FDB00),
            ),
            IconCardData(
                uid = "6d6a5de8-7707-418f-8424-bb3c4557bb46",
                label = "Happy",
                iconRes = "dhis2_happy_positive",
                iconTint = SurfaceColor.CustomGreen,
            ),
            IconCardData(
                uid = "4ee5944e-b75f-4597-95bc-266b38b25604",
                label = "Sad",
                iconRes = "dhis2_sad_positive",
                iconTint = SurfaceColor.CustomYellow,
            ),
        )
    }

    ColumnComponentContainer(title = "Metadata Avatar") {
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

        IconCardInput(
            title = "Label",
            data = inputCardData,
            selectedData = matrixSelectedItem,
            layoutType = IconCardLayoutType.Matrix(),
            onSelectionChanged = { newSelectedItem ->
                matrixSelectedItem = if (matrixSelectedItem == newSelectedItem) {
                    null
                } else {
                    newSelectedItem
                }
            },
        )

        IconCardInput(
            title = "Label",
            data = inputCardData,
            selectedData = matrixSelectedItem,
            state = InputShellState.DISABLED,
            layoutType = IconCardLayoutType.Matrix(itemCount = 3),
            onSelectionChanged = {
                // no-op
            },
        )

        IconCardInput(
            title = "Label",
            data = inputCardData,
            selectedData = sequentialSelectedItem,
            layoutType = IconCardLayoutType.Sequential,
            onSelectionChanged = { newSelectedItem ->
                sequentialSelectedItem = if (matrixSelectedItem == newSelectedItem) {
                    null
                } else {
                    newSelectedItem
                }
            },
        )

        IconCardInput(
            title = "Label",
            data = inputCardData,
            selectedData = sequentialSelectedItem,
            layoutType = IconCardLayoutType.Sequential,
            state = InputShellState.DISABLED,
            onSelectionChanged = {
                // no-op
            },
        )
    }
}
