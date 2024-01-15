package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.component.WarningButton
import org.hisp.dhis.mobile.ui.designsystem.component.WarningButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun WarningButtonScreen() {
    ColumnComponentContainer {
        Title("Buttons")
        SubTitle("Filled")
        RowComponentContainer {
            WarningButton(text = "Label", style = WarningButtonStyle.FILLED) {}
            WarningButton(enabled = false, text = "Label", style = WarningButtonStyle.FILLED) {}
        }
        RowComponentContainer {
            WarningButton(
                text = "Label",
                style = WarningButtonStyle.FILLED,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Button",
                    )
                },
            ) {}
            WarningButton(
                enabled = false,
                text = "Label",
                style = WarningButtonStyle.FILLED,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Button",
                    )
                },
            ) {}
        }

        Spacer(Modifier.size(Spacing.Spacing18))
        SubTitle("Outlined")
        RowComponentContainer {
            WarningButton(text = "Label") {}
            WarningButton(enabled = false, text = "Label", style = WarningButtonStyle.OUTLINED) {}
        }
        RowComponentContainer {
            WarningButton(
                text = "Label",
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Button",
                    )
                },
            ) {}
            WarningButton(
                enabled = false,
                text = "Label",
                style = WarningButtonStyle.OUTLINED,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Button",
                    )
                },
            ) {}
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Text")
        RowComponentContainer {
            WarningButton(
                text = "Label",
                style = WarningButtonStyle.TEXT,
            ) {}
            WarningButton(
                enabled = false,
                text = "Label",
                style = WarningButtonStyle.TEXT,
            ) {}
        }
        RowComponentContainer {
            WarningButton(
                text = "Label",
                style = WarningButtonStyle.TEXT,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Button",
                    )
                },
            ) {}
            WarningButton(
                enabled = false,
                text = "Label",
                style = WarningButtonStyle.TEXT,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Button",
                    )
                },
            ) {}
        }
    }
}