package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme

/**
 * Composable function to display table actions with a title and action icons.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param title The title of the table actions.
 * @param actionIcons A composable function to display the action icons.
 */
@Composable
internal fun TableActions(modifier: Modifier, title: String, actionIcons: @Composable () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Absolute.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.TableChart,
            contentDescription = "",
            tint = TableTheme.colors.primary,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.bodyMedium,
        )
        actionIcons()
    }
}
