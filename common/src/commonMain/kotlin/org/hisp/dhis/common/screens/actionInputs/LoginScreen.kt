package org.hisp.dhis.common.screens.actionInputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputPassword
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputUser
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputPasswordModel
import org.hisp.dhis.mobile.ui.designsystem.component.model.InputUserModel

@Composable
fun LoginScreen() {
    ColumnComponentContainer(title = ActionInputs.LOGIN.label) {
        var server by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("https://play.dhis2.org/40")) }
        var userName by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("android")) }
        var password by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("password")) }

        InputQRCode(
            "Server URL",
            inputTextFieldValue = server,
            onQRButtonClicked = {},
            state = InputShellState.UNFOCUSED,
            onValueChanged = { server = it ?: TextFieldValue() },
        )
        InputUser(
            InputUserModel(
                "Username",
                inputTextFieldValue = userName,
                state = InputShellState.UNFOCUSED,
                onValueChanged = { userName = it ?: TextFieldValue() },
            ),
        )
        InputPassword(
            InputPasswordModel(
                "Password",
                inputTextFieldValue = password,
                state = InputShellState.UNFOCUSED,
                onValueChanged = { password = it ?: TextFieldValue() },
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
            enabled = (password.text.isNotEmpty() && userName.text.isNotEmpty() && server.text.isNotEmpty()),
        )
    }
}
