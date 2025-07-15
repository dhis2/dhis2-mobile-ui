@file:OptIn(ExperimentalSharedTransitionApi::class)

package org.hisp.dhis.showcaseapp

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.MetadataAvatarSize
import org.hisp.dhis.mobile.ui.designsystem.component.VerticalTabs
import org.hisp.dhis.mobile.ui.designsystem.component.layout.TwoPaneConfig
import org.hisp.dhis.mobile.ui.designsystem.component.layout.TwoPaneLayout
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel
import org.hisp.dhis.mobile.ui.designsystem.component.model.Tab
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.showcaseapp.screens.Groups
import org.hisp.dhis.showcaseapp.screens.NoComponentSelectedScreen
import org.hisp.dhis.showcaseapp.screens.actionInputs.ActionInputsScreen
import org.hisp.dhis.showcaseapp.screens.basicTextInputs.BasicTextInputsScreen
import org.hisp.dhis.showcaseapp.screens.bottomSheets.BottomSheetsScreen
import org.hisp.dhis.showcaseapp.screens.buttons.ButtonsScreen
import org.hisp.dhis.showcaseapp.screens.cards.CardsScreen
import org.hisp.dhis.showcaseapp.screens.layouts.TwoPaneLayoutScreen
import org.hisp.dhis.showcaseapp.screens.location.LocationSearchBarScreen
import org.hisp.dhis.showcaseapp.screens.menu.MenuScreen
import org.hisp.dhis.showcaseapp.screens.others.BadgesScreen
import org.hisp.dhis.showcaseapp.screens.others.ChipsScreen
import org.hisp.dhis.showcaseapp.screens.others.IndicatorScreen
import org.hisp.dhis.showcaseapp.screens.others.LegendScreen
import org.hisp.dhis.showcaseapp.screens.others.MetadataAvatarScreen
import org.hisp.dhis.showcaseapp.screens.others.NavigationBarScreen
import org.hisp.dhis.showcaseapp.screens.others.ProgressScreen
import org.hisp.dhis.showcaseapp.screens.others.SearchBarScreen
import org.hisp.dhis.showcaseapp.screens.others.SectionScreen
import org.hisp.dhis.showcaseapp.screens.others.TagsScreen
import org.hisp.dhis.showcaseapp.screens.others.TopBarScreen
import org.hisp.dhis.showcaseapp.screens.parameter.ParameterSelectorScreen
import org.hisp.dhis.showcaseapp.screens.table.InputDialogScreen
import org.hisp.dhis.showcaseapp.screens.table.TableScreen
import org.hisp.dhis.showcaseapp.screens.tabs.TabsScreen
import org.hisp.dhis.showcaseapp.screens.toggleableInputs.ToggleableInputsScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun App(
    sizeClass: WindowSizeClass,
    imageBitmapLoader: (() -> ImageBitmap)? = null,
    onLocationRequest: (
        (
            locationQuery: String,
            locationSearchCallback: (List<LocationItemModel>) -> Unit,
        ) -> Unit
    )? = null,
) {
    DHIS2Theme {
        var currentScreen by remember { mutableStateOf(Groups.NO_GROUP_SELECTED) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor =
                when (currentScreen) {
                    Groups.NO_GROUP_SELECTED -> Color.White
                    else -> SurfaceColor.Container
                },
            bottomBar = {
                Box {}
            },
        ) { innerPadding ->
            SharedTransitionLayout(modifier = Modifier.padding(innerPadding)) {
                AnimatedContent(
                    sizeClass.widthSizeClass,
                    label = "content_size_transition",
                ) { widthSizeClass ->
                    when (widthSizeClass) {
                        WindowWidthSizeClass.Expanded ->
                            ExpandedMain(
                                currentScreen = currentScreen,
                                imageBitmapLoader = imageBitmapLoader,
                                onLocationRequest = onLocationRequest,
                                animatedVisibilityScope = this,
                                sharedTransitionScope = this@SharedTransitionLayout,
                            ) {
                                currentScreen = it
                            }

                        else ->
                            Main(
                                currentScreen = currentScreen,
                                imageBitmapLoader = imageBitmapLoader,
                                onLocationRequest = onLocationRequest,
                                animatedVisibilityScope = this,
                                sharedTransitionScope = this@SharedTransitionLayout,
                            ) {
                                currentScreen = it
                            }
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandedMain(
    currentScreen: Groups,
    imageBitmapLoader: (() -> ImageBitmap)?,
    onLocationRequest: (
        (
            locationQuery: String,
            locationSearchCallback: (List<LocationItemModel>) -> Unit,
        ) -> Unit
    )?,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onSectionChanged: (Groups) -> Unit,
) {
    TwoPaneLayout(
        paneConfig = TwoPaneConfig.SecondaryPaneFixedSize(200.dp),
        primaryPane = {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(Spacing.Spacing16)
                        .background(SurfaceColor.Container)
                        .padding(Spacing.Spacing16),
                verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
            ) {
                ScreenProvider(
                    screen = currentScreen,
                    imageBitmapLoader = imageBitmapLoader,
                    onLocationRequest = onLocationRequest,
                    animatedVisibilityScope = animatedVisibilityScope,
                    sharedTransitionScope = sharedTransitionScope,
                )
            }
        },
        secondaryPane = {
            val tabs by remember {
                derivedStateOf {
                    Groups.entries
                        .map {
                            Tab(
                                id = it.name,
                                label = it.label,
                            )
                        }.sortedBy {
                            it.label
                        }
                }
            }
            val selectedTabIndex by remember(currentScreen) {
                derivedStateOf {
                    tabs.indexOfFirst { it.label == currentScreen.label }
                }
            }
            VerticalTabs(
                modifier = Modifier.padding(Spacing.Spacing16),
                tabs = tabs,
                initialSelectedTabIndex = selectedTabIndex,
                onSectionSelected = {
                    onSectionChanged(Groups.valueOf(it))
                },
            )
        },
    )
}

@Composable
fun Main(
    currentScreen: Groups,
    imageBitmapLoader: (() -> ImageBitmap)?,
    onLocationRequest: (
        (
            locationQuery: String,
            locationSearchCallback: (List<LocationItemModel>) -> Unit,
        ) -> Unit
    )?,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onSectionChanged: (Groups) -> Unit,
) {
    var isComponentSelected by remember(currentScreen) {
        mutableStateOf(currentScreen != Groups.NO_GROUP_SELECTED)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.Spacing16),
        modifier =
            Modifier
                .background(SurfaceColor.Container),
    ) {
        var screenDropdownItemList by remember {
            mutableStateOf(emptyList<DropdownItem>())
        }

        if (isComponentSelected) {
            InputDropDown(
                modifier =
                    Modifier.padding(
                        start = Spacing.Spacing16,
                        end = Spacing.Spacing16,
                        top = Spacing.Spacing16,
                    ),
                title = "Group",
                fetchItem = { index -> screenDropdownItemList[index] },
                itemCount = screenDropdownItemList.size,
                onSearchOption = { query ->
                    screenDropdownItemList =
                        if (query.isNotEmpty()) {
                            screenDropdownItemList.filter { it.label.contains(query) }
                        } else {
                            Groups.entries.map {
                                DropdownItem(it.label)
                            }
                        }
                },
                useDropDown = false,
                onItemSelected = { _, item -> onSectionChanged(getCurrentScreen(item.label)) },
                onResetButtonClicked = { onSectionChanged(Groups.NO_GROUP_SELECTED) },
                state = InputShellState.UNFOCUSED,
                expanded = currentScreen == Groups.NO_GROUP_SELECTED,
                selectedItem = DropdownItem(currentScreen.label),
                inputStyle =
                    InputStyle
                        .DataInputStyle()
                        .apply { backGroundColor = SurfaceColor.SurfaceBright },
                loadOptions = {
                    screenDropdownItemList =
                        Groups.entries.map {
                            DropdownItem(it.label)
                        }
                },
            )

            ScreenProvider(
                screen = currentScreen,
                imageBitmapLoader = imageBitmapLoader,
                onLocationRequest = onLocationRequest,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
            )
        } else {
            NoComponentSelectedScreen(
                modifier =
                    Modifier
                        .background(Color.White, Shape.NoRounding),
            ) {
                isComponentSelected = !isComponentSelected
            }
        }
    }
}

@Composable
fun ScreenProvider(
    screen: Groups,
    imageBitmapLoader: (() -> ImageBitmap)?,
    onLocationRequest: ((locationQuery: String, locationSearchCallback: (List<LocationItemModel>) -> Unit) -> Unit)?,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
) {
    with(sharedTransitionScope) {
        Column(
            modifier =
                Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "screen"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
            verticalArrangement = spacedBy(Spacing.Spacing16),
        ) {
            when (screen) {
                Groups.ACTION_INPUTS -> ActionInputsScreen()
                Groups.BADGES -> BadgesScreen()
                Groups.BASIC_TEXT_INPUTS -> BasicTextInputsScreen()
                Groups.BOTTOM_SHEETS -> BottomSheetsScreen()
                Groups.BUTTONS -> ButtonsScreen()
                Groups.CARDS -> CardsScreen()
                Groups.CHIPS -> ChipsScreen()
                Groups.INDICATOR -> IndicatorScreen()
                Groups.LEGEND -> LegendScreen()
                Groups.METADATA_AVATAR -> MetadataAvatarScreen()
                Groups.PROGRESS_INDICATOR -> ProgressScreen()
                Groups.PARAMETER_SELECTOR -> ParameterSelectorScreen()
                Groups.SECTIONS -> SectionScreen()
                Groups.TOGGLEABLE_INPUTS -> ToggleableInputsScreen(imageBitmapLoader)
                Groups.TAGS -> TagsScreen()
                Groups.SEARCH_BAR -> SearchBarScreen()
                Groups.NAVIGATION_BAR -> NavigationBarScreen()
                Groups.MENU -> MenuScreen()
                Groups.NO_GROUP_SELECTED -> NoComponentSelectedScreen()
                Groups.TOP_BAR -> TopBarScreen()
                Groups.LOCATION_SEARCH_BAR ->
                    LocationSearchBarScreen { locationQuery, locationCallback ->
                        onLocationRequest?.invoke(locationQuery, locationCallback)
                    }

                Groups.TABLE -> TableScreen()
                Groups.INPUT_DIALOG -> InputDialogScreen()
                Groups.TABS -> TabsScreen()
                Groups.TWO_PANE_LAYOUT -> TwoPaneLayoutScreen()
            }
        }
    }
}

fun getCurrentScreen(label: String): Groups = Groups.entries.firstOrNull { it.label == label } ?: Groups.ACTION_INPUTS

fun getAvailableMetadataAvatarSizes() =
    listOf(
        MetadataAvatarSize.XS(),
        MetadataAvatarSize.S(),
        MetadataAvatarSize.M(),
        MetadataAvatarSize.L(),
        MetadataAvatarSize.XL(),
    )
