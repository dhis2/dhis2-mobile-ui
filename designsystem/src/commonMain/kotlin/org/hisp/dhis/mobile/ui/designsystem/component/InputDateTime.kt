package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTimeVisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * Input field to enter date, time or date&time. It will format content based on given visual
 * transformation
 *
 * @param title: Label of the component.
 * @param value: Input of the component in the format of DDMMYYYY/HHMM/DDMMYYYYHHMM
 * @param actionIconType: Type of action icon to display. [DateTimeActionIconType.DATE_TIME], [DateTimeActionIconType.DATE], [DateTimeActionIconType.TIME]
 * @param onActionClicked: Callback to handle the action when the calendar icon is clicked.
 * @param state: [InputShellState]
 * @param legendData: [LegendData]
 * @param supportingText: List of [SupportingTextData] that manages all the messages to be shown.
 * @param isRequired: Mark this input as marked
 * @param visualTransformation: Pass a visual transformation to format the date input visually. By default uses [DateTransformation]
 * @param onValueChanged: Callback to receive changes in the input in the format of DDMMYYYY/HHMM/DDMMYYYYHHMM
 */
@Composable
fun InputDateTime(
    title: String,
    inputTextFieldValue: TextFieldValue? = null,
    actionIconType: DateTimeActionIconType = DateTimeActionIconType.DATE_TIME,
    allowsManualInput: Boolean = true,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    legendData: LegendData? = null,
    supportingText: List<SupportingTextData>? = null,
    onNextClicked: (() -> Unit)? = null,
    isRequired: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: DateTimeVisualTransformation = DateTransformation(),
    onFocusChanged: ((Boolean) -> Unit) = {},
    onValueChanged: (TextFieldValue) -> Unit,
) {
    val allowedCharacters = RegExValidations.DATE_TIME.regex
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    InputShell(
        modifier = modifier.testTag("INPUT_DATE_TIME")
            .focusRequester(focusRequester),
        title = title,
        state = state,
        isRequiredField = isRequired,
        onFocusChanged = onFocusChanged,
        inputField = {
            if (allowsManualInput) {
                BasicTextField(
                    modifier = Modifier
                        .testTag("INPUT_DATE_TIME_TEXT_FIELD")
                        .fillMaxWidth(),
                    inputTextValue = inputTextFieldValue ?: TextFieldValue(),
                    isSingleLine = true,
                    onInputChanged = { newText ->
                        if (newText.text.length > visualTransformation.maskLength) {
                            return@BasicTextField
                        }

                        if (allowedCharacters.containsMatchIn(newText.text) || newText.text.isBlank()) {
                            onValueChanged.invoke(newText)
                        }
                    },
                    enabled = state != InputShellState.DISABLED,
                    state = state,
                    keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Number),
                    visualTransformation = visualTransformation,
                    onNextClicked = {
                        if (onNextClicked != null) {
                            onNextClicked.invoke()
                        } else {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    },
                )
            } else {
                Box {
                    Text(
                        modifier = Modifier
                            .testTag("INPUT_DATE_TIME_TEXT")
                            .fillMaxWidth(),
                        text = inputTextFieldValue?.text.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (state != InputShellState.DISABLED && !inputTextFieldValue?.text.isNullOrEmpty()) {
                                TextColor.OnSurface
                            } else {
                                TextColor.OnDisabledSurface
                            },
                        ),
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .alpha(0f)
                            .clickable(
                                enabled = state != InputShellState.DISABLED,
                                onClick = {
                                    focusRequester.requestFocus()
                                    onActionClicked()
                                },
                            ),
                    )
                }
            }
        },
        primaryButton = {
            if (!inputTextFieldValue?.text.isNullOrBlank() && state != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_DATE_TIME_RESET_BUTTON").padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        onValueChanged.invoke(TextFieldValue())
                        focusRequester.requestFocus()
                    },
                )
            }
        },
        secondaryButton = {
            val icon = when (actionIconType) {
                DateTimeActionIconType.DATE, DateTimeActionIconType.DATE_TIME -> Icons.Filled.Event
                DateTimeActionIconType.TIME -> Icons.Filled.Schedule
            }

            SquareIconButton(
                modifier = Modifier.testTag("INPUT_DATE_TIME_ACTION_BUTTON")
                    .focusable(),
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                },
                onClick = {
                    focusRequester.requestFocus()
                    onActionClicked()
                },
                enabled = state != InputShellState.DISABLED,
            )
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_DATE_TIME_SUPPORTING_TEXT"),
                )
            }
        },
        legend = {
            legendData?.let {
                Legend(legendData, Modifier.testTag("INPUT_DATE_TIME_LEGEND"))
            }
        },
        inputStyle = inputStyle,
    )
}

enum class DateTimeActionIconType {
    DATE, TIME, DATE_TIME
}
