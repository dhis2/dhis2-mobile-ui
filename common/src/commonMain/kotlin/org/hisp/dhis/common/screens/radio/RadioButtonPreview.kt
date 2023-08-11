package org.hisp.dhis.common.screens.radio

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.RadioButton

@Composable
internal fun RadioButtonPreview(selected: Boolean = true, enabled: Boolean = true) {
    RadioButton(
        selected = selected,
        enabled = enabled,
        textInput = null
    ) { }
}

@Composable
internal fun TextRadioButtonPreview(selected: Boolean = false, enabled: Boolean = true, text: String = "Option", changeOption: () -> Unit) {
    RadioButton(
        selected = selected,
        onClick = changeOption,
        enabled = enabled,
        textInput = text
    )
}
