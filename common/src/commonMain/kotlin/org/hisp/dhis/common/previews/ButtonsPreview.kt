package org.hisp.dhis.common.previews

import androidx.compose.runtime.Composable
import org.hisp.dhis.common.components.Dhis2ButtonPreview
import org.hisp.dhis.common.components.Dhis2TextButtonPreview

interface ButtonsPreview {
    @Composable
    fun TextButtonPreview() {
        Dhis2TextButtonPreview()
    }

    @Composable
    fun ButtonPReview() {
        Dhis2ButtonPreview()
    }
}
