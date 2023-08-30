package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonBlock
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer

@Composable
fun ButtonBlockScreen() {
    ColumnComponentContainer {
        Text("One button style")

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
        Text("Two button style")
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
}
