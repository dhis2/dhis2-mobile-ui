package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true,
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(
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
