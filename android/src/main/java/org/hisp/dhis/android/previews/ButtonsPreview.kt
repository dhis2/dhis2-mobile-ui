package org.hisp.dhis.android.previews

import androidx.compose.runtime.Composable
import com.airbnb.android.showkase.annotation.ShowkaseComposable
import org.hisp.dhis.common.previews.ButtonsPreview

class ButtonsPreview : ButtonsPreview {

    @ShowkaseComposable(name = "Dhis2TextButton", group = "Buttons")
    @Composable
    override fun Dhis2TextButtonPreview() {
        super.Dhis2TextButtonPreview()
    }

    @ShowkaseComposable(name = "Simple Button", group = "Buttons")
    @Composable
    override fun Dhis2ButtonPreview() {
        super.Dhis2ButtonPreview()
    }

    @ShowkaseComposable(
        group = "Buttons",
        name = "SquareIconButton"
    )
    @Composable
    override fun SquareIconButtonPreview() {
        super.SquareIconButtonPreview()
    }
}
