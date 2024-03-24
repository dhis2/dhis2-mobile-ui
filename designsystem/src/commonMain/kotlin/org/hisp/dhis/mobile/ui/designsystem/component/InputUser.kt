package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun InputUser(
    uiModel: InputUserModel,
    modifier: Modifier = Modifier,
) {
    BasicTextInput(
        title = uiModel.title,
        state = uiModel.state,
        inputStyle = uiModel.inputStyle,
        supportingText = uiModel.supportingText,
        legendData = uiModel.legendData,
        inputTextFieldValue = uiModel.inputTextFieldValue,
        isRequiredField = uiModel.isRequiredField,
        onNextClicked = uiModel.onNextClicked,
        onValueChanged = uiModel.onValueChanged,
        keyboardOptions = KeyboardOptions(
            imeAction = uiModel.imeAction,
            keyboardType = KeyboardType.Text,
        ),
        modifier = modifier,
        testTag = "USER",
        onFocusChanged = uiModel.onFocusChanged,
        actionButton = {
            SquareIconButton(
                modifier = Modifier.testTag("USER_BUTTON"),
                enabled = false,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "user_icon",
                    )
                },
                onClick = uiModel.onActionCLicked,
            )
        },
        autoCompleteList = uiModel.autoCompleteList,
        autoCompleteItemSelected = uiModel.autoCompleteItemSelected,
    )
}

data class InputUserModel(
    val title: String,
    val state: InputShellState,
    val inputStyle: InputStyle = InputStyle.DataInputStyle(),
    val supportingText: List<SupportingTextData>? = null,
    val legendData: LegendData? = null,
    val inputTextFieldValue: TextFieldValue? = null,
    val isRequiredField: Boolean = false,
    val autoCompleteList: List<String>? = null,
    val autoCompleteItemSelected: ((String?) -> Unit)? = null,
    val onNextClicked: (() -> Unit)? = null,
    val onValueChanged: ((TextFieldValue?) -> Unit)? = null,
    val onFocusChanged: ((Boolean) -> Unit)? = null,
    val imeAction: ImeAction = ImeAction.Next,
    val onActionCLicked: () -> Unit,
)
