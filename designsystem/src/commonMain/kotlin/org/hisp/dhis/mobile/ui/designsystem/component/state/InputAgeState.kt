package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.ImeAction
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData

@Stable
interface InputAgeState {
    val uiData: InputAgeData
    val inputType: AgeInputType
    val inputState: InputShellState
    val legendData: LegendData?
    val supportingText: List<SupportingTextData>?
}

@Stable
internal class InputAgeStateImpl(
    override val uiData: InputAgeData,
    override val inputType: AgeInputType,
    override val inputState: InputShellState,
    override val legendData: LegendData?,
    override val supportingText: List<SupportingTextData>?,
) : InputAgeState

@Composable
fun rememberInputAgeState(
    inputAgeData: InputAgeData,
    inputType: AgeInputType = AgeInputType.None,
    inputState: InputShellState = InputShellState.UNFOCUSED,
    legendData: LegendData? = null,
    supportingText: List<SupportingTextData>? = null,
): InputAgeState = remember(
    inputType,
    inputState,
    legendData,
    supportingText,
) {
    InputAgeStateImpl(
        inputAgeData,
        inputType,
        inputState,
        legendData,
        supportingText,
    )
}

data class InputAgeData(
    val title: String,
    val inputStyle: InputStyle = InputStyle.DataInputStyle(),
    val isRequired: Boolean = false,
    val imeAction: ImeAction = ImeAction.Next,
    val dateOfBirthLabel: String? = null,
    val orLabel: String? = null,
    val ageLabel: String? = null,
    val acceptText: String? = null,
    val cancelText: String? = null,
    val is24hourFormat: Boolean = false,
    val selectableDates: SelectableDates? = null,
)
