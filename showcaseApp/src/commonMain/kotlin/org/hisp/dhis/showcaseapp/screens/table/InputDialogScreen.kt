package org.hisp.dhis.showcaseapp.screens.table

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.AdditionalInfoItem
import org.hisp.dhis.mobile.ui.designsystem.component.Button
import org.hisp.dhis.mobile.ui.designsystem.component.ButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.CheckBoxData
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.IconButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBarData
import org.hisp.dhis.mobile.ui.designsystem.component.InputDialog
import org.hisp.dhis.mobile.ui.designsystem.component.InputMultiSelection
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoField
import org.hisp.dhis.mobile.ui.designsystem.component.InputYesNoFieldValues
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.hisp.dhis.showcaseapp.screens.previews.lorem
import org.hisp.dhis.showcaseapp.screens.previews.lorem_short
import org.hisp.dhis.showcaseapp.screens.previews.regularLegendList

@Composable
fun InputDialogScreen() {
    var showInputDialogWithContent by remember { mutableStateOf(false) }

    var inputValue1 by remember() {
        mutableStateOf(
            TextFieldValue("Label", selection = TextRange(5, 5)),
        )
    }

    var showInputDialogWithSmallContent by remember { mutableStateOf(false) }

    var inputValue5 by remember() {
        mutableStateOf(
            TextFieldValue("Label", selection = TextRange(5, 5)),
        )
    }

    var showInputDialogWithoutContent by remember { mutableStateOf(false) }

    var inputValue2 by remember() {
        mutableStateOf(
            TextFieldValue("Label", selection = TextRange(5, 5)),
        )
    }

    var showInputDialogWithSupportingText by remember { mutableStateOf(false) }

    var inputValue3 by remember() {
        mutableStateOf(
            TextFieldValue("Label", selection = TextRange(5, 5)),
        )
    }

    var showInputDialogWithError by remember { mutableStateOf(false) }

    var selectedItem by remember {
        mutableStateOf<InputYesNoFieldValues?>(null)
    }

    var showInputDialogWithSupportingTextAndLegend by remember { mutableStateOf(false) }

    var selectedItem2 by remember {
        mutableStateOf<InputYesNoFieldValues?>(null)
    }

    var showInputDialogWithBigOptionSet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color.White, Shape.LargeTop)
                .padding(horizontal = Spacing.Spacing16)
                .align(Alignment.TopCenter),
        ) {
            Title(
                text = "Input Data Entry",
            )
            Spacer(Modifier.size(Spacing.Spacing16))

            SubTitle(
                text = "Input dialog with content",
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Show Input Dialog with content",
                    onClick = {
                        showInputDialogWithContent = !showInputDialogWithContent
                    },
                )
                Text(" Value: ${inputValue1.text}")
            }

            SubTitle(
                text = "Input dialog with small content",
                modifier = Modifier.padding(bottom = Spacing.Spacing16),
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Show Input Dialog with small content",
                    onClick = {
                        showInputDialogWithSmallContent = !showInputDialogWithSmallContent
                    },
                )
                Text(" Value: ${inputValue5.text}")
            }

            SubTitle(
                text = "Input dialog without content",
                modifier = Modifier.padding(vertical = Spacing.Spacing16),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Show Input Dialog without content",
                    onClick = {
                        showInputDialogWithoutContent = !showInputDialogWithoutContent
                    },
                )
                Spacer(Modifier.size(Spacing.Spacing8))
                Text(" Value: ${inputValue2.text}")
            }

            SubTitle(
                text = "Input dialog yes/no with error",
                modifier = Modifier.padding(vertical = Spacing.Spacing16),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Show Input Dialog with error",
                    onClick = {
                        showInputDialogWithError = !showInputDialogWithError
                    },
                )
                Spacer(Modifier.size(Spacing.Spacing8))
            }

            SubTitle(
                text = "Input dialog with supporting text",
                modifier = Modifier.padding(vertical = Spacing.Spacing16),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Show Input Dialog with supporting text",
                    onClick = {
                        showInputDialogWithSupportingText = !showInputDialogWithSupportingText
                    },
                )
                Spacer(Modifier.size(Spacing.Spacing8))
            }

            SubTitle(
                text = "Input dialog with supporting text and legend",
                modifier = Modifier.padding(vertical = Spacing.Spacing16),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Show Input Dialog with supporting text and legend",
                    onClick = {
                        showInputDialogWithSupportingTextAndLegend = !showInputDialogWithSupportingTextAndLegend
                    },
                )
                Spacer(Modifier.size(Spacing.Spacing8))
            }

            SubTitle(
                text = "Input dialog with Multi select",
                modifier = Modifier.padding(vertical = Spacing.Spacing16),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Input dialog with Multi select",
                    onClick = {
                        showInputDialogWithBigOptionSet = !showInputDialogWithBigOptionSet
                    },
                )
                Spacer(Modifier.size(Spacing.Spacing8))
            }
        }

        AnimatedVisibility(
            visible = showInputDialogWithContent,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
        ) {
            val focusRequester = remember { FocusRequester() }

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                InputDialog(
                    input = {
                        InputText(
                            modifier = Modifier.focusRequester(focusRequester),
                            title = "Label",
                            inputTextFieldValue = inputValue1,
                            onValueChanged = {
                                if (it != null) {
                                    inputValue1 = it
                                }
                            },
                            state = InputShellState.FOCUSED,
                            onFocusChanged = {
                            },
                        )
                    },
                    details = {
                        InputDialogDetails()
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                                showInputDialogWithContent = false
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                        showInputDialogWithContent = false
                    },
                    modifier = Modifier,
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }

        AnimatedVisibility(
            visible = showInputDialogWithSmallContent,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
        ) {
            val focusRequester = remember { FocusRequester() }

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                InputDialog(
                    input = {
                        InputText(
                            modifier = Modifier.focusRequester(focusRequester),
                            title = "Label",
                            inputTextFieldValue = inputValue1,
                            onValueChanged = {
                                if (it != null) {
                                    inputValue5 = it
                                }
                            },
                            state = InputShellState.FOCUSED,
                            onFocusChanged = {
                            },
                        )
                    },
                    details = {
                        InputDialogDetailsSmall()
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                                showInputDialogWithSmallContent = false
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                        showInputDialogWithSmallContent = false
                    },
                    modifier = Modifier,
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }

        AnimatedVisibility(
            visible = showInputDialogWithoutContent,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
        ) {
            val focusRequester = remember { FocusRequester() }

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                InputDialog(
                    input = {
                        InputText(
                            modifier = Modifier.focusRequester(focusRequester),
                            title = "Label",
                            inputTextFieldValue = inputValue2,
                            onValueChanged = {
                                if (it != null) {
                                    inputValue2 = it
                                }
                            },
                            state = InputShellState.FOCUSED,
                            onFocusChanged = {
                            },
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                                showInputDialogWithoutContent = false
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                        showInputDialogWithoutContent = false
                    },
                    modifier = Modifier,
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }

        AnimatedVisibility(
            visible = showInputDialogWithError,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                InputDialog(
                    input = {
                        InputYesNoField(
                            title = "Label",
                            state = InputShellState.ERROR,
                            supportingText = listOf(SupportingTextData("Error text", SupportingTextState.ERROR)),
                            itemSelected = selectedItem,
                            onItemChange = {
                                selectedItem = it
                            },
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                                showInputDialogWithError = false
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                        showInputDialogWithError = false
                    },
                    modifier = Modifier,
                )
            }
        }

        AnimatedVisibility(
            visible = showInputDialogWithSupportingText,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
        ) {
            val focusRequester = remember { FocusRequester() }

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                InputDialog(
                    input = {
                        InputText(
                            modifier = Modifier.focusRequester(focusRequester),
                            title = "Label",
                            inputTextFieldValue = inputValue3,
                            onValueChanged = {
                                if (it != null) {
                                    inputValue3 = it
                                }
                            },
                            supportingText = listOf(
                                SupportingTextData(
                                    lorem,
                                    SupportingTextState.DEFAULT,
                                ),
                                SupportingTextData(
                                    "Supporting Text",
                                    SupportingTextState.ERROR,
                                ),
                                SupportingTextData(
                                    "Supporting Text",
                                    SupportingTextState.WARNING,
                                ),
                            ),
                            state = InputShellState.FOCUSED,
                            onFocusChanged = {
                            },
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Next",
                            onClick = {
                                showInputDialogWithSupportingText = false
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                        showInputDialogWithSupportingText = false
                    },
                    modifier = Modifier,
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }

        AnimatedVisibility(
            visible = showInputDialogWithSupportingTextAndLegend,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                InputDialog(
                    input = {
                        InputYesNoField(
                            title = "Label",
                            state = InputShellState.ERROR,
                            itemSelected = selectedItem,
                            onItemChange = {
                                selectedItem2 = it
                            },
                            supportingText = listOf(
                                SupportingTextData(
                                    lorem_short,
                                    SupportingTextState.DEFAULT,
                                ),

                            ),
                            legendData = LegendData(SurfaceColor.CustomGreen, "Legend", popUpLegendDescriptionData = regularLegendList),
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                                showInputDialogWithSupportingTextAndLegend = false
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                        showInputDialogWithSupportingTextAndLegend = false
                    },
                    modifier = Modifier,
                )
            }
        }

        AnimatedVisibility(
            visible = showInputDialogWithBigOptionSet,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 400),
            ),
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                InputDialog(
                    input = {
                        val multiSelect2Items = mutableStateListOf(
                            CheckBoxData(
                                uid = "uid-1",
                                checked = true,
                                enabled = true,
                                textInput = "Option 1",
                            ),
                            CheckBoxData(
                                uid = "uid-2",
                                checked = true,
                                enabled = true,
                                textInput = "Option 2",
                            ),
                            CheckBoxData(
                                uid = "uid-3",
                                checked = true,
                                enabled = true,
                                textInput = "Opt. 3",
                            ),
                            CheckBoxData(
                                uid = "uid-4",
                                checked = false,
                                enabled = true,
                                textInput = "Option 4",
                            ),
                            CheckBoxData(
                                uid = "uid-5",
                                checked = false,
                                enabled = true,
                                textInput = "Option 5",
                            ),
                            CheckBoxData(
                                uid = "uid-6",
                                checked = false,
                                enabled = true,
                                textInput = "Opt. 6",
                            ),
                            CheckBoxData(
                                uid = "uid-7",
                                checked = false,
                                enabled = true,
                                textInput = "Opt. 7",
                            ),
                        )
                        InputMultiSelection(
                            items = multiSelect2Items,
                            title = "Multi Select 2",
                            state = InputShellState.UNFOCUSED,
                            onItemsSelected = { selectedItems ->
                                selectedItems.forEach { selectedItem ->
                                    val index = multiSelect2Items.indexOfFirst { it.uid == selectedItem.uid }
                                    multiSelect2Items[index] = selectedItem
                                }
                            },
                            onClearItemSelection = {
                                multiSelect2Items.replaceAll { it.copy(checked = false) }
                            },
                            isRequired = false,
                            legendData = null,
                            supportingTextData = null,
                        )
                    },
                    actionButton = {
                        Button(
                            style = ButtonStyle.FILLED,
                            text = "Done",
                            onClick = {
                                showInputDialogWithBigOptionSet = false
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Done",
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    onDismiss = {
                        showInputDialogWithBigOptionSet = false
                    },
                    modifier = Modifier,
                )
            }
        }
    }
}

@Composable
fun InputDialogDetails() {
    Column(Modifier.padding(Spacing.Spacing0)) {
        Spacer(Modifier.size(Spacing.Spacing8))

        Card(
            shape = Shape.Large,
            modifier = Modifier.clip(Shape.Large)
                .background(color = SurfaceColor.Primary.copy(alpha = 0.2f)),

        ) {
            Column(
                modifier = Modifier.background(SurfaceColor.SurfaceBright)
                    .padding(Spacing.Spacing16),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Assignment,
                        contentDescription = "Done",
                        modifier = Modifier.padding(end = Spacing.Spacing16),
                    )
                    Text(
                        "Details",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.8f),
                    )

                    IconButton(
                        style = IconButtonStyle.TONAL,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowUp,
                                contentDescription = "Icon Button",
                            )
                        },
                        onClick = {
                        },
                    )
                }
                Spacer(Modifier.size(Spacing.Spacing10))

                Text(
                    "Label / Label",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextColor.OnPrimaryContainer,
                )
                Spacer(Modifier.size(Spacing.Spacing4))

                Row {
                    Text(
                        "Code: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurfaceLight,
                    )
                    Text(
                        "DE_359532 ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurface,
                    )
                }
                Spacer(Modifier.size(Spacing.Spacing4))

                Row {
                    Text(
                        "Data element id:  ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurfaceLight,
                    )
                    Text(
                        "fbfJHSPpUQD ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurface,
                    )
                }
                Spacer(Modifier.size(Spacing.Spacing4))
                Row {
                    Text(
                        "Category option combo ID: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurfaceLight,
                    )
                    Text(
                        "pq2X15kz2BY ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurface,
                    )
                }

                Spacer(Modifier.size(Spacing.Spacing16))
                InfoBar(
                    InfoBarData(
                        text = "Marked for follow up",
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Flag,
                                contentDescription = "not synced",
                                tint = SurfaceColor.CustomOrange,
                            )
                        },
                        color = TextColor.OnSurfaceLight,
                        backgroundColor = SurfaceColor.Surface,
                        actionText = "Remove",
                        onClick = {},
                    ),
                )
                Spacer(Modifier.size(Spacing.Spacing16))
            }
        }
        Spacer(Modifier.size(Spacing.Spacing8))
        Card(
            shape = Shape.Large,
            modifier = Modifier.clip(Shape.Large)
                .background(color = SurfaceColor.Primary.copy(alpha = 0.2f)),
        ) {
            Column(
                modifier = Modifier.background(SurfaceColor.SurfaceBright)
                    .padding(Spacing.Spacing16),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Message,
                        contentDescription = "Done",
                        modifier = Modifier.padding(end = Spacing.Spacing16),
                    )
                    Text(
                        "Comments",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.8f),
                    )

                    IconButton(
                        style = IconButtonStyle.TONAL,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowUp,
                                contentDescription = "Icon Button",
                            )
                        },
                        onClick = {
                        },
                    )
                }
                Spacer(Modifier.size(Spacing.Spacing10))
                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "@user"),
                        lastUpdated = "2 days ago",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = emptyList(),
                            syncProgressItem = AdditionalInfoItem(
                                value = "syncing",
                            ),
                        ),
                        description = ListCardDescriptionModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor inci"),
                    ),
                    onCardClick = {},
                )
                Spacer(Modifier.size(Spacing.Spacing16))

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "@user"),
                        lastUpdated = "2 days ago",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = emptyList(),
                            syncProgressItem = AdditionalInfoItem(
                                value = "syncing",
                            ),
                        ),
                        description = ListCardDescriptionModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor inci"),
                    ),
                    onCardClick = {},
                )
                Spacer(Modifier.size(Spacing.Spacing16))

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "@user"),
                        lastUpdated = "2 days ago",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = emptyList(),
                            syncProgressItem = AdditionalInfoItem(
                                value = "syncing",
                            ),
                        ),
                        description = ListCardDescriptionModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor inci"),
                    ),
                    onCardClick = {},
                )
                Spacer(Modifier.size(Spacing.Spacing16))

                ListCard(
                    listCardState = rememberListCardState(
                        title = ListCardTitleModel(text = "@user"),
                        lastUpdated = "2 days ago",
                        additionalInfoColumnState = rememberAdditionalInfoColumnState(
                            additionalInfoList = emptyList(),
                            syncProgressItem = AdditionalInfoItem(
                                value = "syncing",
                            ),
                        ),
                        description = ListCardDescriptionModel(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor inci"),
                    ),
                    onCardClick = {},
                )
                Spacer(Modifier.size(Spacing.Spacing16))

                Button(
                    style = ButtonStyle.TONAL,
                    text = "Add Comment",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Add Comment",
                        )
                    },
                )
            }
        }
        Spacer(Modifier.size(Spacing.Spacing4))
    }
}

@Composable
fun InputDialogDetailsSmall() {
    Column(modifier = Modifier.padding(Spacing.Spacing0)) {
        Spacer(Modifier.size(Spacing.Spacing8))

        Card(
            shape = Shape.Large,
            modifier = Modifier.clip(Shape.Large)
                .background(color = SurfaceColor.Primary.copy(alpha = 0.2f)),

        ) {
            Column(
                modifier = Modifier.background(SurfaceColor.SurfaceBright)
                    .padding(Spacing.Spacing16),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Assignment,
                        contentDescription = "Done",
                        modifier = Modifier.padding(end = Spacing.Spacing16),
                    )
                    Text(
                        "Details",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.8f),
                    )

                    IconButton(
                        style = IconButtonStyle.TONAL,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowUp,
                                contentDescription = "Icon Button",
                            )
                        },
                        onClick = {
                        },
                    )
                }
                Spacer(Modifier.size(Spacing.Spacing10))

                Text(
                    "Label / Label",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextColor.OnPrimaryContainer,
                )
                Spacer(Modifier.size(Spacing.Spacing4))

                Row {
                    Text(
                        "Code: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurfaceLight,
                    )
                    Text(
                        "DE_359532 ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurface,
                    )
                }
                Spacer(Modifier.size(Spacing.Spacing4))

                Row {
                    Text(
                        "Data element id:  ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurfaceLight,
                    )
                    Text(
                        "fbfJHSPpUQD ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurface,
                    )
                }
                Spacer(Modifier.size(Spacing.Spacing4))
                Row {
                    Text(
                        "Category option combo ID: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurfaceLight,
                    )
                    Text(
                        "pq2X15kz2BY ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextColor.OnSurface,
                    )
                }

                Spacer(Modifier.size(Spacing.Spacing16))
                InfoBar(
                    InfoBarData(
                        text = "Marked for follow up",
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Flag,
                                contentDescription = "not synced",
                                tint = SurfaceColor.CustomOrange,
                            )
                        },
                        color = TextColor.OnSurfaceLight,
                        backgroundColor = SurfaceColor.Surface,
                        actionText = "Remove",
                        onClick = {},
                    ),
                )
                Spacer(Modifier.size(Spacing.Spacing16))
            }
        }
        Spacer(Modifier.size(Spacing.Spacing4))
    }
}
