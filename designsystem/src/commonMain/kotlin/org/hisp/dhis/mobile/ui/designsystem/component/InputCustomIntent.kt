package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import org.hisp.dhis.mobile.ui.designsystem.component.CustomIntentState.LAUNCH
import org.hisp.dhis.mobile.ui.designsystem.component.CustomIntentState.LOADED
import org.hisp.dhis.mobile.ui.designsystem.component.CustomIntentState.LOADING
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

const val INPUT_CUSTOM_INTENT_TEST_TAG = "INPUT_CUSTOM_INTENT_"
const val VALUE_CHIP_TEST_TAG = "VALUE_CHIP_"
const val INPUT_CUSTOM_INTENT_CLEAR_BUTTON_TEST_TAG = "CLEAR_BUTTON"
const val LAUNCH_BUTTON_TEST_TAG = "LAUNCH_BUTTON"
const val LAUNCH_ICON_BUTTON_TEST_TAG = "LAUNCH_ICON_BUTTON"
const val CIRCULAR_PROGRESS_INDICATOR_TEST_TAG = "PROGRESS_INDICATOR"
const val INPUT_CUSTOM_INTENT_SUPPORTING_TEXT_TEST_TAG = "SUPPORTING_TEXT"
const val VALUE_TEST_TAG = "VALUE"
const val LEGEND_TEST_TAG = "LEGEND"

/**
 * DHIS2 Input Custom Intent. Wraps DHIS · [InputShell].
 * @param title: controls the text to be shown for the title.
 * @param buttonText: controls the text to be shown for the button.
 * @param values: Is a list of strings to be shown for the values.
 * @param onLaunch: callback for user to launch the intent.
 * @param onClear: callback for user to delete the values.
 * @param customIntentState: the state of the component, can be
 * [LOADING], [LOADED] or [LAUNCH].
 * @param inputShellState: Manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param supportingText: is a list of SupportingTextData that
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param isRequired: whether the field is required or not.
 * @param modifier: allows a modifier to be passed externally.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputCustomIntent(
    title: String,
    buttonText: String,
    values: List<String>,
    onLaunch: () -> Unit,
    onClear: () -> Unit = {},
    customIntentState: CustomIntentState = LAUNCH,
    inputShellState: InputShellState = InputShellState.UNFOCUSED,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    isRequired: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    val primaryButton: @Composable (() -> Unit)? =
        if (customIntentState == LOADED && inputShellState != InputShellState.DISABLED) {
            {
                IconButton(
                    modifier = Modifier.testTag(INPUT_CUSTOM_INTENT_TEST_TAG + INPUT_CUSTOM_INTENT_CLEAR_BUTTON_TEST_TAG),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Cancel icon Button",
                        )
                    },
                    onClick = {
                        onClear.invoke()
                    },
                )
            }
        } else {
            null
        }

    val secondaryButton: @Composable (() -> Unit)? =
        if (customIntentState == LOADED && inputShellState != InputShellState.DISABLED) {
            {
                SquareIconButton(
                    modifier = Modifier.testTag(INPUT_CUSTOM_INTENT_TEST_TAG + LAUNCH_ICON_BUTTON_TEST_TAG),
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                            contentDescription = "Launch Icon Button",
                        )
                    },
                ) {
                    focusRequester.requestFocus()
                    onLaunch.invoke()
                }
            }
        } else {
            null
        }

    InputShell(
        title,
        state = inputShellState,
        legend = {
            legendData?.let {
                Legend(legendData, modifier.testTag(INPUT_CUSTOM_INTENT_TEST_TAG + LEGEND_TEST_TAG))
            }
        },
        supportingText = {
            supportingText?.forEach { label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = modifier.testTag(INPUT_CUSTOM_INTENT_TEST_TAG + INPUT_CUSTOM_INTENT_SUPPORTING_TEXT_TEST_TAG),
                )
            }
        },
        inputField = {
            when (customIntentState) {
                LAUNCH -> {
                    ButtonBlock(
                        modifier = Modifier.padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8),
                        primaryButton = {
                            Button(
                                enabled = inputShellState != InputShellState.DISABLED,
                                modifier =
                                    Modifier
                                        .testTag(INPUT_CUSTOM_INTENT_TEST_TAG + LAUNCH_BUTTON_TEST_TAG)
                                        .padding(end = Spacing.Spacing16)
                                        .fillMaxWidth(),
                                style = ButtonStyle.KEYBOARDKEY,
                                text = buttonText,
                                icon = {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                                        contentDescription = "launch intent icon Button",
                                    )
                                },
                            ) {
                                focusRequester.requestFocus()
                                onLaunch.invoke()
                            }
                        },
                    )
                }
                LOADING -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            Modifier
                                .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)
                                .size(Spacing.Spacing48),
                        ) {
                            ProgressIndicator(
                                modifier = Modifier.testTag(INPUT_CUSTOM_INTENT_TEST_TAG + CIRCULAR_PROGRESS_INDICATOR_TEST_TAG),
                                type = ProgressIndicatorType.CIRCULAR,
                            )
                        }
                    }
                }
                LOADED -> {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                    ) {
                        when {
                            values.size == 1 -> {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = values[0],
                                        color =
                                            if (inputShellState !=
                                                InputShellState.DISABLED
                                            ) {
                                                TextColor.OnSurface
                                            } else {
                                                TextColor.OnDisabledSurface
                                            },
                                        maxLines = 1,
                                        modifier = Modifier.testTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_TEST_TAG),
                                    )
                                }
                            }
                            values.size > 1 -> {
                                values.forEachIndexed { index, value ->
                                    InputChip(
                                        modifier = Modifier.testTag(INPUT_CUSTOM_INTENT_TEST_TAG + VALUE_CHIP_TEST_TAG + "$index"),
                                        label = value,
                                        hasTransparentBackground = true,
                                        enabled = inputShellState != InputShellState.DISABLED,
                                    )
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        },
        primaryButton = primaryButton,
        secondaryButton = secondaryButton,
        isRequiredField = isRequired,
        modifier = modifier.focusRequester(focusRequester),
        inputStyle = inputStyle,
    )
}

enum class CustomIntentState {
    LAUNCH,
    LOADING,
    LOADED,
}
