package org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions

import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TextInputModel
// todo review whether this interface is still needed
interface TextInputInteractions {
    fun onTextChanged(textInputModel: TextInputModel) = run {}
    fun onSave() = run {}
    fun onNextSelected() = run {}
}
