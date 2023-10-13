package org.hisp.dhis.mobile.ui.designsystem.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

/**
 * DHIS2 Input polygon. Wraps DHIS · [InputShell].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param polygonText controls the text to be shown for the polygon label
 * @param addPolygonBtnText controls the text to be shown for the add polygon button
 * @param polygonAdded controls whether the polygon is added or not.
 * @param isRequired controls whether the field is mandatory or not
 * @param modifier allows a modifier to be passed externally
 * @param onResetButtonClicked callback to when reset button is clicked
 * @param onUpdateButtonClicked callback to when add button or edit icon is clicked
 */
@Composable
fun InputPolygon(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    polygonText: String? = provideStringResource("polygon_captured"),
    addPolygonBtnText: String = provideStringResource("add_polygon"),
    polygonAdded: Boolean = false,
    isRequired: Boolean = false,
    modifier: Modifier = Modifier,
    onResetButtonClicked: () -> Unit,
    onUpdateButtonClicked: () -> Unit,
) {
    InputShell(
        modifier = modifier.testTag("INPUT_POLYGON"),
        title = title,
        state = state,
        isRequiredField = isRequired,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag("INPUT_POLYGON_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag("INPUT_POLYGON_SUPPORTING_TEXT"),
                )
            }
        },
        inputField = {
            if (polygonAdded) {
                Text(
                    text = polygonText!!,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (state != InputShellState.DISABLED) {
                            TextColor.OnSurface
                        } else {
                            TextColor.OnDisabledSurface
                        },
                    ),
                )
            } else {
                Button(
                    enabled = state != InputShellState.DISABLED,
                    ButtonStyle.KEYBOARDKEY,
                    addPolygonBtnText,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AddLocationAlt,
                            contentDescription = "Add Polygon Button",
                        )
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(end = Spacing.Spacing12)
                        .testTag("INPUT_POLYGON_ADD_BUTTON"),
                ) {
                    onUpdateButtonClicked.invoke()
                }
            }
        },
        primaryButton = if (polygonAdded && state != InputShellState.DISABLED) {
            {
                IconButton(
                    modifier = Modifier.testTag("INPUT_POLYGON_RESET_BUTTON"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Reset Button",
                        )
                    },
                    onClick = onResetButtonClicked,
                )
            }
        } else {
            null
        },
        secondaryButton = if (polygonAdded && state != InputShellState.DISABLED) {
            {
                SquareIconButton(
                    modifier = Modifier.testTag("INPUT_POLYGON_EDIT_BUTTON"),
                    enabled = true,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.EditLocationAlt,
                            contentDescription = "edit_polygon",
                        )
                    },
                    onClick = {},
                )
            }
        } else {
            null
        },
    )
}