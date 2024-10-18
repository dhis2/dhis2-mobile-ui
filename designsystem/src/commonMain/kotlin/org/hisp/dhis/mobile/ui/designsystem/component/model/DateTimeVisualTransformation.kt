package org.hisp.dhis.mobile.ui.designsystem.component.model

import androidx.compose.ui.text.input.VisualTransformation

interface DateTimeVisualTransformation : VisualTransformation {
    val maskLength: Int
}
