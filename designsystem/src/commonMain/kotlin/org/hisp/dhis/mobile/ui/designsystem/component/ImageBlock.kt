package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.image.rememberImageProvider
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import java.io.File

@Composable
fun ImageBlock(
    file: File,
    downloadButtonVisible: Boolean = true,
    onClick: () -> Unit,
) {
    val imageBitmap by rememberImageProvider(file)
    if (imageBitmap != null) {
        Box(
            modifier = Modifier
                .padding(vertical = Spacing.Spacing8)
                .testTag("IMAGE_BLOCK_CONTAINER"),
        ) {
            imageBitmap?.let { bitmap ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(Radius.S))
                        .background(Color.Black)
                        .height(160.dp),
                    bitmap = bitmap,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }

            if (downloadButtonVisible) {
                SquareIconButton(
                    enabled = true,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(Spacing.Spacing4),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FileDownload,
                            contentDescription = "File download Button",
                        )
                    },
                ) {
                    onClick.invoke()
                }
            }
        }
    }
}
