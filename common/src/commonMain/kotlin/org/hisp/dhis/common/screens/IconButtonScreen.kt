package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.hisp.dhis.common.screens.previews.IconButtonPreview
import org.hisp.dhis.common.screens.previews.SquareIconButtonPreview
import org.hisp.dhis.mobileui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobileui.designsystem.component.IconButtonStyle
import org.hisp.dhis.mobileui.designsystem.component.RowComponentContainer

@Composable
fun IconButtonScreen() {
    ColumnComponentContainer(
        title = "Icon Buttons ",
        content = {
            // SquareIconButton
            RowComponentContainer(
                title = "Square Icon Button",
                content = {
                    SquareIconButtonPreview()
                    SquareIconButtonPreview(false)
                }
            )

            // IconButton
            Text("Icon Buttons")
            RowComponentContainer(
                title = "Standard",
                content = {
                    IconButtonPreview()
                    IconButtonPreview(false)
                }
            )

            RowComponentContainer(
                title = "Filled",
                content = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        IconButtonPreview(true, IconButtonStyle.FILLED)
                        IconButtonPreview(false, IconButtonStyle.FILLED)
                    }
                }
            )
            RowComponentContainer(
                title = "Tonal",
                content = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        IconButtonPreview(true, IconButtonStyle.TONAL)
                        IconButtonPreview(false, IconButtonStyle.TONAL)
                    }
                }
            )
            RowComponentContainer(
                title = "Outlined",
                content = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        IconButtonPreview(true, IconButtonStyle.OUTLINED)
                        IconButtonPreview(false, IconButtonStyle.OUTLINED)
                    }
                }
            )
        }
    )
}
