package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputChip
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class InputChipSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchChip() {
        paparazzi.snapshot {
            ColumnComponentContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
                InputChip(label = "Label", selected = false, badge = "3")
                InputChip(label = "Label", selected = true, badge = "3")
            }
        }
    }
}
