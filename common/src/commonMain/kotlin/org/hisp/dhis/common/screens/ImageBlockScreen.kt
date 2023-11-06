package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.component.ImageBlock
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageBlockScreen() {
    val sampleImage = provideSampleImage()
    ImageBlock(
        load = { sampleImage },
        painterFor = { remember { it } },
        onClick = {},
        onImageClick = {},
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun provideSampleImage(): Painter =
    painterResource("drawable/sample.png")
