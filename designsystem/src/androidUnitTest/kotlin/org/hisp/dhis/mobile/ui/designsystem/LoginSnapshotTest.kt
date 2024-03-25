package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputChip
import org.hisp.dhis.mobile.ui.designsystem.component.InputPassword
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputUser
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputPasswordModel
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputUserModel
import org.junit.Rule
import org.junit.Test

class LoginSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchLoginScreen() {
        paparazzi.snapshot {
            ColumnComponentContainer(title = "Login") {

                InputQRCode(
                    "Server URL",
                    inputTextFieldValue = TextFieldValue("https://play.dhis2.org/40"),
                    onQRButtonClicked = {},
                    state = InputShellState.UNFOCUSED,
                    onValueChanged = {  },
                )
                InputUser(
                    InputUserModel(
                        "Username",
                        inputTextFieldValue = TextFieldValue("android"),
                        state = InputShellState.UNFOCUSED,
                        onValueChanged = {  },
                    ),
                )
                InputPassword(
                    InputPasswordModel(
                        "Password",
                        inputTextFieldValue = TextFieldValue("password"),
                        state = InputShellState.UNFOCUSED,
                        onValueChanged = {  },
                    ),
                )

                Button(
                    style = ButtonStyle.FILLED,
                    onClick = {},
                    text = "Log In",
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Login,
                            contentDescription = "Login button",

                            )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,

                    )
            }
        }
    }
}
