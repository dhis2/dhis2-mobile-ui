package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData

sealed interface InputModel

sealed class BasicTextInputModel : InputModel {
    abstract val title: String
    abstract val state: InputShellState
    abstract val inputStyle: InputStyle
    abstract val supportingText: List<SupportingTextData>?
    abstract val legendData: LegendData?
    abstract val inputTextFieldValue: TextFieldValue?
    abstract val isRequiredField: Boolean
    abstract val autoCompleteList: List<String>?
    abstract val autoCompleteItemSelected: ((String?) -> Unit)?

    @Deprecated("Use onImeActionClick instead")
    abstract val onNextClicked: (() -> Unit)?
    abstract val onValueChanged: ((TextFieldValue?) -> Unit)?
    abstract val onFocusChanged: ((Boolean) -> Unit)?
    abstract val imeAction: ImeAction
    abstract val onImeActionClick: ((ImeAction) -> Unit)?
}

/**
 * UiModel used for Input User
 * @param title : Label of the component.
 * @param state: [InputShellState]
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param legendData: [LegendData]
 * @param inputTextFieldValue: Input of the component in the format of ddMMyyyy/HHMM/ddMMyyyyHHMM.
 * @param isRequiredField: Mark this input as required.
 * @param autoCompleteList: List of strings to be used for autocomplete dropdown.
 * @param autoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param onNextClicked: gives access to the on next callback.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param imeAction: controls the imeAction button to be shown.
 * @param onFocusChanged: gives access to the on Focus changed callback.
 *
 */
data class InputUserModel(
    override val title: String,
    override val state: InputShellState,
    override val inputStyle: InputStyle = InputStyle.DataInputStyle(),
    override val supportingText: List<SupportingTextData>? = null,
    override val legendData: LegendData? = null,
    override val inputTextFieldValue: TextFieldValue? = null,
    override val isRequiredField: Boolean = false,
    override val autoCompleteList: List<String>? = null,
    override val autoCompleteItemSelected: ((String?) -> Unit)? = null,
    @Deprecated("Use onImeActionClick instead")
    override val onNextClicked: (() -> Unit)? = null,
    override val onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    override val onFocusChanged: ((Boolean) -> Unit)? = null,
    override val imeAction: ImeAction = ImeAction.Next,
    override val onImeActionClick: ((ImeAction) -> Unit)? = { imeAction ->
        if (imeAction == ImeAction.Next) {
            onNextClicked?.invoke()
        }
    },
) : BasicTextInputModel() {
    companion object {
        const val MAIN = "INPUT_USER"
        const val INPUT_TYPE = "USER"
        const val RESET_BUTTON = "INPUT_USER_RESET_BUTTON"
        const val LEGEND = "INPUT_USER_LEGEND"
        const val SUPPORTING_TEXT = "INPUT_USER_SUPPORTING_TEXT"
        const val TEXT_FIELD = "INPUT_USER_FIELD"
    }
}

/**
 * UiModel used for Input Password
 * @param title: Label of the component.
 * @param state: [InputShellState]
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param legendData: [LegendData]
 * @param inputTextFieldValue: Input of the component in the format of ddMMyyyy/HHMM/ddMMyyyyHHMM.
 * @param isRequiredField: Mark this input as required.
 * @param autoCompleteList: List of strings to be used for autocomplete dropdown.
 * @param autoCompleteItemSelected: gives access to the autocomplete item selection.
 * @param onNextClicked: gives access to the on next callback.
 * @param onValueChanged: gives access to the onValueChanged event.
 * @param imeAction: controls the imeAction button to be shown.
 * @param onFocusChanged: gives access to the on Focus changed callback.
 *
 */
data class InputPasswordModel(
    override val title: String,
    override val state: InputShellState,
    override val inputStyle: InputStyle = InputStyle.DataInputStyle(),
    override val supportingText: List<SupportingTextData>? = null,
    override val legendData: LegendData? = null,
    override val inputTextFieldValue: TextFieldValue? = null,
    override val isRequiredField: Boolean = false,
    override val autoCompleteList: List<String>? = null,
    override val autoCompleteItemSelected: ((String?) -> Unit)? = null,
    @Deprecated("Use onImeActionClick instead")
    override val onNextClicked: (() -> Unit)? = null,
    override val onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    override val onFocusChanged: ((Boolean) -> Unit)? = null,
    override val imeAction: ImeAction = ImeAction.Next,
    override val onImeActionClick: ((ImeAction) -> Unit)? = { imeAction ->
        if (imeAction == ImeAction.Next) {
            onNextClicked?.invoke()
        }
    },
) : BasicTextInputModel() {
    companion object {
        const val MAIN = "INPUT_PASSWORD"
        const val INPUT_TYPE = "PASSWORD"
        const val RESET_BUTTON = "INPUT_PASSWORD_RESET_BUTTON"
        const val LEGEND = "INPUT_PASSWORD_LEGEND"
        const val SUPPORTING_TEXT = "INPUT_PASSWORD_SUPPORTING_TEXT"
        const val TEXT_FIELD = "INPUT_PASSWORD_TEXT_FIELD"
        const val ACTION_BUTTON = "INPUT_PASSWORD_ACTION_BUTTON"
    }
}
