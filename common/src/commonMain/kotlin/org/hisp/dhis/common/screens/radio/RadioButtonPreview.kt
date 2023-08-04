package org.hisp.dhis.common.screens.radio

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.RadioButton

@Composable
internal fun RadioButtonPreview(enabled: Boolean = true) {
    RadioButton(
        selected = true,
        enabled = enabled,
        textInput = null
    ) { }
}

@Composable
internal fun RadioButtonDisabledPreview(enabled: Boolean = false) {
    RadioButton(
        selected = true,
        enabled = enabled,
        textInput = null
    ) { }
}

@Composable
internal fun TextRadioButtonPreview(enabled: Boolean = true, text: String = "Option") {
    RadioButton(
        selected = true,
        enabled = enabled,
        textInput = text
    ) { }
}