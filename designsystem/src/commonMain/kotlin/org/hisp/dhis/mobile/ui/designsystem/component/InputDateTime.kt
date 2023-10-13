package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTimeVisualTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

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
    value: String?,
    actionIconType: DateTimeActionIconType = DateTimeActionIconType.DATE_TIME,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    state: InputShellState = InputShellState.UNFOCUSED,
    legendData: LegendData? = null,
    supportingText: List<SupportingTextData>? = null,
    isRequired: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: DateTimeVisualTransformation = DateTransformation(),
    onFocusChanged: ((Boolean) -> Unit) = {},
    onValueChanged: (String) -> Unit,
) {
    val allowedCharacters = RegExValidations.DATE_TIME.regex

    InputShell(
        modifier = modifier.testTag("INPUT_DATE_TIME"),
        title = title,
        state = state,
        isRequiredField = isRequired,
        onFocusChanged = onFocusChanged,
        inputField = {
            BasicTextField(
                modifier = Modifier
                    .testTag("INPUT_DATE_TIME_TEXT_FIELD")
                    .fillMaxWidth(),
                inputText = value.orEmpty(),
                isSingleLine = true,
                onInputChanged = { newText ->
                    if (newText.length > visualTransformation.maskLength) {
                        return@BasicTextField
                    }

                    if (allowedCharacters.containsMatchIn(newText) || newText.isBlank()) {
                        onValueChanged.invoke(newText)
                    }
                },
                enabled = state != InputShellState.DISABLED,
                state = state,
                keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Number),
                visualTransformation = visualTransformation,
            )
        },
        primaryButton = {
            if (!value.isNullOrBlank() && state != InputShellState.DISABLED) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_DATE_TIME_RESET_BUTTON").padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        onValueChanged.invoke("")
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
                onClick = onActionClicked,
                enabled = state != InputShellState.DISABLED,
            )
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                )
            }
        },
        legend = {
            legendData?.let {
                Legend(legendData, Modifier.testTag("INPUT_DATE_TIME_LEGEND"))
            }
        },
    )
}

enum class DateTimeActionIconType {
    DATE, TIME, DATE_TIME
}
