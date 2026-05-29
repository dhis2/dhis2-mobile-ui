package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.AgeInputType
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.DateTimeActionType
import org.hisp.dhis.mobile.ui.designsystem.component.InputAge
import org.hisp.dhis.mobile.ui.designsystem.component.InputDateTime
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.TimeUnitValues
import org.hisp.dhis.mobile.ui.designsystem.component.menu.MenuLeadingElement
import org.hisp.dhis.mobile.ui.designsystem.component.model.DateTransformation
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.ParameterInputStyle
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.ParameterSelectorItem
import org.hisp.dhis.mobile.ui.designsystem.component.parameter.model.ParameterSelectorItemModel
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputAgeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.InputDateTimeData
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputAgeState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberInputDateTimeState
import org.hisp.dhis.mobile.ui.designsystem.resource.provideDHIS2Icon
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect
import org.junit.Rule
import org.junit.Test

class ParameterSelectorSnapshotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @OptIn(ExperimentalResourceApi::class)
    @Test
    fun launchParameterSelectorSnapshot() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewContextConfigurationEffect()
            }
            ColumnScreenContainer {
                SubTitle("Parameter Selector Component  - Date (collapsed)")
                ParameterSelectorItem(
                    model = ParameterSelectorItemModel(
                        label = "Date parameter",
                        helper = "Optional",
                        inputField = {
                            InputDateTime(
                                state =
                                    rememberInputDateTimeState(
                                        inputDateTimeData =
                                            InputDateTimeData(
                                                title = "Date parameter",
                                                visualTransformation = DateTransformation(),
                                                actionType = DateTimeActionType.DATE_TIME,
                                                inputStyle = ParameterInputStyle(),
                                            ),
                                        inputTextFieldValue = TextFieldValue(""),
                                        inputState = InputShellState.UNFOCUSED,
                                    ),
                                onValueChanged = {
                                },
                            )
                        },
                        status = ParameterSelectorItemModel.Status.CLOSED,
                        onExpand = {},
                    ),
                )

                SubTitle("Parameter Selector Component  - Date (Expanded)")
                ParameterSelectorItem(
                    model = ParameterSelectorItemModel(
                        label = "Date parameter",
                        helper = "Optional",
                        inputField = {
                            InputDateTime(
                                state =
                                    rememberInputDateTimeState(
                                        inputDateTimeData =
                                            InputDateTimeData(
                                                title = "Date parameter",
                                                visualTransformation = DateTransformation(),
                                                actionType = DateTimeActionType.DATE_TIME,
                                                inputStyle = ParameterInputStyle(),
                                            ),
                                        inputTextFieldValue = TextFieldValue(""),
                                        inputState = InputShellState.FOCUSED,
                                    ),
                                onValueChanged = {
                                },
                            )
                        },
                        status = ParameterSelectorItemModel.Status.FOCUSED,
                        onExpand = {},
                    ),
                )

            }
        }
    }
}
