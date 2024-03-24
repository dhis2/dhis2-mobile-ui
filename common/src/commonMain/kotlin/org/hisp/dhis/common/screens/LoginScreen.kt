package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputPassword
import org.hisp.dhis.mobile.ui.designsystem.component.InputPasswordModel
import org.hisp.dhis.mobile.ui.designsystem.component.InputQRCode
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputUser

@Composable
fun LoginScreen() {
    ColumnComponentContainer(title = "Legend") {
        var inputText1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        InputQRCode("Server URL", inputTextFieldValue = TextFieldValue(""), onQRButtonClicked = {}, state = InputShellState.UNFOCUSED)
        InputUser("Username", inputTextFieldValue = TextFieldValue(""), onActionCLicked = {}, state = InputShellState.UNFOCUSED)
        InputPassword(
            InputPasswordModel(
                "Password",
                inputTextFieldValue = inputText1,
                state = InputShellState.UNFOCUSED,
                onValueChanged = { inputText1 = it ?: TextFieldValue() },
            ),
        )
    }
}
