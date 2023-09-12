package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
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
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        containerColor = Color.Transparent,
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        dragHandle = {
            BottomSheetDefaults.HiddenShape
            Box(modifier = Modifier.background(Color.Transparent)) {
                BottomSheetIconButton(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Button",
                            tint = SurfaceColor.SurfaceBright,
                        )
                    },
                    modifier = Modifier.padding(bottom = Spacing.Spacing4),
                ) {
                    scope.launch {
                        onDismiss()
                    }
                }
            }
        },
    ) {
        Column(
            modifier = Modifier
                .background(SurfaceColor.SurfaceBright, Shape.ExtraLargeTop)
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
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = Spacing.Spacing24),
            ) {
                content?.let {
                    it.invoke()
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth().padding(Spacing.Spacing8),
                        color = TextColor.OnDisabledSurface,
                    )
                }
            }

            buttonBlock?.invoke()
        }
    }
}
