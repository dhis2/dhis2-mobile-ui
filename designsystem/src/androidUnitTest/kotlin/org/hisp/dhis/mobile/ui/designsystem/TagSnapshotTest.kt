package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Tag
import org.hisp.dhis.mobile.ui.designsystem.component.TagType
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class TagSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchChip() {
        paparazzi.snapshot {
            ColumnComponentContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
                TagType.entries.forEach {
                    Tag(
                        label = "Label",
                        type = it,
                    )
                }
            }
        }
    }
}
