package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputAgeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputAgeState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.Rule
import org.junit.Test

class InputAgeSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun launchInputAgeSnapshot() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewContextConfigurationEffect()
            }
            ColumnScreenContainer {
                SubTitle("Input Age Component - Idle")
                InputAge(
                    state =
                        rememberInputAgeState(
                            inputAgeData =
                                InputAgeData(
                                    title = "Label",
                                ),
                        ),
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Idle Disabled")
                InputAge(
                    state =
                        rememberInputAgeState(
                            inputAgeData =
                                InputAgeData(
                                    title = "Label",
                                ),
                            inputState = InputShellState.DISABLED,
                        ),
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Invalid Date Of Birth")
                InputAge(
                    state =
                        rememberInputAgeState(
                            inputAgeData =
                                InputAgeData(
                                    title = "Label",
                                ),
                            inputType =
                                AgeInputType.DateOfBirth(
                                    TextFieldValue("01011985"),
                                ),
                            inputState = InputShellState.DISABLED,
                        ),
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Date Of Birth")
                InputAge(
                    state =
                        rememberInputAgeState(
                            inputAgeData =
                                InputAgeData(
                                    title = "Label",
                                ),
                            inputType =
                                AgeInputType.DateOfBirth(
                                    TextFieldValue("1991-11-27"),
                                ),
                            inputState = InputShellState.DISABLED,
                        ),
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Date Of Birth Required Error")
                InputAge(
                    state =
                        rememberInputAgeState(
                            inputAgeData =
                                InputAgeData(
                                    title = "Label",
                                ),
                            inputType =
                                AgeInputType.DateOfBirth(
                                    TextFieldValue("010"),
                                ),
                            inputState = InputShellState.ERROR,
                        ),
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Age Disabled")
                InputAge(
                    state =
                        rememberInputAgeState(
                            inputAgeData =
                                InputAgeData(
                                    title = "Label",
                                ),
                            inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                            inputState = InputShellState.DISABLED,
                        ),
                    onValueChanged = {
                    },
                )

                SubTitle("Input Age Component - Age Required Error")
                InputAge(
                    state =
                        rememberInputAgeState(
                            inputAgeData =
                                InputAgeData(
                                    title = "Label",
                                ),
                            inputType = AgeInputType.Age(value = TextFieldValue("56"), unit = TimeUnitValues.YEARS),
                            inputState = InputShellState.ERROR,
                        ),
                    onValueChanged = {
                    },
                )
            }
        }
    }
}
