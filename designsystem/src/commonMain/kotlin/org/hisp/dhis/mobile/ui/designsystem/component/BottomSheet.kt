package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.hisp.dhis.mobile.ui.designsystem.component.internal.Keyboard
import org.hisp.dhis.mobile.ui.designsystem.component.internal.keyboardAsState
import org.hisp.dhis.mobile.ui.designsystem.theme.Border
import org.hisp.dhis.mobile.ui.designsystem.theme.InternalSizeValues
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing0
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing24
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing8
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.mobile.ui.designsystem.theme.innerShadow

/**
 * DHIS2 [BottomSheetHeader] component designed to be used
 * within [BottomSheetShell]
 *
 * @param title: header.
 * @param subTitle: optional subtitle.
 * @param description: optional description.
 * @param icon: optional icon to be shown above the header .
 * @param hasSearch: whether to adapt for [SearchBar] or not.
 * @param headerTextAlignment: optional header alignment [Alignment].
 * @param modifier width and size of the barcode.
 */
@Composable
fun BottomSheetHeader(
    title: String,
    subTitle: String? = null,
    description: String? = null,
    icon:
    @Composable
    (() -> Unit)? = null,
    hasSearch: Boolean = false,
    headerTextAlignment: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier,
) {
    val horizontalAlignment =
        if (icon != null || hasSearch) Alignment.CenterHorizontally else Alignment.Start
    Column(
        modifier = modifier.padding(horizontal = Spacing24, vertical = Spacing0)
            .fillMaxWidth(),
        horizontalAlignment = horizontalAlignment,
    ) {
        icon?.let {
            it.invoke()

            Spacer(Modifier.size(Spacing.Spacing16))
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = TextColor.OnSurface,
            textAlign = headerTextAlignment,
        )

        subTitle?.let {
            Spacer(Modifier.requiredHeight(4.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = subTitle,
                style = MaterialTheme.typography.bodySmall,
                color = TextColor.OnDisabledSurface,
                textAlign = headerTextAlignment,
            )
        }

        description?.let {
            Spacer(Modifier.requiredHeight(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextColor.OnSurfaceLight,
                textAlign = TextAlign.Start,
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
 * @param title: title to be shown.
 * @param subtitle: subTitle to be shown.
 * @param description: PopUp description.
 * @param searchQuery: Search query to be displayed in the search bar.
 * @param showTopSectionDivider: whether to show the top divider or not.
 * @param showBottomSectionDivider: whether to show the bottom divider or not.
 * @param windowInsets: The insets to use for the bottom sheet shell.
 * @param bottomPadding The lower padding for the bottom sheet shell.
 * @param icon: the icon to be shown.
 * @param buttonBlock: Space for the lower buttons.
 * @param content: to be shown under the header.
 * @param contentScrollState: Pass custom scroll state when content is
 * scrollable. For example, pass configure it when using `LazyColumn` to `Modifier.verticalScroll`
 * for content.
 * @param onSearchQueryChanged: Callback when search query is changed.
 * @param onSearch: Callback when search action is triggered.
 * @param onDismiss: gives access to the onDismiss event.
 * @param modifier allows a modifier to be passed externally.
 * @param headerTextAlignment [Alignment] for header text.
 * @param scrollableContainerMinHeight: Min size for scrollable content.
 * @param scrollableContainerMaxHeight: Max size for scrollable content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetShell(
    content: @Composable (() -> Unit)?,
    title: String? = null,
    subtitle: String? = null,
    description: String? = null,
    searchQuery: String? = null,
    showTopSectionDivider: Boolean = true,
    showBottomSectionDivider: Boolean = true,
    windowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    bottomPadding: Dp = Spacing0,
    contentScrollState: ScrollableState = rememberScrollState(),
    icon: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    headerTextAlignment: TextAlign = TextAlign.Center,
    scrollableContainerMinHeight: Dp = Spacing0,
    scrollableContainerMaxHeight: Dp = InternalSizeValues.Size386,
    animateHeaderOnKeyboardAppearance: Boolean = true,
    onSearchQueryChanged: ((String) -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()
    val keyboardState by keyboardAsState()

    var isKeyboardOpen by remember { mutableStateOf(false) }
    val showHeader by remember {
        derivedStateOf {
            if (animateHeaderOnKeyboardAppearance) {
                !title.isNullOrBlank() && !isKeyboardOpen
            } else {
                !title.isNullOrBlank()
            }
        }
    }

    LaunchedEffect(keyboardState) {
        isKeyboardOpen = keyboardState == Keyboard.Opened
    }

    ModalBottomSheet(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentWindowInsets = windowInsets,
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        dragHandle = {
            Box(
                modifier = Modifier.padding(top = Spacing.Spacing72),
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

        Column(
            modifier = Modifier.padding(bottom = Spacing0).background(SurfaceColor.SurfaceBright, Shape.ExtraLargeTop),
        ) {
            val scrollColumnShadow = if (canScrollForward) {
                Modifier.innerShadow(blur = 32.dp)
            } else {
                Modifier
            }
            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .padding(top = Spacing24),
            ) {
                val hasSearch =
                    searchQuery != null && onSearchQueryChanged != null && onSearch != null
                AnimatedVisibility(
                    visible = showHeader,
                ) {
                    BottomSheetHeader(
                        title = title!!,
                        subTitle = subtitle,
                        description = description,
                        icon = icon,
                        hasSearch = hasSearch,
                        headerTextAlignment = headerTextAlignment,
                        modifier = Modifier
                            .padding(vertical = Spacing0)
                            .align(Alignment.CenterHorizontally),
                    )
                }

                if (showHeader && hasSearch) {
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

                if (showHeader || hasSearch) {
                    if (showTopSectionDivider) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth()
                                .padding(top = Spacing24, start = Spacing24, end = Spacing24, bottom = Spacing0),
                            color = TextColor.OnDisabledSurface,
                            thickness = Border.Thin,
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
                        Modifier.then(scrollColumnShadow),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(Modifier.requiredHeight(Spacing8))
                        Column(
                            modifier = Modifier
                                .padding(horizontal = Spacing24)
                                .heightIn(scrollableContainerMinHeight, scrollableContainerMaxHeight)
                                .then(scrollModifier),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = spacedBy(Spacing8),
                        ) {
                            content.invoke()
                            Spacer(Modifier.requiredHeight(Spacing8))
                        }
                        if (showBottomSectionDivider && !canScrollForward) {
                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth().padding(start = Spacing24, end = Spacing24, bottom = Spacing0, top = Spacing0),
                                color = TextColor.OnDisabledSurface,
                                thickness = Border.Thin,
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.requiredHeight(Spacing24))
            buttonBlock?.let {
                buttonBlock.invoke()
            }
            Spacer(Modifier.requiredHeight(bottomPadding))
        }
    }
}
