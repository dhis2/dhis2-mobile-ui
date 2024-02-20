package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
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
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing8
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.Color as ThemeColor

@Composable
fun BottomSheetHeader(
    title: String,
    subTitle: String? = null,
    description: String? = null,
    icon:
    @Composable
    (() -> Unit)? = null,
    hasSearch: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val horizontalAlignment = if (icon != null || hasSearch) Alignment.CenterHorizontally else Alignment.Start
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
        )

        subTitle?.let {
            Spacer(Modifier.requiredHeight(4.dp))

            Text(
                subTitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.OnDisabledSurface,
            )
        }

        description?.let {
            Spacer(Modifier.requiredHeight(16.dp))

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
 *
 * Need to override [searchQuery], [onSearchQueryChanged] & [onSearch] in order
 * to show the search bar. (TODO: We can add lint check for this)
 *
 * @param title: title to be shown
 * @param subtitle: subTitle to be shown
 * @param description: PopUp description
 * @param searchQuery: Search query to be displayed in the search bar
 * @param icon: the icon to be shown
 * @param buttonBlock: Space for the lower buttons
 * @param content: to be shown under the header
 * @param contentScrollState: Pass custom scroll state when content is
 * scrollable. For example, pass configure it when using `LazyColumn` to `Modifier.verticalScroll`
 * for content.
 * @param onSearchQueryChanged: Callback when search query is changed
 * @param onSearch: Callback when search action is triggered
 * @param onDismiss: gives access to the onDismiss event
 * @param modifier allows a modifier to be passed externally
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetShell(
    content: @Composable (() -> Unit)?,
    title: String? = null,
    subtitle: String? = null,
    description: String? = null,
    searchQuery: String? = null,
    showSectionDivider: Boolean = true,
    contentScrollState: ScrollableState = rememberScrollState(),
    icon: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onSearchQueryChanged: ((String) -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
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
        val canScrollForward by derivedStateOf { contentScrollState.canScrollForward }

        Column {
            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .background(SurfaceColor.SurfaceBright, Shape.ExtraLargeTop)
                    .padding(top = Spacing24),
            ) {
                val hasSearch = searchQuery != null && onSearchQueryChanged != null && onSearch != null
                val hasTitle by derivedStateOf { !title.isNullOrBlank() }
                if (hasTitle) {
                    BottomSheetHeader(
                        title!!,
                        subtitle,
                        description,
                        icon,
                        hasSearch,
                        modifier = Modifier
                            .padding(vertical = Spacing0)
                            .align(Alignment.CenterHorizontally),
                    )
                }

                if (hasTitle && hasSearch) {
                    Spacer(Modifier.requiredHeight(16.dp))
                }

                if (hasSearch) {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = Spacing24),
                        text = searchQuery!!,
                        onQueryChange = onSearchQueryChanged!!,
                        onSearch = onSearch!!,
                    )
                }

                if (hasTitle || hasSearch) {
                    if (showSectionDivider) {
                        Divider(
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = Spacing24, start = Spacing24, end = Spacing24),
                            color = TextColor.OnDisabledSurface,
                        )
                    } else {
                        Spacer(Modifier.requiredHeight(Spacing24))
                    }
                }

                content?.let {
                    val scrollModifier = if ((contentScrollState as? ScrollState) != null) {
                        Modifier.verticalScroll(contentScrollState)
                    } else {
                        Modifier
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = Spacing24)
                            .heightIn(Spacing0, InternalSizeValues.Size386)
                            .then(scrollModifier),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        content.invoke()
                        if (showSectionDivider) {
                            Divider(
                                modifier = Modifier.fillMaxWidth().padding(top = Spacing8),
                                color = TextColor.OnDisabledSurface,
                            )
                        }
                    }
                }
            }

            val shadowModifier = if (canScrollForward) {
                Modifier.shadow(elevation = 32.dp, ambientColor = ThemeColor.Blue900, spotColor = ThemeColor.Blue900)
            } else {
                Modifier
            }

            Box(
                Modifier.fillMaxWidth()
                    .then(shadowModifier)
                    .background(SurfaceColor.SurfaceBright)
                    .padding(start = Spacing24, top = Spacing24, end = Spacing24, bottom = Spacing24),
                contentAlignment = Alignment.BottomCenter,
            ) {
                buttonBlock?.invoke()
                Spacer(Modifier.size(Spacing8))
            }
        }
    }
}
