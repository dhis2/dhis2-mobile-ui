package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import org.hisp.dhis.mobile.ui.designsystem.component.internal.ImageCardData
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon

@Composable
fun MetadataIcon(
    imageCardData: ImageCardData,
    painter: Painter? = null,
) {
    when (imageCardData) {
        is ImageCardData.IconCardData ->
            Icon(
                painter = provideDHIS2Icon(imageCardData.iconRes),
                contentDescription = null,
            )

        is ImageCardData.CustomIconData -> {
            Image(
                painter = painter ?: BitmapPainter(imageCardData.image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    }
}
