package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputSegmentedShell
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.model.SegmentedShellType
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class InputSegmentedShellSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInputSegmentedShellTest() {
        paparazzi.snapshot {
            ColumnScreenContainer(modifier = Modifier.padding(Spacing.Spacing10)) {
                InputSegmentedShell(
                    segmentCount = 4,
                    supportingTextData =
                        SupportingTextData(
                            text = "Supporting text",
                        ),
                )
                InputSegmentedShell(
                    segmentCount = 4,
                    initialValue = "3333",
                    supportingTextData =
                        SupportingTextData(
                            text = "Supporting text",
                        ),
                )
                InputSegmentedShell(
                    segmentCount = 6,
                    supportingTextData =
                        SupportingTextData(
                            text = "Supporting text",
                        ),
                )
                InputSegmentedShell(
                    segmentCount = 6,
                    initialValue = "3333",
                    supportingTextData =
                        SupportingTextData(
                            text = "Supporting text",
                        ),
                )
                InputSegmentedShell(
                    segmentCount = 5,
                    initialValue = "3333",
                    supportingTextData =
                        SupportingTextData(
                            text = "Supporting text",
                            state = SupportingTextState.ERROR,
                        ),
                )
                InputSegmentedShell(
                    segmentCount = 5,
                    initialValue = "1A2B3",
                    segmentedShellType = SegmentedShellType.LettersAndNumbers,
                    supportingTextData = null,
                )
            }
        }
    }
}
