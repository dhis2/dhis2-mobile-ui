package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddLocationAlt
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.EditLocationAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 Input coordinate. Wraps DHIS · [InputShell].
 * @param title: controls the text to be shown for the title.
 * @param state: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param coordinates: controls the latitude and longitude of the location.
 * @param latitudeText: controls the text to be shown for the latitude label.
 * @param longitudeText: controls the text to be shown for the longitude label.
 * @param addLocationBtnText: controls the text to be shown for the add polygon button.
 * @param isRequired: controls whether the field is mandatory or not.
 * @param modifier: allows a modifier to be passed externally.
 * @param onResetButtonClicked: callback to when reset button is clicked.
 * @param onUpdateButtonClicked: callback to when add button or edit icon is clicked.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputCoordinate(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    coordinates: Coordinates? = null,
    latitudeText: String = provideStringResource("latitude"),
    longitudeText: String = provideStringResource("longitude"),
    addLocationBtnText: String = provideStringResource("add_location"),
    isRequired: Boolean = false,
    modifier: Modifier = Modifier,
    onResetButtonClicked: () -> Unit,
    onUpdateButtonClicked: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    InputShell(
        modifier =
            modifier
                .focusRequester(focusRequester)
                .testTag("INPUT_COORDINATE"),
        title = title,
        state = state,
        isRequiredField = isRequired,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_COORDINATE_LEGEND"))
            }
        },
        supportingText = supportingText,
        supportingTextTestTag = "INPUT_COORDINATE_SUPPORTING_TEXT",
        inputField = {
            if (coordinates != null) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
                    modifier = Modifier.padding(end = Spacing.Spacing16).focusable(),
                ) {
                    CoordinateText(
                        latitudeText,
                        coordinates.latitude.toString(),
                        state == InputShellState.DISABLED,
                    )
                    CoordinateText(
                        longitudeText,
                        coordinates.longitude.toString(),
                        state == InputShellState.DISABLED,
                    )
                }
            } else {
                Button(
                    enabled = state != InputShellState.DISABLED,
                    ButtonStyle.KEYBOARDKEY,
                    ColorStyle.DEFAULT,
                    addLocationBtnText,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AddLocationAlt,
                            contentDescription = "Add Location Button",
                            tint = if (state != InputShellState.DISABLED) SurfaceColor.Primary else TextColor.OnDisabledSurface,
                        )
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            end = Spacing.Spacing12,
                            top = Spacing.Spacing8,
                            bottom = Spacing.Spacing8,
                        ).focusable()
                        .testTag("INPUT_COORDINATE_ADD_BUTTON"),
                ) {
                    focusRequester.requestFocus()
                    onUpdateButtonClicked.invoke()
                }
            }
        },
        primaryButton =
            if (coordinates != null && state != InputShellState.DISABLED) {
                {
                    IconButton(
                        modifier = Modifier.testTag("INPUT_COORDINATE_RESET_BUTTON"),
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = "Reset Button",
                            )
                        },
                        onClick = {
                            focusRequester.requestFocus()
                            onResetButtonClicked.invoke()
                        },
                    )
                }
            } else {
                null
            },
        secondaryButton =
            if (coordinates != null && state != InputShellState.DISABLED) {
                {
                    SquareIconButton(
                        modifier = Modifier.testTag("INPUT_COORDINATE_EDIT_BUTTON"),
                        enabled = true,
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.EditLocationAlt,
                                contentDescription = "edit_location",
                            )
                        },
                        onClick = {
                            focusRequester.requestFocus()
                            onUpdateButtonClicked.invoke()
                        },
                    )
                }
            } else {
                null
            },
        inputStyle = inputStyle,
    )
}

@Composable
fun CoordinateText(
    text: String,
    value: String,
    isDisabled: Boolean,
) {
    Text(
        style =
            MaterialTheme.typography.bodyLarge.copy(
                color =
                    if (!isDisabled) {
                        TextColor.OnSurface
                    } else {
                        TextColor.OnDisabledSurface
                    },
            ),
        text =
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = TextColor.OnDisabledSurface)) {
                    append("$text: ")
                }
                append(value)
            },
    )
}

data class Coordinates(
    val latitude: Double,
    val longitude: Double,
)
