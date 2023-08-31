package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 ColumnComponentContainer wraps Material 3 [Column]
 * has a default spacing between items of 10 dp
 * vertical scroll enabled
 * @param title is the value of the text to be shown for the row.
 * @param content controls the content to be shown
 */
@Composable
fun ColumnComponentContainer(
    title: String? = null,
    content: @Composable (() -> Unit),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        modifier = Modifier.padding(Spacing.Spacing10).verticalScroll(rememberScrollState()),
    ) {
        title?.let {
            Text(title, style = MaterialTheme.typography.titleMedium)
        }
        Spacer(Modifier.size(Spacing.Spacing4))
        content()
    }
}

/**
 * DHIS2 RowComponentContainer wraps Material 3 [Row]
 * @param title is the value of the text to be shown for the row.
 * @param content controls the content to be shown
 */

@Composable
fun RowComponentContainer(
    title: String? = null,
    content: @Composable (() -> Unit),
) {
    title?.let {
        Text(title, style = MaterialTheme.typography.titleMedium)
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10),
        modifier = Modifier.padding(Spacing.Spacing10),
    ) {
        content()
    }
}

/**
 * DHIS2 FlowRowComponentsContainer wraps Material 3 [FlowRow]
 * @param title is the value of the text to be shown for the row.
 * @param spacing is the distance in dp between the row items
 * @param modifier customs the row component.
 * @param content controls the content to be shown
 */

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowComponentsContainer(
    title: String? = null,
    spacing: Dp,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
) {
    title?.let {
        Text(title, style = MaterialTheme.typography.titleMedium)
    }
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        modifier = modifier,
    ) {
        content()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowColumnComponentsContainer(
    title: String? = null,
    spacing: Dp,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
) {
    title?.let {
        Text(title, style = MaterialTheme.typography.titleMedium)
    }
    FlowColumn(
        verticalArrangement = Arrangement.spacedBy(spacing),
        modifier = modifier,
    ) {
        content()
    }
}
