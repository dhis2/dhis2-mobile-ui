package org.hisp.dhis.showcaseapp.screens.buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Switch

@Composable
fun SwitchScreen() {
    ColumnScreenContainer(title = ButtonScreens.SWITCH.label) {
        ColumnComponentContainer("Toggled enabled and disabled switch") {
            var switchOne by remember { mutableStateOf(true) }
            var switchTwo by remember { mutableStateOf(true) }
            RowComponentContainer {
                Switch(isChecked = switchOne, onCheckedChange = { switchOne = !it })
                Switch(isChecked = switchTwo, onCheckedChange = { switchTwo = !it }, enabled = false)
            }
        }

        ColumnComponentContainer("Unselected enabled and disabled switch") {
            var switchThree by remember { mutableStateOf(false) }
            var switchFour by remember { mutableStateOf(false) }
            RowComponentContainer {
                Switch(isChecked = switchThree, onCheckedChange = { switchThree = !it })
                Switch(isChecked = switchFour, onCheckedChange = { switchFour = !it }, enabled = false)
            }
        }
    }
}
