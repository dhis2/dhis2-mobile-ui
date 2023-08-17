package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import org.hisp.dhis.mobileui.designsystem.theme.Spacing

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
    content: @Composable (() -> Unit)
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        modifier = Modifier.padding(Spacing.Spacing10).verticalScroll(rememberScrollState())
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
    content: @Composable (() -> Unit)
) {
    title?.let {
        Text(title, style = MaterialTheme.typography.titleMedium)
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10),
        modifier = Modifier.padding(Spacing.Spacing10)
    ) {
        content()
    }
}
