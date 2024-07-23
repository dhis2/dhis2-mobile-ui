package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.TagsPreview
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer

@Composable
fun TagsScreen() {
    ColumnScreenContainer(title = "Tag component") {
        TagsPreview()
    }
}
