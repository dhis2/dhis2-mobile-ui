package org.hisp.dhis.common.previews

import androidx.compose.runtime.Composable

interface ButtonsPreview {
    @Composable
    fun Dhis2TextButtonPreview() {
        org.hisp.dhis.common.designsystem.component.Dhis2TextButtonPreview()
    }

    @Composable
    fun Dhis2ButtonPreview() {
        org.hisp.dhis.common.designsystem.component.Dhis2ButtonPreview()
    }

    @Composable
    fun SquareIconButtonPreview() {
        org.hisp.dhis.common.designsystem.component.SquareIconButtonPreview()
    }
}
