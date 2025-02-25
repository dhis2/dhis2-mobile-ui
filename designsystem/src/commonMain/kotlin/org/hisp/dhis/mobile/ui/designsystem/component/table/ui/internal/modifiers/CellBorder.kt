package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

internal fun Modifier.cellBorder(
    selected: Boolean,
    borderWidth: Dp = 1.dp,
    borderColor: Color,
    backgroundColor: Color,
    dividerColor: Color,
) = this.then(
    drawBehind {
        val outBorderOffset = borderWidth.toPx()
        drawRect(
            color = backgroundColor,
            size = size,
        )
        drawRect(
            color = if (selected) borderColor else Color.Unspecified,
            topLeft = Offset(-outBorderOffset, -outBorderOffset),
            size = Size(width = outBorderOffset, height = size.height + outBorderOffset),
        )
        drawRect(
            color = if (selected) borderColor else Color.Unspecified,
            topLeft = Offset(size.width, -outBorderOffset),
            size = Size(width = outBorderOffset, height = size.height + outBorderOffset),
        )
        drawRect(
            color = if (selected) borderColor else Color.Unspecified,
            topLeft = Offset(0f, -outBorderOffset),
            size = Size(width = size.width + outBorderOffset, height = borderWidth.toPx()),
        )
        drawRect(
            color = if (selected) borderColor else Color.Unspecified,
            topLeft = Offset(-outBorderOffset, size.height),
            size = Size(width = size.width, height = borderWidth.toPx()),
        )
        drawRect(
            color = dividerColor,
            topLeft = Offset(0f, size.height - outBorderOffset),
            size = Size(width = size.width, height = borderWidth.toPx()),
        )
        drawCircle(
            color = if (selected) Color.White else Color.Unspecified,
            radius = 4.dp.toPx(),
            center = Offset(0.5.dp.toPx(), 0.5.dp.toPx()),
            blendMode = BlendMode.SrcAtop,
        )
        drawCircle(
            color = if (selected) borderColor else Color.Unspecified,
            radius = 2.dp.toPx(),
            center = Offset(0.5.dp.toPx(), 0.5.dp.toPx()),
            blendMode = BlendMode.SrcAtop,
        )
        drawCircle(
            color = if (selected) Color.White else Color.Unspecified,
            radius = 4.dp.toPx(),
            center = Offset(size.width + 0.5.dp.toPx(), size.height + 0.5.dp.toPx()),
            blendMode = BlendMode.SrcAtop,
        )
        drawCircle(
            color = if (selected) borderColor else Color.Unspecified,
            radius = 2.dp.toPx(),
            center = Offset(size.width + 0.5.dp.toPx(), size.height + 0.5.dp.toPx()),
            blendMode = BlendMode.SrcAtop,
        )
    }.zIndex(if (selected) 1f else 0f)
        .graphicsLayer { clip = true },
)

internal fun Modifier.rowSupportForCellBorder(
    isCellSelectedOnRow: Boolean,
    isFirstCellOnRowSelected: Boolean,
    borderColor: Color,
    subRowCount: Int,
    subRowIndex: Int,
) = this.then(
    zIndex(
        if (isCellSelectedOnRow) {
            (subRowCount - subRowIndex).toFloat()
        } else {
            subRowCount + 1f
        },
    )
        .drawBehind {
            drawCircle(
                color = if (isFirstCellOnRowSelected) Color.White else Color.Unspecified,
                radius = 4.dp.toPx(),
                center = Offset(0.5.dp.toPx(), 0.5.dp.toPx()),
                blendMode = BlendMode.SrcAtop,
            )
            drawCircle(
                color = if (isFirstCellOnRowSelected) borderColor else Color.Unspecified,
                radius = 2.dp.toPx(),
                center = Offset(0.5.dp.toPx(), 0.5.dp.toPx()),
                blendMode = BlendMode.SrcAtop,
            )
        },
)
