package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputUserModel

/**
 * DHIS2 Input User. Wraps DHIS · [BasicTextInput].
 * @param uiModel: [InputUserModel] with all parameters for component
 * @param modifier: optional modifier.
 */
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
        keyboardOptions =
            KeyboardOptions(
                imeAction = uiModel.imeAction,
                keyboardType = KeyboardType.Text,
            ),
        modifier = modifier,
        testTag = InputUserModel.INPUT_TYPE,
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
                onClick = {
                    // no-op
                },
            )
        },
        autoCompleteList = uiModel.autoCompleteList,
        autoCompleteItemSelected = uiModel.autoCompleteItemSelected,
    )
}
