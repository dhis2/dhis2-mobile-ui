package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
internal fun VerticalGrid(
    columns: Int,
    itemCount: Int,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(Spacing.Spacing0),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(Spacing.Spacing0),
    content: @Composable BoxScope.(Int) -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = verticalArrangement) {
        var rows = (itemCount / columns)
        if (itemCount.mod(columns) > 0) {
            rows += 1
        }

        for (rowId in 0 until rows) {
            val firstIndex = rowId * columns

            Row(
                horizontalArrangement = horizontalArrangement,
                modifier = Modifier.height(IntrinsicSize.Min),
            ) {
                for (columnId in 0 until columns) {
                    val index = firstIndex + columnId
                    Box(
                        modifier =
                            Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .weight(1f),
                    ) {
                        if (index < itemCount) {
                            content.invoke(this, index)
                        }
                    }
                }
            }
        }
    }
}
