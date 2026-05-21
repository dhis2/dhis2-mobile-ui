package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.hisp.dhis.mobile.designsystem.generated.resources.Res
import org.hisp.dhis.mobile.designsystem.generated.resources.cancel
import org.hisp.dhis.mobile.designsystem.generated.resources.ok
import org.hisp.dhis.mobile.ui.designsystem.component.internal.CalendarDayView
import org.hisp.dhis.mobile.ui.designsystem.component.internal.CalendarMonthView
import org.hisp.dhis.mobile.ui.designsystem.component.internal.CalendarPickerHeader
import org.hisp.dhis.mobile.ui.designsystem.component.internal.CalendarYearView
import org.hisp.dhis.mobile.ui.designsystem.component.state.CalendarPickerState
import org.hisp.dhis.mobile.ui.designsystem.component.state.CalendarPickerViewMode
import org.hisp.dhis.mobile.ui.designsystem.theme.Outline
import org.hisp.dhis.mobile.ui.designsystem.theme.Radius
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.jetbrains.compose.resources.stringResource

/**
 * DHIS2 CalendarPickerModal
 *
 * A modal date picker following the Material 3 date picker layout, designed to support
 * configurable calendar systems (Nepali / Bikram Sambat, Ethiopian / Ge'ez, Gregorian, …).
 *
 * The calendar system controls the number of months per year, day-of-week labels, year range,
 * and the conversion between its own representation and UTC epoch millis.
 *
 * @param state       holds display position, selection, and view-mode; create with
 *                    [rememberCalendarPickerState].
 * @param title       label shown above the selected-date headline in the picker header.
 * @param acceptText  label for the confirm button; defaults to "OK".
 * @param cancelText  label for the cancel button; defaults to "Cancel".
 * @param onConfirm   called with the selected date as UTC epoch millis (null if none selected).
 * @param onCancel    called when the user dismisses without confirming.
 * @param onDismissRequest called when the user taps outside the dialog.
 * @param modifier    optional modifier applied to the dialog container.
 */
@Composable
fun CalendarPickerModal(
    state: CalendarPickerState,
    title: String,
    acceptText: String? = null,
    cancelText: String? = null,
    onConfirm: (Long?) -> Unit,
    onCancel: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false,
        ),
    ) {
        Column(
            modifier = modifier
                .widthIn(max = 360.dp)
                .fillMaxWidth()
                .background(
                    color = SurfaceColor.Container,
                    shape = RoundedCornerShape(Radius.XXL),
                )
                .clip(RoundedCornerShape(Radius.XXL))
                .testTag("CALENDAR_PICKER_MODAL"),
        ) {
            CalendarPickerHeader(
                state = state,
                title = title,
                modifier = Modifier.fillMaxWidth(),
            )

            HorizontalDivider(color = Outline.Light)

            when (state.viewMode) {
                CalendarPickerViewMode.DAY ->
                    CalendarDayView(
                        state = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Spacing.Spacing12),
                    )

                CalendarPickerViewMode.YEAR ->
                    CalendarYearView(
                        state = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Spacing.Spacing12),
                    )

                CalendarPickerViewMode.MONTH ->
                    CalendarMonthView(
                        state = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Spacing.Spacing12),
                    )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        end = Spacing.Spacing12,
                        bottom = Spacing.Spacing8,
                    ),
            ) {
                Button(
                    enabled = true,
                    style = ButtonStyle.TEXT,
                    colorStyle = ColorStyle.DEFAULT,
                    text = cancelText ?: stringResource(Res.string.cancel),
                    onClick = onCancel,
                )
                Spacer(Modifier.width(Spacing.Spacing8))
                Button(
                    enabled = state.selectedDate != null,
                    style = ButtonStyle.TEXT,
                    colorStyle = ColorStyle.DEFAULT,
                    text = acceptText ?: stringResource(Res.string.ok),
                    onClick = { onConfirm(state.selectedEpochMillis) },
                )
            }
        }
    }
}