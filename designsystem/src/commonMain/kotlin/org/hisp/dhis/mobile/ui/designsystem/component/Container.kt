package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 ColumnComponentContainer wraps Material 3 [Column]
 * has a default spacing between items of 16 dp
 * with large top corner radius, white background and
 * vertical scroll enabled
 * @param title: is the value of the text to be shown for the row.
 * @param content: controls the content to be shown.
 * @param modifier: optional modifier.
 * @param verticalArrangement: optional vertical alignment.
 * @param horizontalAlignment: optional horizontal alignment.
 */
@Composable
fun ColumnComponentContainer(
    title: String? = null,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(Spacing.Spacing16),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable (() -> Unit),
) {
    Column(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        modifier = modifier
            .fillMaxSize().background(Color.White, Shape.LargeTop)
            .padding(horizontal = Spacing.Spacing16)
            .verticalScroll(rememberScrollState()),
    ) {
        title?.let {
            Title(title, modifier = Modifier.padding(top = Spacing.Spacing16))
        }
        content()
        Spacer(modifier = Modifier.padding(bottom = Spacing.Spacing16))
    }
}

/**
 * DHIS2 ColumnComponentItemContainer wraps Material 3 [Column]
 * has a default spacing between items of 16 dp
 * @param subTitle: is the value of the text to be shown for the component.
 * @param content: controls the content to be shown.
 * @param modifier: optional modifier.
 */
@Composable
fun ColumnComponentItemContainer(
    subTitle: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        horizontalAlignment = Alignment.Start,
        modifier = modifier.padding(bottom = Spacing.Spacing32),
    ) {
        subTitle?.let {
            SubTitle(subTitle)
        }
        content()
    }
}

/**
 * DHIS2 ListCardColumn wraps Material 3 [Column]
 * has a default spacing between items of 4 dp
 * vertical scroll enabled
 * @param content: controls the content to be shown.
 * @param spacing: the spacing between items.
 * @param modifier: optional modifier.
 */
@Composable
fun ListCardColumn(
    modifier: Modifier = Modifier,
    spacing: Dp = Spacing.Spacing4,
    content: @Composable (() -> Unit),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing),
        modifier = modifier.padding(horizontal = Spacing.Spacing4),
    ) {
        content()
    }
}

/**
 * DHIS2 RowComponentContainer wraps Material 3 [Row]
 * @param title: is the value of the text to be shown for the row.
 * @param content: controls the content to be shown.
 * @param modifier: optional modifier.
 */
@Composable
fun RowComponentContainer(
    title: String? = null,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
) {
    title?.let {
        SubTitle(title)
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing10),
    ) {
        content()
    }
}

/**
 * DHIS2 FlowRowComponentsContainer wraps Material 3 [FlowRow].
 * @param title: is the value of the text to be shown for the row.
 * @param spacing: is the distance in dp between the row items.
 * @param modifier: optional modifier.
 * @param content: controls the content to be shown.
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

/**
 * DHIS2 FlowColumnComponentsContainer wraps Material 3 [FlowColumn].
 * @param title: is the value of the text to be shown for the column.
 * @param spacing: is the distance in dp between the column items.
 * @param modifier: optional modifier.
 * @param content: controls the content to be shown.
 */
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

enum class Orientation {
    HORIZONTAL,
    VERTICAL,
}
