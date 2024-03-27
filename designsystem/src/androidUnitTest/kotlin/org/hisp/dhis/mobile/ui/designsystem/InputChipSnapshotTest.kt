package org.hisp.dhis.mobile.ui.designsystem

import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputChip
import org.junit.Rule
import org.junit.Test

class InputChipSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchChip() {
        paparazzi.snapshot {
            ColumnComponentContainer {
                InputChip(label = "Label", selected = false, badge = "3")
                InputChip(label = "Label", selected = true, badge = "3")
            }
        }
    }
}
