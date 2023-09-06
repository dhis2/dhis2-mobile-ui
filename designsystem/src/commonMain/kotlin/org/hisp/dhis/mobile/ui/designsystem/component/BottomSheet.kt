package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
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
fun BottomSheetShell(
    title: String,
    subtitle: String,
    description: String,
    searchBar: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    val animateTrigger = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        launch {
            animateTrigger.value = true
        }
    }
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
        onDismissRequest = onDismiss,
    ) {
        AnimatedExpandTransition(animateTrigger.value) {
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
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
}

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
