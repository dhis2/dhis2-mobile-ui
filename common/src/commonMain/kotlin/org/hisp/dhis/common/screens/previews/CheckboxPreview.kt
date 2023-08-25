package org.hisp.dhis.common.screens.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBox

@Composable
internal fun CheckboxPreview(checked: Boolean, enabled: Boolean = true) {
    CheckBox(
        checked = checked,
        enabled = enabled,
        textInput = null,
    )
}

@Composable
internal fun TextCheckboxPreview(checked: Boolean, enabled: Boolean = true, text: String = "Option") {
    CheckBox(
        checked = checked,
        enabled = enabled,
        textInput = text,
    )
}
