package org.hisp.dhis.showcaseapp.screens.basicTextInputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputSegmentedShell
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.Orientation
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.model.SegmentedShellType
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun InputSegmentedShellScreen() {
    val styleOptions =
        remember {
            listOf(
                RadioButtonData(
                    uid = "DarkInputStyle",
                    selected = true,
                    enabled = true,
                    textInput = "Dark",
                ),
                RadioButtonData(
                    uid = "BrightInputStyle",
                    selected = false,
                    enabled = true,
                    textInput = "Bright",
                ),
            )
        }
    var selectedStyle by remember { mutableStateOf(styleOptions[0]) }
    val (inputStyle, backgroundColor) =
        when (selectedStyle.uid) {
            "DarkInputStyle" -> InputStyle.DarkInputStyle() to SurfaceColor.SurfaceBright
            else -> InputStyle.BrightInputStyle() to SurfaceColor.Surface
        }

    ColumnScreenContainer(title = BasicTextInputs.INPUT_SEGMENTED_SHELL.label) {
        ColumnComponentContainer("Input Style") {
            RadioButtonBlock(
                orientation = Orientation.HORIZONTAL,
                content = styleOptions,
                itemSelected = selectedStyle,
                onItemChange = { selectedStyle = it },
            )
        }
        ColumnComponentContainer(
            modifier =
                Modifier
                    .background(backgroundColor, Shape.Large)
                    .padding(Spacing.Spacing8),
            subTitle = "Numeric",
        ) {
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 5,
                initialValue = null,
                supportingTextData =
                    SupportingTextData(
                        text = "This is a message",
                        state = SupportingTextState.DEFAULT,
                    ),
                inputStyle = inputStyle,
            )
            var newValue by remember { mutableStateOf("123456") }
            var supportingTextData by remember(newValue) {
                mutableStateOf(
                    SupportingTextData(
                        text = "This is an error message",
                        state = SupportingTextState.ERROR,
                    ),
                )
            }
            var savedValue by remember { mutableStateOf("") }
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 6,
                initialValue = newValue,
                supportingTextData = supportingTextData,
                inputStyle = inputStyle,
                onValueChanged = {
                    savedValue = it
                },
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    enabled = true,
                    style = ButtonStyle.FILLED,
                    text = "Validate",
                    onClick = {
                        newValue = savedValue
                    },
                )
            }
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 4,
                initialValue = null,
                supportingTextData = null,
                inputStyle = inputStyle,
            )

            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 9,
                initialValue = null,
                supportingTextData = null,
                inputStyle = inputStyle,
            )
        }

        ColumnComponentContainer(
            modifier =
                Modifier
                    .background(backgroundColor, Shape.Large)
                    .padding(Spacing.Spacing8),
            subTitle = "Numbers and letters",
        ) {
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
                inputStyle = inputStyle,
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
                inputStyle = inputStyle,
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 4,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.LettersAndNumbers,
                inputStyle = inputStyle,
            )

            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 9,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.LettersAndNumbers,
                inputStyle = inputStyle,
            )
        }

        ColumnComponentContainer(
            modifier =
                Modifier
                    .background(backgroundColor, Shape.Large)
                    .padding(Spacing.Spacing8),
            subTitle = "Letters",
        ) {
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
                inputStyle = inputStyle,
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
                inputStyle = inputStyle,
            )
            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 4,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.Letters,
                inputStyle = inputStyle,
            )

            InputSegmentedShell(
                modifier = Modifier.fillMaxWidth(),
                segmentCount = 9,
                initialValue = null,
                supportingTextData = null,
                segmentedShellType = SegmentedShellType.Letters,
                inputStyle = inputStyle,
            )
        }
    }
}
