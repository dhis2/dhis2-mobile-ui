package org.hisp.dhis.android.previews

import androidx.compose.runtime.Composable
import com.airbnb.android.showkase.annotation.ShowkaseComposable
import org.hisp.dhis.common.components.Dhis2ButtonPreview
import org.hisp.dhis.common.components.Dhis2TextButtonPreview

@ShowkaseComposable(name = "Dhis2TextButton", group = "Buttons")
@Composable
fun TextButtonPreview() {
    Dhis2TextButtonPreview()
}

@ShowkaseComposable(name = "Simple Button", group = "Buttons")
@Composable
fun ButtonPReview() {
    Dhis2ButtonPreview()
}
