package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.internal.barcode.rememberBarcodeGenerator
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * Container to render barcode image and text for given
 * data
 *
 * @param data: Data to render barcode and text for
 */
@Composable
fun BarcodeBlock(
    data: String,
    modifier: Modifier = Modifier,
    barcodeSize: DpSize = DpSize(312.dp, 76.dp),
) {
    if (data.isNotBlank()) {
        Column(
            modifier = modifier.padding(vertical = Spacing.Spacing16)
                .requiredSizeIn(maxWidth = barcodeSize.width)
                .testTag("BARCODE_BLOCK_CONTAINER"),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val barcode by rememberBarcodeGenerator(data)

            Box(Modifier.size(barcodeSize)) {
                barcode?.let { bitmap ->
                    Image(
                        bitmap = bitmap,
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }

            Text(
                text = data,
                style = MaterialTheme.typography.titleMedium,
                color = TextColor.OnSurface,
                textAlign = TextAlign.Center,
            )
        }
    }
}
