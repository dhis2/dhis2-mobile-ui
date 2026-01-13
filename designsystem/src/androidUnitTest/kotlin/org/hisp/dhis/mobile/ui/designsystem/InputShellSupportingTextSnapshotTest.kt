package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.Rule
import org.junit.Test

class InputShellSupportingTextSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInputShellSupportingTextDataInputStyle() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewContextConfigurationEffect()
            }
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(),
                )
            }
            InputText(
                "Label",
                inputTextFieldValue = inputValue,
                supportingText =
                    listOf(
                        SupportingTextData(
                            "Data",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.FOCUSED,
                onValueChanged = {},
            )
        }
    }

    @Test
    fun launchInputShellSupportingTextParameterInputStyle() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewContextConfigurationEffect()
            }
            var inputValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
                mutableStateOf(
                    TextFieldValue(),
                )
            }
            InputText(
                "Label",
                inputTextFieldValue = inputValue,
                supportingText =
                    listOf(
                        SupportingTextData(
                            "Data",
                            SupportingTextState.DEFAULT,
                        ),
                    ),
                state = InputShellState.FOCUSED,
                inputStyle = InputStyle.ParameterInputStyle(),
                onValueChanged = {},
            )
        }
    }
}
