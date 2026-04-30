package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

internal expect fun fullScreenDialogProperties(): DialogProperties

@Composable
internal expect fun ConfigureDialogSystemBars()

/**
 * DHIS2 Full Screen Dialog.
 * @param modifier: allows a modifier to be passed externally.
 * @param onDismiss: is a callback to dismiss the full screen dialog component.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(
        properties = fullScreenDialogProperties(),
        onDismissRequest = onDismiss,
    ) {
        ConfigureDialogSystemBars()
        Scaffold(
            modifier = modifier,
            containerColor = SurfaceColor.SurfaceBright,
            topBar = {
                TopBar(
                    colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = SurfaceColor.SurfaceBright,
                        ),
                    navigationIcon = {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.testTag("FULL_SCREEN_DIALOG_BACK_BUTTON"),
                            icon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                    contentDescription = "Back Button",
                                )
                            },
                        )
                    },
                    title = {},
                    actions = {},
                )
            },
            content = {
                Column(Modifier.padding(it)) {
                    content()
                }
            },
        )
    }
}
