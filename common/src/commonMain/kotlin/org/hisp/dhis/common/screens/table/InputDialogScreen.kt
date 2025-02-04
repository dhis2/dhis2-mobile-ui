package org.hisp.dhis.common.screens.table

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
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.ArrowDropUp
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
import org.hisp.dhis.mobile.ui.designsystem.component.IconButton
import org.hisp.dhis.mobile.ui.designsystem.component.IconButtonStyle
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBar
import org.hisp.dhis.mobile.ui.designsystem.component.InfoBarData
import org.hisp.dhis.mobile.ui.designsystem.component.InputDialog
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.InputText
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardDescriptionModel
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberAdditionalInfoColumnState
import org.hisp.dhis.mobile.ui.designsystem.component.state.rememberListCardState
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputDialogScreen() {
    var showTextInputDataEntry by remember { mutableStateOf(false) }

    var inputValue1 by remember() {
        mutableStateOf(
            TextFieldValue("Label", selection = TextRange(5, 5)),
        )
    }
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
                modifier = Modifier.padding(vertical = Spacing.Spacing16),
            )
            Spacer(Modifier.size(Spacing.Spacing16))

            SubTitle(
                text = "Input dialog with content",
                modifier = Modifier.padding(vertical = Spacing.Spacing16),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    style = ButtonStyle.FILLED,
                    text = "Show Input Text Data Entry",
                    onClick = {
                        showTextInputDataEntry = !showTextInputDataEntry
                    },
                )
                Spacer(Modifier.size(Spacing.Spacing8))
                Text(" Value: ${inputValue1.text}")
            }
        }

        AnimatedVisibility(
            visible = showTextInputDataEntry,
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
                                showTextInputDataEntry = false
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
                        showTextInputDataEntry = false
                    },
                    modifier = Modifier,
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            }
        }
    }
}

@Composable
fun InputDialogDetails() {
    Column {
        Spacer(Modifier.size(Spacing.Spacing10))

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
                        modifier = Modifier.padding(Spacing.Spacing16),
                    )
                    Text(
                        "Details",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.7f),
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
                                tint = SurfaceColor.Warning,
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
        Spacer(Modifier.size(Spacing.Spacing10))
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
                        modifier = Modifier.padding(Spacing.Spacing16),
                    )
                    Text(
                        "Comments",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    IconButton(
                        style = IconButtonStyle.TONAL,
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.ArrowDropUp,
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
    }
}
