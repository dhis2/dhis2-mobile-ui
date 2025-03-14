package org.hisp.dhis.showcaseapp.screens.others

import androidx.compose.runtime.Composable
import org.hisp.dhis.showcaseapp.screens.previews.TagsPreview
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer

@Composable
fun TagsScreen() {
    ColumnScreenContainer(title = "Tag component") {
        TagsPreview()
    }
}
