package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme

@Composable
internal fun Modifier.cornerBackground(
    hasLabel: Boolean,
    isSelected: Boolean,
    selectedColor: Color = TableTheme.colors.primary,
    defaultColor: Color = TableTheme.colors.tableBackground,
    labelledColor: Color = TableTheme.colors.headerBackground2,
) = this.then(
    background(
        color =
            when {
                isSelected -> selectedColor
                hasLabel -> labelledColor
                else -> defaultColor
            },
    ),
)
