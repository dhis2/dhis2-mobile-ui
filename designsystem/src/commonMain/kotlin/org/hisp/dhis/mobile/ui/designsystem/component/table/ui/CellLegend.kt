package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

/**
 * Composable function to display a cell with a legend color.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param legendColor The color of the legend to be displayed.
 * @param content The content to be displayed inside the box.
 */
@Composable
internal fun CellLegendBox(
    modifier: Modifier = Modifier,
    legendColor: Color?,
    content: @Composable
    BoxScope.() -> Unit,
) {
    val boxModifier = legendColor?.let {
        val cornerSize = LocalTableDimensions.current.defaultLegendCornerSize
        val borderWidth = LocalTableDimensions.current.defaultLegendBorderWidth
        modifier
            .clip(shape = RoundedCornerShape(size = cornerSize))
            .drawBehind {
                drawRect(
                    color = legendColor,
                    topLeft = Offset(0f, 0f),
                    size = Size(borderWidth.toPx(), size.height),
                )
            }
            .background(color = legendColor.copy(alpha = 0.15f))
    } ?: modifier
    Box(
        modifier = boxModifier,
        content = content,
    )
}
