package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import org.hisp.dhis.mobileui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobileui.designsystem.component.EmptyInput
import org.hisp.dhis.mobileui.designsystem.component.RowComponentContainer

@Composable
fun FormsComponentsScreen() {
    ColumnComponentContainer(
        title = "Components ",
        content = {
            RowComponentContainer(
                title = "Empty input",
                content = {
                    EmptyInput()
                }
            )
        }
    )
}
