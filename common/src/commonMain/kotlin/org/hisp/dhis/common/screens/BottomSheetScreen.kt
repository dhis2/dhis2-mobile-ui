package org.hisp.dhis.common.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetHeader
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetShell
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.LegendDescriptionData
import org.hisp.dhis.mobile.ui.designsystem.component.LegendRange
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun BottomSheetScreen() {

    /*SubTitle("With Icon", TextColor.OnSurface)
        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                subTitle = "Subtitle",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = "Button",
                        tint = SurfaceColor.Primary,
                    )
                },
            )
        }
        SubTitle("Without Icon", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                subTitle = "Subtitle",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
            )
        }
        SubTitle("Without Icon, without subtitle", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
            )
        }
        SubTitle("Without Icon, subtitle or description", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
            )
        }
        SubTitle("With Icon, without subtitle or description", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.HelpOutline,
                        contentDescription = "Button",
                        tint = SurfaceColor.Primary,
                    )
                },
            )
        }*/
    SubTitle("Bottom sheet shell with header, content and buttons", TextColor.OnSurface)

    var showBottomSheetShell by rememberSaveable { mutableStateOf(false) }


    ColumnComponentContainer {
        when (showBottomSheetShell) {
            showBottomSheetShell -> {

                BottomSheetShell(
                    "Legend name",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Button",
                            tint = SurfaceColor.Primary,
                        )
                    },
                    content = {
                        LegendRange(
                            listOf(
                                LegendDescriptionData(
                                    SurfaceColor.CustomGreen,
                                    "Low",
                                    IntRange(0, 5),
                                ),
                                LegendDescriptionData(
                                    SurfaceColor.CustomYellow,
                                    "Medium",
                                    IntRange(5, 10),
                                ),
                                LegendDescriptionData(
                                    TextColor.OnWarning,
                                    "High",
                                    IntRange(10, 20)
                                ),
                                LegendDescriptionData(
                                    SurfaceColor.CustomPink,
                                    "Very high",
                                    IntRange(20, 40),
                                ),
                                LegendDescriptionData(
                                    SurfaceColor.CustomBrown,
                                    "Extreme",
                                    IntRange(40, 120),
                                ),
                                LegendDescriptionData(
                                    SurfaceColor.CustomGray,
                                    "Lorem fistrum torpedo está la cosa muy malar diodeno" +
                                            " se calle ustée ahorarr al ataquerl condemor a wan. ",
                                    IntRange(120, 1000),
                                ),
                            ),
                        )
                    },
                    buttonBlock = {
                        ButtonBlock(
                            primaryButton = {
                                Button(
                                    style = ButtonStyle.FILLED,
                                    enabled = true,
                                    text = "Accept",
                                    onClick = {
                                        showBottomSheetShell = false
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            },
                            secondaryButton = {
                                Button(
                                    style = ButtonStyle.FILLED,
                                    enabled = true,
                                    text = "Dismiss",
                                    onClick = {
                                        showBottomSheetShell = false
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            },
                        )
                    },
                    onDismiss = { showBottomSheetShell = !showBottomSheetShell },
                )
            }

            else -> {}
        }
        Button(
            text = "show bottom sheet",
            onClick = {
                showBottomSheetShell = !showBottomSheetShell
            }
        )

    }
}

