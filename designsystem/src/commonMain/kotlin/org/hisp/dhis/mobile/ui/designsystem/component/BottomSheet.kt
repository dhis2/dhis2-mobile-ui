package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
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
        modifier = modifier.padding(horizontal = Spacing.Spacing24, vertical = Spacing.Spacing0),
        horizontalAlignment = horizontalAlignment,
    ) {
        icon?. let {
            it.invoke()

            Spacer(Modifier.size(Spacing.Spacing16))
        }

        Text(
            title,
            style = MaterialTheme.typography.headlineSmall,
            color = TextColor.OnPrimaryContainer,
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

@Composable
expect fun BottomSheetShell(
    title: String,
    subtitle: String? = null,
    description: String? = null,
    icon: @Composable (() -> Unit)? = null,
    searchBar: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit,
)

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetShell(
    title: String,
    subtitle: String? = null,
    description: String? = null,
    icon: @Composable (() -> Unit)? = null,
    searchBar: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    val animateTrigger = remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            skipPartiallyExpanded = false, // pass false here
            initialValue = SheetValue.Expanded
        )
    )


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

                Divider(
                    color = TextColor.OnDisabledSurface,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8),
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
*/
@Composable
internal fun AnimatedExpandTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(
            expandFrom = Alignment.Bottom,
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Bottom,
        ),
        content = content,
    )
}
