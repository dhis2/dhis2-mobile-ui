package org.hisp.dhis.common.screens.previews

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData

@Composable
internal fun CheckboxPreview(checked: Boolean, enabled: Boolean = true) {
    CheckBox(
        CheckBoxData("", checked, enabled, null,)
    )
}

@Composable
internal fun TextCheckboxPreview(checked: Boolean, enabled: Boolean = true, text: String = "Option") {
    CheckBox(
        CheckBoxData("", checked, enabled, text,)
    )
}
