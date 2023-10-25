package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing0
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing24
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun BottomSheetHeader(
    title: String,
    subTitle: String? = null,
    description: String? = null,
    icon: @Composable
    (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val horizontalAlignment = if (icon != null) Alignment.CenterHorizontally else Alignment.Start
    Column(
        modifier = modifier.padding(horizontal = Spacing24, vertical = Spacing0)
            .fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
    ) {
        icon?. let {
            it.invoke()

            Spacer(Modifier.size(Spacing.Spacing16))
        }

        Text(
            title,
            style = MaterialTheme.typography.headlineSmall,
            color = TextColor.OnSurface,
            modifier = Modifier.padding(bottom = Spacing.Spacing4),
        )
        subTitle?.let {
            Text(
                subTitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.OnDisabledSurface,
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
            )
        }

        description?.let {
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor.OnSurfaceLight,
            )
        }
    }
}

/**
 * DHIS2 BottomSheetShell. Wraps compose · [ModalBottomSheet].
 * desktop version to be implemented
 * @param title: title to be shown
 * @param subtitle: subTitle to be shown
 * @param description: PopUp description
 * @param icon: the icon to be shown
 * @param searchBar: dhis searchBar
 * @param buttonBlock: Space for the lower buttons
 * @param content: to be shown under the header
 * @param onDismiss: gives access to the onDismiss event
 * @param modifier allows a modifier to be passed externally
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetShell(
    title: String,
    subtitle: String? = null,
    description: String? = null,
    icon: @Composable (() -> Unit)? = null,
    searchBar: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
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
        val contentScrollState = rememberScrollState()

        // While the scroll state has `canScrollForward` variable. It's not
        // working as expected. The max value for the scroll area is fluctuating.
        // Instead we are getting the initial max value to compare it with
        // changing value similar to how it's done in `ScrollState`
        var contentScrollMaxValue by remember { mutableStateOf(0) }

        LaunchedEffect(Unit) {
            contentScrollMaxValue = contentScrollState.maxValue
        }

        val canScrollForward by derivedStateOf { contentScrollState.value < (contentScrollMaxValue - Spacing24.value) }

        Column {
            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .background(SurfaceColor.SurfaceBright, Shape.ExtraLargeTop)
                    .padding(top = Spacing24),
            ) {
                BottomSheetHeader(
                    title,
                    subtitle,
                    description,
                    icon,
                    modifier = Modifier
                        .padding(vertical = Spacing0)
                        .align(Alignment.CenterHorizontally),
                )
                searchBar?.invoke()
                Divider(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = Spacing24, start = Spacing24, end = Spacing24),
                    color = TextColor.OnDisabledSurface,
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = Spacing24)
                        .heightIn(Spacing0, InternalSizeValues.Size386)
                        .verticalScroll(contentScrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    content?.let {
                        it.invoke()
                        Divider(
                            modifier = Modifier.fillMaxWidth().padding(top = Spacing.Spacing8),
                            color = TextColor.OnDisabledSurface,
                        )
                    }
                }
            }

            val shadowModifier = if (canScrollForward) {
                Modifier.shadow(elevation = 16.dp)
            } else {
                Modifier
            }

            Box(
                Modifier.fillMaxWidth()
                    .then(shadowModifier)
                    .background(SurfaceColor.SurfaceBright)
                    .padding(Spacing24),
                contentAlignment = Alignment.BottomCenter,
            ) {
                buttonBlock?.invoke()
            }
        }
    }
}
