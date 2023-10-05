package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputFileResource
import org.hisp.dhis.mobile.ui.designsystem.component.UploadFileState

@Composable
fun InputFileResourceScreen() {
    ColumnComponentContainer {
        InputFileResource(
            title = "Label"
        )
        InputFileResource(
            title = "Label",
            uploadFileState = UploadFileState.UPLOADING
        )
        InputFileResource(
            title = "Label",
            fileName = "filename.extension",
            fileWeight = "524kb",
            uploadFileState = UploadFileState.LOADED
        )
    }
}