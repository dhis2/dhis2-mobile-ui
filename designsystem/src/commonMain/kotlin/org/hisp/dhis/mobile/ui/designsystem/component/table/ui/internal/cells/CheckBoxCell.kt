package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCellContent
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalInteraction
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_VALUE_TEST_TAG

@Composable
internal fun CheckBoxCell(
    modifier: Modifier = Modifier,
    cell: TableCell,
) {
    val localInteraction = LocalInteraction.current
    CheckBox(
        modifier =
            modifier
                .testTag(CELL_VALUE_TEST_TAG)
                .wrapContentHeight(Alignment.CenterVertically),
        checkBoxData =
            CheckBoxData(
                uid = cell.id,
                checked = (cell.content as TableCellContent.Checkbox).isChecked,
                enabled = cell.editable,
                textInput = "",
            ),
        onCheckedChange = {
            localInteraction.onChecked(cell, it)
        },
    )
}
