package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.cells

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.TableTheme
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalInteraction
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.internal.semantics.CELL_VALUE_TEST_TAG

@Composable
internal fun CheckBoxCell(
    modifier: Modifier = Modifier,
    checkBoxData: CheckBoxData,
) {
    val localInteraction = LocalInteraction.current
    CheckBox(
        modifier = modifier
            .testTag(CELL_VALUE_TEST_TAG)
            .padding(TableTheme.dimensions.cellPaddingValues)
            .wrapContentHeight(Alignment.CenterVertically),
        checkBoxData = checkBoxData,
        onCheckedChange = {
            localInteraction.onChecked(it)
        },
    )
}
