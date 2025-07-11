package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputPasswordModel
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input Password.
 * @param uiModel: an [InputPasswordModel] with all the parameters for the input.
 * @param modifier: optional modifier.
 */
@Composable
fun InputPassword(
    uiModel: InputPasswordModel,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val supportingTextList = mutableListOf<SupportingTextData>()

    uiModel.supportingText?.forEach { item ->
        supportingTextList.add(item)
    }
    val showPassword = mutableStateOf(false)
    val focusManager = LocalFocusManager.current

    InputShell(
        modifier =
            modifier
                .testTag(InputPasswordModel.MAIN)
                .focusRequester(focusRequester),
        title = uiModel.title,
        state = uiModel.state,
        isRequiredField = uiModel.isRequiredField,
        onFocusChanged = uiModel.onFocusChanged,
        inputField = {
            BasicTextField(
                modifier =
                    Modifier
                        .testTag(InputPasswordModel.TEXT_FIELD)
                        .fillMaxWidth(),
                inputTextValue =
                    TextFieldValue(
                        uiModel.inputTextFieldValue?.text ?: "",
                        TextRange(
                            uiModel.inputTextFieldValue?.text?.length ?: 0,
                        ),
                    ),
                isSingleLine = true,
                onInputChanged = { newText ->

                    uiModel.onValueChanged?.invoke(newText)
                },
                enabled = uiModel.state != InputShellState.DISABLED,
                state = uiModel.state,
                keyboardOptions = KeyboardOptions(imeAction = uiModel.imeAction, keyboardType = KeyboardType.Text),
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                onNextClicked = {
                    if (uiModel.onNextClicked != null) {
                        uiModel.onNextClicked.invoke()
                    } else {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                },
            )
        },
        primaryButton = {
            if (!uiModel.inputTextFieldValue?.text.isNullOrBlank() && uiModel.state != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag(InputPasswordModel.RESET_BUTTON).padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        uiModel.onValueChanged?.invoke(TextFieldValue())
                        focusRequester.requestFocus()
                    },
                )
            }
        },
        secondaryButton = {
            SquareIconButton(
                modifier =
                    Modifier
                        .testTag(InputPasswordModel.ACTION_BUTTON)
                        .focusable(),
                icon = {
                    Icon(
                        imageVector = if (showPassword.value) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = null,
                    )
                },
                onClick = {
                    showPassword.value = !showPassword.value
                },
                enabled = uiModel.state != InputShellState.DISABLED,
            )
        },
        supportingText =
            {
                supportingTextList.forEach { item ->
                    SupportingText(
                        item.text,
                        item.state,
                        modifier = Modifier.testTag(InputPasswordModel.SUPPORTING_TEXT),
                    )
                }
            },
        legend = {
            uiModel.legendData?.let {
                Legend(uiModel.legendData, Modifier.testTag(InputPasswordModel.LEGEND))
            }
        },
        inputStyle = uiModel.inputStyle,
    )
}
