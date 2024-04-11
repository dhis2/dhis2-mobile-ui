package org.hisp.dhis.common.screens.others

import androidx.compose.runtime.Composable
import org.hisp.dhis.common.screens.previews.TagsPreview
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer

@Composable
fun TagsScreen() {
    ColumnComponentContainer(title = "Tags") {
        TagsPreview()
    }
}
