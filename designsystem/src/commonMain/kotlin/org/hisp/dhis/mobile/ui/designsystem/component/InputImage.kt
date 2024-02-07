package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

/**
 * DHIS2 Input image. Wraps DHIS · [BasicInputImage].
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param uploadState controls whether the image is added, loading, or need to be added.
 * @param addImageBtnText controls the text to be shown for the add image button
 * @param downloadButtonVisible controls whether the download button is visible or not
 * @param isRequired controls whether the field is mandatory or not
 * @param load to load an image stored in the resource, device memory or from network
 * we can use loadPainter, loadImageBitmap, loadSvgPainter or loadXmlImageVector
 * @param painterFor is a composable function which controls how to paint the load param,
 * @param modifier allows a modifier to be passed externally
 * @param onDownloadButtonClick callback to when download button is clicked
 * @param onShareButtonClick callback to when share button is clicked
 * @param onResetButtonClicked callback to when reset button is clicked
 * @param onAddButtonClicked callback to when add button is clicked
 */
@Composable
fun <T> InputImage(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    uploadState: UploadState = UploadState.ADD,
    addImageBtnText: String = provideStringResource("add_image"),
    downloadButtonVisible: Boolean = true,
    isRequired: Boolean = false,
    load: suspend () -> T,
    painterFor: (@Composable (T) -> Painter)? = null,
    modifier: Modifier = Modifier,
    onDownloadButtonClick: () -> Unit,
    onShareButtonClick: () -> Unit,
    onResetButtonClicked: () -> Unit,
    onAddButtonClicked: () -> Unit,
) {
    BasicInputImage(
        title = title,
        state = state,
        inputStyle = inputStyle,
        supportingText = supportingText,
        legendData = legendData,
        addButtonText = addImageBtnText,
        addButtonIcon = Icons.Outlined.FileUpload,
        uploadState = uploadState,
        downloadButtonVisible = downloadButtonVisible,
        isRequired = isRequired,
        load = load,
        painterFor = painterFor,
        testTag = "IMAGE",
        modifier = modifier,
        onDownloadButtonClick = onDownloadButtonClick,
        onShareButtonClick = onShareButtonClick,
        onResetButtonClicked = onResetButtonClicked,
        onAddButtonClicked = onAddButtonClicked,
    )
}
