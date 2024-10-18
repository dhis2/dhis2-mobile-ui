package org.hisp.dhis.mobile.ui.designsystem.component.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SelectableDates
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTimeVisualTransformation

@Stable
interface InputDateTimeState {
    val uiData: InputDateTimeData
    val inputTextFieldValue: TextFieldValue?
    val inputState: InputShellState
    val legendData: LegendData?
    val supportingText: List<SupportingTextData>?
}

@Stable
internal class InputDateTimeStateImpl(
    override val uiData: InputDateTimeData,
    override val inputTextFieldValue: TextFieldValue,
    override val inputState: InputShellState,
    override val legendData: LegendData?,
    override val supportingText: List<SupportingTextData>?,

) : InputDateTimeState

@Composable
fun rememberInputDateTimeState(
    inputDateTimeData: InputDateTimeData,
    inputTextFieldValue: TextFieldValue = TextFieldValue(),
    inputState: InputShellState = InputShellState.UNFOCUSED,
    legendData: LegendData? = null,
    supportingText: List<SupportingTextData>? = null,
): InputDateTimeState = remember(
    inputTextFieldValue,
    inputState,
    legendData,
    supportingText,
) {
    InputDateTimeStateImpl(
        inputDateTimeData,
        inputTextFieldValue,
        inputState,
        legendData,
        supportingText,
    )
}

data class InputDateTimeData(
    val title: String,
    val inputStyle: InputStyle = InputStyle.DataInputStyle(),
    val imeAction: ImeAction = ImeAction.Next,
    val isRequired: Boolean = false,
    val actionType: DateTimeActionType = DateTimeActionType.DATE_TIME,
    val allowsManualInput: Boolean = true,
    val visualTransformation: DateTimeVisualTransformation,
    val is24hourFormat: Boolean = false,
    val acceptText: String? = null,
    val cancelText: String? = null,
    val outOfRangeText: String? = null,
    val incorrectHourFormatText: String? = null,
    val selectableDates: SelectableDates = SelectableDates("01011940", "12312300"),
    val yearRange: IntRange = IntRange(1970, 2100),

)
