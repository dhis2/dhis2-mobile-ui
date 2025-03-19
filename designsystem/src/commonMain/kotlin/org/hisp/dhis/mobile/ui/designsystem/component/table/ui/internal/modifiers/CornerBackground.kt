package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.modifiers

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableColors

@Composable
internal fun Modifier.cornerBackground(
    hasLabel: Boolean,
    isSelected: Boolean,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    defaultColor: Color = LocalTableColors.current.tableBackground,
    labelledColor: Color = LocalTableColors.current.headerBackground2,
) =
    this.then(
        background(
            color = when {
                isSelected -> selectedColor
                hasLabel -> labelledColor
                else -> defaultColor
            },
        ),
    )
