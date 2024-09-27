package org.hisp.dhis.common.screens.toggleableInputs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ImageCardData
import org.hisp.dhis.mobile.ui.designsystem.component.InputSequential
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun InputSequentialScreen(imageBitmapLoader: (() -> ImageBitmap)?) {
    var sequentialSelectedItem by remember { mutableStateOf<ImageCardData?>(null) }

    val inputCardData = remember {
        mutableStateListOf(
            ImageCardData.IconCardData(
                uid = "3eea3133-8a2f-4bba-a259-8b4b457d5ad0",
                label = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dolor lacus, aliquam.",
                iconRes = "dhis2_boy_0510y_outline",
                iconTint = SurfaceColor.Primary,
            ),
            ImageCardData.IconCardData(
                uid = "7e0cb105-c276-4f12-9f56-a26af8314121",
                label = "Stethoscope",
                iconRes = "dhis2_stethoscope_positive",
                iconTint = Color(0xFFFF8400),
            ),
            ImageCardData.IconCardData(
                uid = "72269f6b-6b99-4d2e-a667-09f20c2097e0",
                label = "Medicines",
                iconRes = "dhis2_medicines_positive",
                iconTint = Color(0xFFEB0085),
            ),
            ImageCardData.IconCardData(
                uid = "37b81748-e9b4-4f74-a50a-59b945e54aa4",
                label = "Sayana press",
                iconRes = "dhis2_sayana_press_positive",
                iconTint = Color(0xFF1FDB00),
            ),
            ImageCardData.IconCardData(
                uid = "6d6a5de8-7707-418f-8424-bb3c4557bb46",
                label = "Happy",
                iconRes = "dhis2_happy_positive",
                iconTint = SurfaceColor.CustomGreen,
            ),
            ImageCardData.IconCardData(
                uid = "4ee5944e-b75f-4597-95bc-266b38b25604",
                label = "Sad",
                iconRes = "dhis2_sad_positive",
                iconTint = SurfaceColor.CustomYellow,
            ),
            imageBitmapLoader?.invoke()?.let {
                ImageCardData.CustomIconData(
                    uid = "4ee5944e-b75f-4597-95bc-266b38b25604",
                    label = "Sad",
                    image = it,
                )
            },
        )
    }

    val painterFor = provideSampleImages(
        inputCardData.filterIsInstance<ImageCardData.CustomIconData>().map { it },
    )

    ColumnScreenContainer(title = ToggleableInputs.INPUT_SEQUENTIAL.label) {
        ColumnComponentContainer("Basic state") {
            InputSequential(
                title = "Label",
                data = inputCardData.filterNotNull(),
                selectedData = sequentialSelectedItem,
                onSelectionChanged = { newSelectedItem ->
                    sequentialSelectedItem = if (sequentialSelectedItem == newSelectedItem) {
                        null
                    } else {
                        newSelectedItem
                    }
                },
                state = InputShellState.UNFOCUSED,
                painterFor = painterFor,
            )
        }

        ColumnComponentContainer("Disabled state") {
            InputSequential(
                title = "Label",
                data = inputCardData.filterNotNull(),
                selectedData = sequentialSelectedItem,
                state = InputShellState.DISABLED,
                onSelectionChanged = {
                    // no-op
                },
                painterFor = painterFor,
            )
        }
    }
}

@Composable
private fun provideSampleImages(image: List<ImageCardData.CustomIconData>): Map<String, Painter> =
    image.associate { it.uid to BitmapPainter(it.image) }
