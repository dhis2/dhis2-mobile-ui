package org.hisp.dhis.common.screens.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentItemContainer
import org.hisp.dhis.mobile.ui.designsystem.component.TextButtonSelector
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource

@Composable
fun ButtonBlockScreen() {
    ColumnComponentContainer(title = ButtonScreens.BUTTON_BLOCK.label) {
        ColumnComponentItemContainer("One button style") {
            ButtonBlock(
                primaryButton = {
                    Button(
                        style = ButtonStyle.KEYBOARDKEY,
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
        }

        ColumnComponentItemContainer("Two button style") {
            ButtonBlock(
                primaryButton = {
                    Button(
                        style = ButtonStyle.KEYBOARDKEY,
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
                secondaryButton = {
                    Button(
                        style = ButtonStyle.KEYBOARDKEY,
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
        }

        Title("Text Button Selectors")

        ColumnComponentItemContainer("Enabled") {
            TextButtonSelector(
                firstOptionText = provideStringResource("date_birth"),
                onClickFirstOption = {},
                middleText = provideStringResource("or"),
                secondOptionText = provideStringResource("age"),
                onClickSecondOption = {},
            )
        }

        ColumnComponentItemContainer("Disabled") {
            TextButtonSelector(
                enabled = false,
                firstOptionText = provideStringResource("date_birth"),
                onClickFirstOption = {},
                middleText = provideStringResource("or"),
                secondOptionText = provideStringResource("age"),
                onClickSecondOption = {},
            )
        }
    }
}
