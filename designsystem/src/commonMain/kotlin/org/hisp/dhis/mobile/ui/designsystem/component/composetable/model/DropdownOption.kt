package org.hisp.dhis.mobile.ui.designsystem.component.composetable.model

import kotlinx.serialization.Serializable

//todo: maybe we could rethink this class in order to avoid using it in so many different places in the app, it could be a generic T variable
@Serializable
data class DropdownOption(
    val code: String,
    val name: String,
)
