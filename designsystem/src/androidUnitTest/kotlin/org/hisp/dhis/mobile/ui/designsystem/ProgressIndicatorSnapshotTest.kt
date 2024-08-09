package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicator
import org.hisp.dhis.mobile.ui.designsystem.component.ProgressIndicatorType
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class ProgressIndicatorSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchChip() {
        paparazzi.snapshot {
            ColumnComponentContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
                ProgressIndicator(
                    type = ProgressIndicatorType.LINEAR,
                    progress = 0.25f,
                    hasError = false,
                )
                ProgressIndicator(
                    type = ProgressIndicatorType.LINEAR,
                    progress = 0.25f,
                    hasError = true,
                )
                ProgressIndicator(
                    type = ProgressIndicatorType.CIRCULAR,
                    progress = 0.25f,
                    hasError = false,
                )
                ProgressIndicator(
                    type = ProgressIndicatorType.CIRCULAR,
                    progress = 0.25f,
                    hasError = true,
                )
                ProgressIndicator(
                    type = ProgressIndicatorType.CIRCULAR_SMALL,
                    progress = 0.25f,
                    hasError = false,
                )
                ProgressIndicator(
                    type = ProgressIndicatorType.CIRCULAR_SMALL,
                    progress = 0.25f,
                    hasError = true,
                )
            }
        }
    }
}
