package org.hisp.dhis.showcaseapp.screens.bottomSheets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetHeader
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun BottomSheetHeaderScreen() {
    ColumnScreenContainer(title = BottomSheets.BOTTOM_SHEET_HEADER.label) {
        ColumnComponentContainer("With Icon") {
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
        }

        ColumnComponentContainer("Without Icon") {
            Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
                BottomSheetHeader(
                    title = "Title",
                    subTitle = "Subtitle",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
                )
            }
        }

        ColumnComponentContainer("Without Icon, without subtitle") {
            Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
                BottomSheetHeader(
                    title = "Title",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
                )
            }
        }

        ColumnComponentContainer("Without Icon, subtitle or description") {
            Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
                BottomSheetHeader(
                    title = "Title",
                )
            }
        }

        ColumnComponentContainer("With Icon, without subtitle or description") {
            Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
                BottomSheetHeader(
                    title = "Title",
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.HelpOutline,
                            contentDescription = "Button",
                            tint = SurfaceColor.Primary,
                        )
                    },
                )
            }
        }

        ColumnComponentContainer("Bottom sheet shell with header text aligned to start") {
            Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
                BottomSheetHeader(
                    title = "Title",
                    subTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
                    headerTextAlignment = TextAlign.Start,
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.HelpOutline,
                            contentDescription = "Button",
                            tint = SurfaceColor.Primary,
                        )
                    },
                )
            }
        }
    }
}
