package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.LocalTableColors
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.extensions.isNumeric
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_VALUE_TEST_TAG

@Composable
internal fun TextCell(
    maxLines: Int,
    cell: TableCell,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxSize()
            .testTag(CELL_VALUE_TEST_TAG)
            .padding(TableTheme.dimensions.cellPaddingValues)
            .wrapContentHeight(Alignment.CenterVertically),
        text = cell.value ?: "",
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle.Default.copy(
            fontSize = TableTheme.dimensions.defaultCellTextSize,
            textAlign = if (cell.value.isNumeric()) TextAlign.End else TextAlign.Start,
            color = LocalTableColors.current.cellTextColor(
                hasError = cell.error != null,
                hasWarning = cell.warning != null,
                isEditable = cell.editable,
            ),
        ),
    )
}
