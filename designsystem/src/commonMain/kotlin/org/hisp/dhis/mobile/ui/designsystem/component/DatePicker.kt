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

) {
    MaterialTheme(
        colorScheme = DHIS2LightColorScheme.copy(
            outlineVariant = Outline.Medium,
        ),
    ) {
        DatePickerDialog(
            modifier = Modifier.testTag("DATE_PICKER"),
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
            properties = DialogProperties(
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
                        modifier = Modifier.padding(
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
