package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * Composable function to display an extended divider for table rows.
 *
 * @param tableId The ID of the table.
 */
@Composable
internal fun ExtendDivider(
    tableId: String,
    rowHeaderCount: Int,
) {
    val background = TableTheme.colors.primary
    val config = TableTheme.configuration
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = Spacing.Spacing16)) {
        Box(
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        (
                            TableTheme.dimensions
                                .rowHeaderWidth(
                                    groupedTables = config.groupTables,
                                    tableId = tableId,
                                ) * rowHeaderCount
                            ).toDp()
                    },
                )
                .height(8.dp)
                .background(Color.White)
                .drawBehind {
                    drawRect(
                        color = background,
                        topLeft = Offset(size.width - 1.dp.toPx(), 0f),
                        size = Size(1.dp.toPx(), size.height),
                    )
                },
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .background(Color.White),
        )
    }
}
