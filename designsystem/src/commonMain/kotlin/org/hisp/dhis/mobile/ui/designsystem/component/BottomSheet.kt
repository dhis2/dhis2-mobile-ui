package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
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
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellDefaults
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellUIState
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
    icon: @Composable (() -> Unit)? = null,
    hasSearch: Boolean = false,
    headerTextAlignment: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier,
) {
    val horizontalAlignment =
        if (icon != null || hasSearch) Alignment.CenterHorizontally else Alignment.Start
    Column(
        modifier =
            modifier
                .padding(horizontal = Spacing24, vertical = Spacing0)
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
 * @param uiState UI data class of type [BottomSheetShellUIState] with all the values for the ui elements used in the component.
 * @param windowInsets: The insets to use for the bottom sheet shell.
 * @param icon: the icon to be shown.
 * @param buttonBlock: Space for the lower buttons, use together with BottomSheetShellDefaults
 * button block padding to ensure a correct style is displayed.
 * @param content: to be shown under the header.
 * @param contentScrollState: Pass custom scroll state when content is
 * scrollable. For example, pass configure it when using `LazyColumn` to `Modifier.verticalScroll`
 * for content. If you want the content to be scrollable in desktop using the middle mouse button,
 * the modifier [Modifier.draggableList] must be used.
 * @param onSearchQueryChanged: Callback when search query is changed.
 * @param onSearch: Callback when search action is triggered.
 * @param onDismiss: gives access to the onDismiss event.
 * @param modifier allows a modifier to be passed externally.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetShell(
    uiState: BottomSheetShellUIState,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)?,
    windowInsets: @Composable () -> WindowInsets = { BottomSheetShellDefaults.windowInsets() },
    contentScrollState: ScrollableState? = null,
    icon: @Composable (() -> Unit)? = null,
    buttonBlock: @Composable (() -> Unit)? = null,
    onSearchQueryChanged: ((String) -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val keyboardState by keyboardAsState()
    val canDismissByDragging = contentScrollState == null
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = {
                when (it) {
                    SheetValue.Hidden -> canDismissByDragging
                    SheetValue.Expanded -> true
                    SheetValue.PartiallyExpanded -> true
                }
            },
        )

    var isKeyboardOpen by remember { mutableStateOf(false) }
    val showHeader by remember {
        derivedStateOf {
            if (uiState.animateHeaderOnKeyboardAppearance) {
                !uiState.title.isNullOrBlank() && !isKeyboardOpen
            } else {
                !uiState.title.isNullOrBlank()
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
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = null,
                            onClick = onDismiss,
                            indication = null,
                        ).padding(top = Spacing.Spacing72),
                contentAlignment = Alignment.BottomCenter,
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
        val canScrollForward by derivedStateOf { contentScrollState?.canScrollForward == true }

        Column(
            modifier =
                Modifier
                    .imePadding()
                    .padding(bottom = Spacing0)
                    .background(SurfaceColor.SurfaceBright, Shape.ExtraLargeTop),
        ) {
            val scrollColumnShadow =
                if (canScrollForward) {
                    Modifier.innerShadow(blur = 32.dp)
                } else {
                    Modifier
                }
            Column(
                modifier =
                    Modifier
                        .padding(top = Spacing24),
            ) {
                val hasSearch =
                    uiState.searchQuery != null && onSearchQueryChanged != null && onSearch != null
                AnimatedVisibility(
                    visible = showHeader,
                ) {
                    BottomSheetHeader(
                        title = uiState.title!!,
                        subTitle = uiState.subtitle,
                        description = uiState.description,
                        icon = icon,
                        hasSearch = hasSearch,
                        headerTextAlignment = uiState.headerTextAlignment,
                        modifier =
                            Modifier
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
                        text = uiState.searchQuery!!,
                        onQueryChange = onSearchQueryChanged!!,
                        onSearch = onSearch!!,
                    )
                }

                if (showHeader || hasSearch) {
                    if (uiState.showTopSectionDivider) {
                        HorizontalDivider(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = Spacing24,
                                        start = Spacing24,
                                        end = Spacing24,
                                        bottom = Spacing0,
                                    ),
                            color = TextColor.OnDisabledSurface,
                            thickness = Border.Thin,
                        )
                    } else {
                        Spacer(Modifier.requiredHeight(Spacing24))
                    }
                }

                content?.let {
                    val scrollModifier =
                        if ((contentScrollState as? ScrollState) != null) {
                            Modifier.verticalScroll(contentScrollState)
                        } else {
                            Modifier
                        }
                    Column(
                        Modifier.then(scrollColumnShadow).padding(Spacing0),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (uiState.showTopSectionDivider) {
                            Spacer(Modifier.requiredHeight(Spacing8))
                        }
                        Column(
                            modifier =
                                Modifier
                                    .padding(uiState.contentPadding)
                                    .heightIn(
                                        uiState.scrollableContainerMinHeight,
                                        uiState.scrollableContainerMaxHeight,
                                    ).then(scrollModifier),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = spacedBy(Spacing8),
                        ) {
                            content.invoke()
                            if (uiState.showBottomSectionDivider) {
                                Spacer(Modifier.requiredHeight(Spacing8))
                            }
                        }
                        if (uiState.showBottomSectionDivider && !canScrollForward) {
                            HorizontalDivider(
                                modifier =
                                    Modifier.fillMaxWidth().padding(
                                        start = Spacing24,
                                        end = Spacing24,
                                        bottom = Spacing0,
                                        top = Spacing0,
                                    ),
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
            Spacer(Modifier.requiredHeight(BottomSheetShellDefaults.safePadding()))
        }
    }
}
