package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.common.screens.previews.longLegendList
import org.hisp.dhis.common.screens.previews.lorem
import org.hisp.dhis.common.screens.previews.regularLegendList
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.LegendRange
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun BottomSheetScreen() {
    var showBottomSheetShellScrollableContent by rememberSaveable { mutableStateOf(false) }

    var showLegendBottomSheetShell by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetShellMaxExpansion by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetShellSingleButton by rememberSaveable { mutableStateOf(false) }
    var showBottomSheetShellTwoButtons by rememberSaveable { mutableStateOf(false) }

    if (showLegendBottomSheetShell) {
        BottomSheetShell(
            title = "Legend name ",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            content = {
                Column() {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
        ) {
            showLegendBottomSheetShell = false
        }
    }
    if (showBottomSheetShellScrollableContent) {
        BottomSheetShell(
            title = "Legend name ",
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            content = {
                Column() {
                    LegendRange(
                        longLegendList,
                    )
                }
            },
        ) {
            showBottomSheetShellScrollableContent = false
        }
    }
    if (showBottomSheetShellMaxExpansion) {
        BottomSheetShell(
            title = "Legend name ",
            subtitle = "Subtitle",
            description = lorem + lorem,
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
                                showBottomSheetShellMaxExpansion = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            content = {
                Column() {
                    LegendRange(
                        longLegendList,
                    )
                }
            },
        ) {
            showBottomSheetShellMaxExpansion = false
        }
    }

    if (showBottomSheetShellSingleButton) {
        BottomSheetShell(
            title = "Legend name ",
            subtitle = "Subtitle",
            description = lorem,
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
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            content = {
                Column() {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
        ) {
            showBottomSheetShellSingleButton = false
        }
    }

    if (showBottomSheetShellTwoButtons) {
        BottomSheetShell(
            title = "Legend name ",
            subtitle = "Subtitle",
            description = lorem,
            buttonBlock = {
                ButtonBlock(
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
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Button",
                    tint = SurfaceColor.Primary,
                )
            },
            content = {
                Column() {
                    LegendRange(
                        regularLegendList,
                    )
                }
            },
        ) {
            showBottomSheetShellTwoButtons = false
        }
    }

    ColumnComponentContainer {
        SubTitle("Legend type bottom sheet shell")
        Button(
            enabled = true,
            ButtonStyle.FILLED,
            text = "Show Modal",
        ) {
            showLegendBottomSheetShell = !showLegendBottomSheetShell
        }
        Spacer(modifier = Modifier.size(Spacing.Spacing10))

        SubTitle("Bottom sheet shell with scrollable content")
        Button(
            enabled = true,
            ButtonStyle.FILLED,
            text = "Show Modal",
        ) {
            showBottomSheetShellScrollableContent = !showBottomSheetShellScrollableContent
        }
        Spacer(modifier = Modifier.size(Spacing.Spacing10))

        SubTitle("Bottom sheet shell with with maximum expansion ")
        Button(
            enabled = true,
            ButtonStyle.FILLED,
            text = "Show Modal",
        ) {
            showBottomSheetShellMaxExpansion = !showBottomSheetShellScrollableContent
        }
        Spacer(modifier = Modifier.size(Spacing.Spacing10))
        SubTitle("Bottom sheet shell with single button")
        Button(
            enabled = true,
            ButtonStyle.FILLED,
            text = "Show Modal",
        ) {
            showBottomSheetShellSingleButton = !showBottomSheetShellSingleButton
        }
        Spacer(modifier = Modifier.size(Spacing.Spacing10))

        SubTitle("Bottom sheet shell with two buttons")
        Button(
            enabled = true,
            ButtonStyle.FILLED,
            text = "Show Modal",
        ) {
            showBottomSheetShellTwoButtons = !showBottomSheetShellTwoButtons
        }
        Spacer(modifier = Modifier.size(Spacing.Spacing10))
    }
}
