package org.hisp.dhis.common.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.RowComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Switch
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

@Composable
fun InputYesOnlyScreen() {
    ColumnComponentContainer {
        Title("Switches")
        SubTitle("Toggled enabled and disabled switch")
        var switchOne by remember { mutableStateOf(true) }
        var switchTwo by remember { mutableStateOf(true) }
        var switchThree by remember { mutableStateOf(false) }
        var switchFour by remember { mutableStateOf(false) }

        SubTitle("Toggled enabled and disabled switch")

        RowComponentContainer {
            Switch(isChecked = switchOne, onCheckedChange = { switchOne = !it })
            Switch(isChecked = switchTwo, onCheckedChange = { switchTwo = !it }, enabled = false)
        }

        Spacer(Modifier.size(Spacing.Spacing6))
        SubTitle("Untoggled enabled and disabled switch")

        RowComponentContainer {
            Switch(isChecked = switchThree, onCheckedChange = { switchThree = !it })
            Switch(isChecked = switchFour, onCheckedChange = { switchFour = !it }, enabled = false)
        }
    }
}
