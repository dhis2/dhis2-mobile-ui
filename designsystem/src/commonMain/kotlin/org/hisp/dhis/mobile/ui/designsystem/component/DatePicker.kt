package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.DialogProperties
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2LightColorScheme
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import androidx.compose.material3.DatePicker as Material3DatePicker

/**
 * DHIS2 DatePicker component
 * a picker to enter Date values
 * component wraps Material 3 [DatePicker]
 * @param title text to be used for the picker title.
 * @param state a [DatePickerState] to manage the value changes.
 * @param cancelText text to be used for the cancel button.
 * @param acceptText text to be used for the confirm button.
 * @param onDismissRequest callback for when user taps outside the dialog.
 * @param onConfirm callBack for the confirm button.
 * @param onCancel callback for the cancel button.
 * @param modifier: optional modifier.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    title: String,
    state: DatePickerState,
    acceptText: String?,
    cancelText: String?,
    onConfirm: (DatePickerState) -> Unit,
    onCancel: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MaterialTheme(
        colorScheme =
            DHIS2LightColorScheme.copy(
                outlineVariant = Outline.Medium,
            ),
    ) {
        DatePickerDialog(
            modifier = modifier.testTag("DATE_PICKER"),
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                Button(
                    enabled = true,
                    ButtonStyle.TEXT,
                    ColorStyle.DEFAULT,
                    acceptText ?: provideStringResource("ok"),
                ) {
                    onConfirm(state)
                }
            },
            colors = datePickerColors(),
            dismissButton = {
                Button(
                    enabled = true,
                    ButtonStyle.TEXT,
                    ColorStyle.DEFAULT,
                    cancelText ?: provideStringResource("cancel"),
                ) {
                    onCancel()
                }
            },
            properties =
                DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = true,
                ),
        ) {
            Material3DatePicker(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier =
                            Modifier.padding(
                                start = Spacing.Spacing24,
                                top = Spacing.Spacing24,
                            ),
                    )
                },
                state = state,
                showModeToggle = true,
                modifier = Modifier.padding(Spacing.Spacing0),
            )
        }
    }
}
