package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

@Composable
fun Dhis2RadioButton(
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier
                .width(Spacing.Spacing40)
                .height(Spacing.Spacing40)
                .padding(start = Spacing.Spacing8, top = Spacing.Spacing8, end = Spacing.Spacing8, bottom = Spacing.Spacing8),
            colors = RadioButtonDefaults.colors(
                selectedColor = SurfaceColor.Primary,
                unselectedColor = Outline.Dark,
                disabledSelectedColor = TextColor.OnDisabledSurface,
                disabledUnselectedColor = TextColor.OnDisabledSurface
            )
        )
}

@Composable
fun Dhis2RadioButtonInput(
    selected: Boolean,
    textInput: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Dhis2RadioButton(
            selected,
            enabled,
            onClick
        )
        //TODO: styles to InputText
        Text(text = textInput)

    }
}