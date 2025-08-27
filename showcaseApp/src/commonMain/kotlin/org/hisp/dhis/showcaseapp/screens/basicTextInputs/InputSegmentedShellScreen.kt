package org.hisp.dhis.showcaseapp.screens.basicTextInputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputSegmentedShell
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.model.SegmentedShellType

@Composable
fun InputSegmentedShellScreen() {
    ColumnScreenContainer(title = BasicTextInputs.INPUT_SEGMENTED_SHELL.label) {
        ColumnComponentContainer("Numeric") {
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 5,
                initialValue = null,
                supportingTextData =
                    SupportingTextData(
                        text = "This is a message",
                        state = SupportingTextState.DEFAULT,
                    ),
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 6,
                initialValue = "123456",
                supportingTextData =
                    SupportingTextData(
                        text = "This is an error message",
                        state = SupportingTextState.ERROR,
                    ),
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 4,
                initialValue = null,
                supportingTextData = null,
            )

            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 9,
                initialValue = null,
                supportingTextData = null,
            )
        }

        ColumnComponentContainer("Numbers and letters") {
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 5,
                initialValue = null,
                supportingTextData =
                    SupportingTextData(
                        text = "This is a message",
                        state = SupportingTextState.DEFAULT,
                    ),
                segmentedShellType = SegmentedShellType.LettersAndNumbers,
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 6,
                initialValue = "1A2B3C",
                supportingTextData =
                    SupportingTextData(
                        text = "This is an error message",
                        state = SupportingTextState.ERROR,
                    ),
                segmentedShellType = SegmentedShellType.LettersAndNumbers,
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 4,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.LettersAndNumbers,
            )

            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 9,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.LettersAndNumbers,
            )
        }

        ColumnComponentContainer("Letters") {
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 5,
                initialValue = null,
                supportingTextData =
                    SupportingTextData(
                        text = "This is a message",
                        state = SupportingTextState.DEFAULT,
                    ),
                segmentedShellType = SegmentedShellType.Letters,
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 6,
                initialValue = "ABCDEF",
                supportingTextData =
                    SupportingTextData(
                        text = "This is an error message",
                        state = SupportingTextState.ERROR,
                    ),
                segmentedShellType = SegmentedShellType.Letters,
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 4,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.Letters,
            )

            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 9,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.Letters,
            )
        }
    }
}
