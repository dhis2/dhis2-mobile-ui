package org.hisp.dhis.showcaseapp.screens.previews

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBox
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData

@Composable
internal fun CheckboxPreview(
    checked: Boolean,
    enabled: Boolean = true,
    changeOption: (Boolean) -> Unit,
) {
    CheckBox(
        CheckBoxData("", checked, enabled, null),
    ) {
        changeOption(it)
    }
}

@Composable
internal fun TextCheckboxPreview(
    checked: Boolean,
    enabled: Boolean = true,
    text: String = "Option",
    changeOption: (Boolean) -> Unit,
) {
    CheckBox(
        CheckBoxData("", checked, enabled, text),
    ) {
        changeOption(it)
    }
}
