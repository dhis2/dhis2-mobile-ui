package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.previews.IconButtonPreview
import org.hisp.dhis.common.screens.previews.SquareIconButtonPreview
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.IconButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Title

@Composable
fun IconButtonScreen() {
    ColumnComponentContainer(
        content = {
            // SquareIconButton
            Title("Icon Buttons")
            RowComponentContainer(
                title = "Square",
                content = {
                    SquareIconButtonPreview()
                    SquareIconButtonPreview(false)
                },
            )

            // IconButton
            RowComponentContainer(
                title = "Standard",
                content = {
                    IconButtonPreview()
                    IconButtonPreview(false)
                },
            )

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
        },
    )
}
