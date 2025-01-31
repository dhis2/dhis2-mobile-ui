package org.hisp.dhis.common.screens.bottomSheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.previews.longLegendList
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.common.screens.previews.regularLegendList
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColorStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.LegendRange
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellDefaults
import org.hisp.dhis.mobile.ui.designsystem.component.state.BottomSheetShellUIState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun BottomSheetScreen() {
    var showBottomSheetShellScrollableContent by rememberSaveable { mutableStateOf(false) }

    var showLegendBottomSheetShell by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetShellMaxExpansion by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetShellSingleButton by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetShellTwoButtons by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetWithSearchBar by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetWithoutTitle by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetWithoutContent by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetWithAndroid35Paddings by rememberSaveable { mutableStateOf(false) }

    if (showLegendBottomSheetShell) {
        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                title = "Legend name ",
            ),
            content = {
                Column {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
        ) {
            showLegendBottomSheetShell = false
        }
    }
    if (showBottomSheetShellScrollableContent) {
        val scrollState = rememberLazyListState()
        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                title = "Legend name ",
            ),
            content = {
                LazyColumn(state = scrollState) {
                    items(longLegendList) { item ->
                        Column {
                            Text(
                                text = item.text,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                            )
                            HorizontalDivider()
                        }
                    }
                }
            },
            contentScrollState = scrollState,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
        ) {
            showBottomSheetShellScrollableContent = false
        }
    }
    if (showBottomSheetShellMaxExpansion) {
        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                title = "Legend name ",
                subtitle = "Subtitle",
                description = lorem + lorem,
            ),
            content = {
                Column {
                    LegendRange(
                        longLegendList,
                    )
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            buttonBlock = {
                ButtonBlock(
                    modifier = Modifier.padding(BottomSheetShellDefaults.buttonBlockPaddings()),
                    primaryButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Button",
                                )
                            },
                            enabled = true,
                            text = "Label",
                            onClick = {
                                showBottomSheetShellMaxExpansion = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                )
            },
        ) {
            showBottomSheetShellMaxExpansion = false
        }
    }

    if (showBottomSheetWithAndroid35Paddings) {
        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                title = "Legend name ",
                bottomPadding = BottomSheetShellDefaults.lowerPadding(true),
                subtitle = "Subtitle",
                description = lorem + lorem,
            ),
            content = {
                Column {
                    LegendRange(
                        longLegendList,
                    )
                }
            },
            windowInsets = { BottomSheetShellDefaults.windowInsets(true) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            buttonBlock = {
                ButtonBlock(
                    modifier = Modifier.padding(
                        BottomSheetShellDefaults.buttonBlockPaddings(),
                    ),
                    primaryButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Button",
                                )
                            },
                            enabled = true,
                            text = "Label",
                            onClick = {
                                showBottomSheetShellMaxExpansion = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                )
            },
        ) {
            showBottomSheetShellMaxExpansion = false
        }
    }

    if (showBottomSheetShellSingleButton) {
        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                title = "Legend name ",
                subtitle = "Subtitle",
                description = lorem,
            ),
            content = {
                Column {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            buttonBlock = {
                ButtonBlock(
                    primaryButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Button",
                                )
                            },
                            enabled = true,
                            text = "Label",
                            onClick = {
                                showBottomSheetShellSingleButton = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    modifier = Modifier.padding(BottomSheetShellDefaults.buttonBlockPaddings()),
                )
            },
        ) {
            showBottomSheetShellSingleButton = false
        }
    }

    if (showBottomSheetShellTwoButtons) {
        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                title = "Legend name ",
                subtitle = "Subtitle",
                description = lorem,
            ),
            content = {
                Column {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            buttonBlock = {
                Row(
                    modifier = Modifier.padding(BottomSheetShellDefaults.buttonBlockPaddings()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        modifier = Modifier.weight(0.5f),
                        style = ButtonStyle.OUTLINED,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Button",
                            )
                        },
                        enabled = true,
                        text = "Label",
                        onClick = {
                            showBottomSheetShellTwoButtons = false
                        },

                    )

                    Spacer(Modifier.size(Spacing.Spacing8))
                    Button(
                        modifier = Modifier.weight(0.5f),

                        style = ButtonStyle.FILLED,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Button",
                            )
                        },
                        enabled = true,
                        text = "Label",
                        onClick = {
                        },
                    )
                }
            },
        ) {
            showBottomSheetShellTwoButtons = false
        }
    }

    if (showBottomSheetWithSearchBar) {
        var searchQuery by rememberSaveable { mutableStateOf("") }

        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                title = "Bottom Sheet with Search Bar",
                subtitle = "Subtitle",
                description = lorem,
            ),
            content = {
                Column {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            buttonBlock = {
                ButtonBlock(
                    modifier = Modifier.padding(BottomSheetShellDefaults.buttonBlockPaddings()),
                    primaryButton = {
                        Button(
                            style = ButtonStyle.OUTLINED,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Button",
                                )
                            },
                            enabled = true,
                            text = "Label",
                            onClick = {
                                showBottomSheetShellTwoButtons = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    secondaryButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Button",
                                )
                            },
                            enabled = true,
                            text = "Label",
                            onClick = {
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                )
            },
            onSearchQueryChanged = { searchQuery = it },
            onSearch = { searchQuery = it },
            onDismiss = {
                showBottomSheetWithSearchBar = false
            },
        )
    }

    if (showBottomSheetWithoutTitle) {
        var searchQuery by rememberSaveable { mutableStateOf("") }

        BottomSheetShell(
            uiState = BottomSheetShellUIState(),
            content = {
                Column {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            onSearchQueryChanged = { searchQuery = it },
            onSearch = { searchQuery = it },
        ) {
            showBottomSheetWithoutTitle = false
        }
    }

    if (showBottomSheetWithoutContent) {
        BottomSheetShell(
            uiState = BottomSheetShellUIState(
                showTopSectionDivider = true,
                showBottomSectionDivider = false,
            ),
            content = null,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            buttonBlock = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(BottomSheetShellDefaults.buttonBlockPaddings()),
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        text = "Cancel",
                        onClick = {
                            showBottomSheetWithoutContent = false
                        },
                    )

                    Spacer(Modifier.requiredWidth(16.dp))

                    Button(
                        modifier = Modifier.weight(1f),
                        text = "Delete",
                        colorStyle = ColorStyle.ERROR,
                        style = ButtonStyle.FILLED,
                        onClick = {
                            showBottomSheetWithoutContent = false
                        },
                    )
                }
            },
        ) {
            showBottomSheetWithoutContent = false
        }
    }

    ColumnScreenContainer(title = BottomSheets.BOTTOM_SHEET.label) {
        ColumnComponentContainer("Legend type bottom sheet shell") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showLegendBottomSheetShell = !showLegendBottomSheetShell
            }
        }

        ColumnComponentContainer("Bottom sheet shell with scrollable content") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetShellScrollableContent = !showBottomSheetShellScrollableContent
            }
        }

        ColumnComponentContainer("Bottom sheet shell with with maximum expansion ") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetShellMaxExpansion = !showBottomSheetShellScrollableContent
            }
        }
        ColumnComponentContainer("Bottom sheet shell with single button") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetShellSingleButton = !showBottomSheetShellSingleButton
            }
        }

        ColumnComponentContainer("Bottom sheet shell with two buttons") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetShellTwoButtons = !showBottomSheetShellTwoButtons
            }
        }

        ColumnComponentContainer("Bottom sheet shell with for devices with edge to edge enabled") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetWithAndroid35Paddings = !showBottomSheetWithAndroid35Paddings
            }
        }

        ColumnComponentContainer("Bottom sheet shell with search bar") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetWithSearchBar = !showBottomSheetWithSearchBar
            }
        }

        ColumnComponentContainer("Bottom sheet shell without title") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetWithoutTitle = !showBottomSheetWithoutTitle
            }
        }

        ColumnComponentContainer("Bottom sheet shell without content") {
            Button(
                enabled = true,
                ButtonStyle.FILLED,
                text = "Show Modal",
            ) {
                showBottomSheetWithoutContent = !showBottomSheetWithoutContent
            }
        }
    }
}
