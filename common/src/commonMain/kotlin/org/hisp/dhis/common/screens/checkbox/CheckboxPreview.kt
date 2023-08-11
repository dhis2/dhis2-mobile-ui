package org.hisp.dhis.common.screens.checkbox

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.CheckBox

@Composable
internal fun CheckboxPreview(checked: Boolean = true, enabled: Boolean = true) {
    CheckBox(
        checked = checked,
        enabled = enabled,
        textInput = null
    ) {  }
}

@Composable
internal fun TextCheckboxPreview(checked: Boolean = false, enabled: Boolean = true, text: String = "Option", changeOption: (Boolean) -> Unit) {
    CheckBox(
        checked = checked,
        enabled = enabled,
        textInput = text,
        onClick = changeOption
    )
}