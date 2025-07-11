package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.hisp.dhis.mobile.ui.designsystem.component.internal.timePickerColors
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import androidx.compose.material3.TimePicker as Material3TimePicker

/**
 * DHIS2 TimePicker component
 * a picker to enter time values
 * component wraps Material 3 [TimePicker]
 * @param state a [TimePickerState] to manage the value changes.
 * @param cancelText text to be used for the cancel button.
 * @param acceptText text to be used for the confirm button.
 * @param onDismissRequest callback for when user taps outside the dialog.
 * @param onAccept callBack for the confirm button.
 * @param onCancel callback for the cancel button.
 * @param title text to be used for the picker title.
 * @param modifier: optional modifier.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    state: TimePickerState,
    cancelText: String?,
    acceptText: String?,
    onDismissRequest: () -> Unit,
    title: String,
    onAccept: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties =
            DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true,
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                modifier
                    .background(
                        color = SurfaceColor.Container,
                        shape = RoundedCornerShape(Radius.L),
                    ).testTag("TIME_PICKER")
                    .padding(vertical = Spacing.Spacing16, horizontal = Spacing.Spacing24),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = Spacing.Spacing16).align(Alignment.Start),
            )
            Material3TimePicker(
                state = state,
                layoutType = TimePickerLayoutType.Vertical,
                colors = timePickerColors(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Row(Modifier.align(Alignment.End)) {
                Button(
                    enabled = true,
                    ButtonStyle.TEXT,
                    ColorStyle.DEFAULT,
                    cancelText ?: provideStringResource("cancel"),
                ) {
                    onCancel()
                }
                Button(
                    enabled = true,
                    ButtonStyle.TEXT,
                    ColorStyle.DEFAULT,
                    acceptText ?: provideStringResource("ok"),
                ) {
                    onAccept()
                }
            }
        }
    }
}
