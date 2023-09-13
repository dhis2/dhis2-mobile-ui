package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
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
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
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
    modifier: Modifier,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier,
        containerColor = Color.Transparent,
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        dragHandle = {
            Box(
                modifier = Modifier.background(Color.Transparent)
                    .padding(top = Spacing.Spacing72),
            ) {
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
                .padding(Spacing.Spacing24)
                .heightIn(Spacing.Spacing0, InternalSizeValues.Size800)
                .verticalScroll(rememberScrollState()),
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
                    .padding(top = Spacing.Spacing24),
                color = TextColor.OnDisabledSurface,
            )
            Box(
                modifier = Modifier.align(Alignment.Start)
                    .heightIn(Spacing.Spacing0, InternalSizeValues.Size386)
                    .padding(bottom = Spacing.Spacing24),
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxHeight(1f),
                ) {
                    content?.let {
                        it.invoke()
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth().padding(top = Spacing.Spacing8),
                            color = TextColor.OnDisabledSurface,
                        )
                    }
                }
            }
            buttonBlock?.invoke()
        }
    }
}
