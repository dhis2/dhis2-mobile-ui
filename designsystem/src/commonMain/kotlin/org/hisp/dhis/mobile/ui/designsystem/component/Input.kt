package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

@Composable
fun InputText(
    title: String,
    state: MutableState<InputShellState> = mutableStateOf(InputShellState.UNFOCUSED),
    supportingText: List<SupportingTextData>? = null,
    legendText: String? = null,
    inputText: String = "",
    modifier: Modifier = Modifier
) {
    var inputValue by rememberSaveable { mutableStateOf(inputText) }
    var deleteButtonIsVisible by remember { mutableStateOf(false) }
    InputShell(
        modifier = modifier,
        title = title,
        primaryButton = {
            if (deleteButtonIsVisible) {
                IconButton(
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button"
                        )
                    },
                    onClick = {
                        inputValue = ""
                        deleteButtonIsVisible = false
                    },
                    enabled = state.value != InputShellState.DISABLED
                )
            }
        },
        state = state.value,
        legend = {
            legendText?.let {
                Legend(SurfaceColor.CustomGreen, legendText) {}
            }
        },
        supportingText = {
            supportingText?.forEach { label -> SupportingText(label.text, label.state) }
        },
        inputField = {
            BasicInput(inputText = inputValue, onInputChanged = {
                inputValue = it
                deleteButtonIsVisible = inputValue.isNotEmpty()
            })
        }
    )
}
