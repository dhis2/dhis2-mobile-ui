package org.hisp.dhis.common.screens.previews

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButton
import org.hisp.dhis.mobile.ui.designsystem.component.RadioButtonData

@Composable
internal fun RadioButtonPreview(selected: Boolean = true, enabled: Boolean = true) {
    RadioButton(
        RadioButtonData("", selected, enabled, textInput = null)
    ) { }
}

@Composable
internal fun TextRadioButtonPreview(selected: Boolean = false, enabled: Boolean = true, text: String = "Option", changeOption: (Boolean) -> Unit) {
    RadioButton(
        RadioButtonData("", selected, enabled, text),
        onClick = changeOption
    )
}
