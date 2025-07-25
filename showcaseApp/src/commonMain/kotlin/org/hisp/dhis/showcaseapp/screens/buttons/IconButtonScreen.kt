package org.hisp.dhis.showcaseapp.screens.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.showcaseapp.screens.previews.IconButtonPreview
import org.hisp.dhis.showcaseapp.screens.previews.SquareIconButtonPreview

@Composable
fun IconButtonScreen() {
    ColumnScreenContainer(title = ButtonScreens.ICON_BUTTON.label) {
        // SquareIconButton
        RowComponentContainer(
            title = "Square",
            content = {
                SquareIconButtonPreview()
                SquareIconButtonPreview(false)
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        // IconButton
        RowComponentContainer(
            title = "Standard",
            content = {
                IconButtonPreview()
                IconButtonPreview(false)
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        RowComponentContainer(
            title = "Filled",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    IconButtonPreview(true, IconButtonStyle.FILLED)
                    IconButtonPreview(false, IconButtonStyle.FILLED)
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        RowComponentContainer(
            title = "Tonal",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    IconButtonPreview(true, IconButtonStyle.TONAL)
                    IconButtonPreview(false, IconButtonStyle.TONAL)
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        RowComponentContainer(
            title = "Outlined",
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    IconButtonPreview(true, IconButtonStyle.OUTLINED)
                    IconButtonPreview(false, IconButtonStyle.OUTLINED)
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))
    }
}
