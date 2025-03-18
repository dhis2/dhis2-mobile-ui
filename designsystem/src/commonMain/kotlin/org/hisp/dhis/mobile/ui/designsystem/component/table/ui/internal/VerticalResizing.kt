package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SwapHorizontalCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.internal.ResizingCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableDimensions
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
internal fun VerticalResizingView(
    modifier: Modifier = Modifier,
    provideResizingCell: () -> ResizingCell?,
) {
    val colorPrimary = TableTheme.colors.primary
    provideResizingCell()?.let { resizingCell ->
        val offsetX = resizingCell.initialPosition.x + Spacing.Spacing12.value + resizingCell.draggingOffsetX
        var tableOffset: Offset? by remember { mutableStateOf(null) }

        Box(
            modifier
                .onGloballyPositioned {
                    tableOffset = it.positionInRoot()
                }
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .fillMaxHeight()
                .drawBehind {
                    drawRect(
                        color = colorPrimary,
                        topLeft = Offset(0f, 0f),
                        size = Size(2.dp.toPx(), size.height),
                    )
                }
                .graphicsLayer(clip = false),
        ) {
            tableOffset?.let {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset {
                            IntOffset(
                                x = -19.dp.roundToPx(),
                                y = -8.dp.roundToPx() + resizingCell.initialPosition.y.roundToInt() - it.y.roundToInt(),
                            )
                        }
                        .background(
                            color = colorPrimary,
                            shape = CircleShape,
                        )
                        .size(Spacing.Spacing40)
                        .padding(8.dp),
                    imageVector = Icons.Outlined.SwapHorizontalCircle,
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }
    }
}

@Composable
internal fun VerticalResizingRule(
    checkMaxMinCondition: (dimensions: TableDimensions, currentOffsetX: Float) -> Boolean,
    onHeaderResize: (Float) -> Unit,
    onResizing: (ResizingCell?) -> Unit,
    modifier: Modifier = Modifier,
    inverse: Boolean = false,
) {
    var dimensions by remember { mutableStateOf<TableDimensions?>(null) }
    dimensions = TableTheme.dimensions

    val minOffset = with(LocalDensity.current) {
        when (inverse) {
            true -> -5
            false -> 5
        }.dp.toPx()
    }
    var offsetX by remember { mutableStateOf(minOffset) }
    var positionInRoot by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier
            .fillMaxHeight()
            .width(Spacing.Spacing48)
            .offset(if (inverse) -Spacing.Spacing24 else Spacing.Spacing24)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        onResizing(null)
                        if (abs(offsetX) > abs(minOffset)) {
                            onHeaderResize(offsetX)
                        }
                        offsetX = minOffset
                    },
                ) { change, dragAmount ->
                    change.consume()
                    val offsetChange = when (inverse) {
                        true -> offsetX - dragAmount.x
                        false -> offsetX + dragAmount.x
                    }
                    if (checkMaxMinCondition(dimensions!!, offsetChange)) {
                        offsetX = offsetChange
                    }
                    when (inverse) {
                        true -> onResizing(ResizingCell(positionInRoot, -offsetX))
                        false -> onResizing(ResizingCell(positionInRoot, offsetX))
                    }
                }
            },
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    color = TableTheme.colors.primary,
                    shape = CircleShape,
                )
                .size(Spacing.Spacing40),
            onClick = {},
        ) {
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .onGloballyPositioned { coordinates ->
                        positionInRoot = coordinates.positionInRoot()
                    },
                imageVector = Icons.Outlined.SwapHorizontalCircle,
                contentDescription = "",
                tint = Color.White,
            )
        }
    }
}
