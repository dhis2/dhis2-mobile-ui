package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun BottomSheetShell(
    title: String,
    subtitle: String?,
    description: String?,
    icon: @Composable (() -> Unit)?,
    searchBar: @Composable (() -> Unit)?,
    buttonBlock: @Composable (() -> Unit)?,
    content: @Composable (() -> Unit)?,
    onDismiss: () -> Unit,
) {
    val animateTrigger = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = false, // pass false here
            density = LocalDensity.current,
            initialValue = SheetValue.Expanded,
        ),
    )

    ModalBottomSheet(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .background(SurfaceColor.SurfaceBright, Shape.ExtraLarge)
                .padding(Spacing.Spacing24),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BottomSheetHeader(
                title,
                subtitle,
                description,
                icon,
                modifier = Modifier
                    .padding(horizontal = Spacing.Spacing24, vertical = Spacing.Spacing0),
            )
            searchBar?.invoke()

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8),
                color = TextColor.OnDisabledSurface,
            )

            content?.let {
                Column(modifier = Modifier.scrollable(orientation = Orientation.Vertical, state = rememberScrollState())) {
                    it.invoke()
                }
                Divider(
                    color = TextColor.OnDisabledSurface,
                    modifier = Modifier.fillMaxWidth().padding(Spacing.Spacing8),
                )
            }
            buttonBlock?.invoke()
        }
    }
}
