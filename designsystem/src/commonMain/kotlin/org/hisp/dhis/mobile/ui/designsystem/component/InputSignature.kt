package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Draw
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import org.hisp.dhis.mobile.ui.designsystem.component.internal.signature.SignatureBottomSheet
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellDefaults
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

/**
 * DHIS2 Input signature. Wraps DHIS · [BasicInputImage].
 * @param title: controls the text to be shown for the title.
 * @param state: manages the InputShell state.
 * @param inputStyle: manages the InputShell style.
 * @param windowInsets: the insets for the bottom sheet shell.
 * @param supportingText: is a list of SupportingTextData that.
 * manages all the messages to be shown.
 * @param legendData: manages the legendComponent.
 * @param addSignatureBtnText: controls the text to be shown for the add signature button.
 * @param downloadButtonVisible: controls whether the download button is visible or not.
 * @param isRequired: controls whether the field is mandatory or not.
 * @param load: to load an image stored in the resource, device memory or from network.
 * we can use loadPainter, loadImageBitmap, loadSvgPainter or loadXmlImageVector.
 * @param painterFor: is a composable function which controls how to paint the load param.
 * @param modifier: allows a modifier to be passed externally.
 * @param onDownloadButtonClick: callback to when download button is clicked.
 * @param onShareButtonClick: callback to when share button is clicked.
 * @param onResetButtonClicked: callback to when reset button is clicked.
 * @param onSaveSignature: callback to when save button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> InputSignature(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    windowInsets: @Composable () -> WindowInsets = { BottomSheetShellDefaults.windowInsets() },
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    addSignatureBtnText: String = provideStringResource("add_signature"),
    downloadButtonVisible: Boolean = true,
    isRequired: Boolean = false,
    load: suspend () -> T,
    painterFor: (@Composable (T) -> Painter)? = null,
    modifier: Modifier = Modifier,
    onDownloadButtonClick: () -> Unit,
    onShareButtonClick: () -> Unit,
    onResetButtonClicked: () -> Unit,
    onSaveSignature: (ImageBitmap) -> Unit,
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val uploadState = getUploadState(painterFor, showBottomSheet)
    BasicInputImage(
        title = title,
        state = state,
        inputStyle = inputStyle,
        supportingText = supportingText,
        legendData = legendData,
        addButtonText = addSignatureBtnText,
        addButtonIcon = Icons.Outlined.Draw,
        uploadState = uploadState,
        downloadButtonVisible = downloadButtonVisible,
        isRequired = isRequired,
        load = load,
        painterFor = painterFor,
        testTag = "SIGNATURE",
        modifier = modifier,
        onDownloadButtonClick = onDownloadButtonClick,
        onShareButtonClick = onShareButtonClick,
        onResetButtonClicked = onResetButtonClicked,
        onAddButtonClicked = {
            showBottomSheet = true
        },
    )

    if (showBottomSheet) {
        SignatureBottomSheet(
            title = title,
            onDismiss = {
                showBottomSheet = false
            },
            onSave = {
                onSaveSignature.invoke(it)
                showBottomSheet = false
            },
            windowInsets = windowInsets,
        )
    }
}

internal fun <T> getUploadState(
    painterFor: (@Composable (T) -> Painter)? = null,
    isBottomSheetOpened: Boolean,
): UploadState =
    if (isBottomSheetOpened && painterFor == null) {
        UploadState.UPLOADING
    } else if (painterFor == null) {
        UploadState.ADD
    } else {
        UploadState.LOADED
    }
