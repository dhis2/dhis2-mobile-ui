package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.CustomIntentState
import org.hisp.dhis.mobile.ui.designsystem.component.InputCustomIntent
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.junit.Rule
import org.junit.Test

class InputCustomIntentTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchInputCustomIntent() {
        paparazzi.snapshot {
            val value = listOf("Option")
            val values = listOf("Option 1", "Option 2", "Option 3")

            ColumnScreenContainer("Custom Intent") {
                InputCustomIntent(
                    title = "Label",
                    buttonText = "Launch",
                    values = emptyList(),
                    onLaunch = { },
                    onClear = { },
                )

                InputCustomIntent(
                    title = "Label",
                    buttonText = "Launch",
                    customIntentState = CustomIntentState.LOADING,
                    values = value,
                    onLaunch = { },
                    onClear = { },
                )

                Spacer(Modifier.size(Spacing.Spacing18))
                InputCustomIntent(
                    title = "Label",
                    buttonText = "Launch",
                    values = value,
                    onLaunch = { },
                    onClear = { },
                    customIntentState = CustomIntentState.LOADED,
                )

                Spacer(Modifier.size(Spacing.Spacing18))
                InputCustomIntent(
                    title = "Label",
                    buttonText = "Launch",
                    values = values,
                    onLaunch = { },
                    onClear = { },
                    customIntentState = CustomIntentState.LOADED,
                )

                Spacer(Modifier.size(Spacing.Spacing18))
                InputCustomIntent(
                    title = "Label",
                    buttonText = "Launch",
                    values = value,
                    onLaunch = { },
                    onClear = { },
                    customIntentState = CustomIntentState.LOADED,
                    inputShellState = InputShellState.DISABLED,
                )

                Spacer(Modifier.size(Spacing.Spacing18))
                InputCustomIntent(
                    title = "Label",
                    buttonText = "Launch",
                    values = values,
                    onLaunch = { },
                    onClear = { },
                    customIntentState = CustomIntentState.LOADED,
                    inputShellState = InputShellState.DISABLED,
                )
            }
        }
    }
}
