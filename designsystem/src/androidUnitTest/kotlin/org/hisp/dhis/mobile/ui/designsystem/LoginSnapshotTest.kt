package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputPassword
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputUser
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputPasswordModel
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputUserModel
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class LoginSnapshotTest {

    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchLoginScreen() {
        paparazzi.snapshot {
            ColumnScreenContainer(title = "Login", modifier = Modifier.padding(Spacing.Spacing10)) {
                InputQRCode(
                    "Server URL",
                    inputTextFieldValue = TextFieldValue("https://play.dhis2.org/40"),
                    onQRButtonClicked = {},
                    state = InputShellState.UNFOCUSED,
                    onValueChanged = { },
                )
                InputUser(
                    InputUserModel(
                        "Username",
                        inputTextFieldValue = TextFieldValue("android"),
                        state = InputShellState.UNFOCUSED,
                        onValueChanged = { },
                    ),
                )
                InputPassword(
                    InputPasswordModel(
                        "Password",
                        inputTextFieldValue = TextFieldValue("password"),
                        state = InputShellState.UNFOCUSED,
                        onValueChanged = { },
                    ),
                )

                Button(
                    style = ButtonStyle.FILLED,
                    onClick = {},
                    text = "Log In",
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Login,
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
