package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.component.SectionSemantics.stateSemantic
import org.hisp.dhis.mobile.ui.designsystem.component.internal.bottomBorder
import org.hisp.dhis.mobile.ui.designsystem.resource.provideQuantityStringResource
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Color.Ash600
import org.hisp.dhis.mobile.ui.designsystem.theme.Shape
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing16
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing.Spacing24
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

enum class SectionState {
    OPEN, CLOSE, FIXED, NO_HEADER;

    fun getNextState(): SectionState {
        return when (this) {
            OPEN -> CLOSE
            CLOSE -> OPEN
            FIXED -> FIXED
            NO_HEADER -> NO_HEADER
        }
    }
}

@Composable
fun Section(
    modifier: Modifier = Modifier,
    isLastSection: Boolean = false,
    title: String,
    description: String?,
    completedFields: Int,
    totalFields: Int,
    state: SectionState,
    errorCount: Int,
    warningCount: Int,
    onNextSection: () -> Unit,
    onSectionClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    when (state) {
        SectionState.NO_HEADER -> SectionContent(spacedBy(Spacing24)) { content() }
        else -> SectionBlock(
            modifier = modifier,
            isLastSection = isLastSection,
            title = title,
            description = description,
            completedFields = completedFields,
            totalFields = totalFields,
            state = state,
            errorCount = errorCount,
            warningCount = warningCount,
            onNextSection = onNextSection,
            onSectionClick = onSectionClick,
        ) {
            content()
        }
    }
}

@Composable
internal fun SectionBlock(
    modifier: Modifier,
    isLastSection: Boolean,
    title: String,
    description: String?,
    completedFields: Int,
    totalFields: Int,
    state: SectionState,
    errorCount: Int,
    warningCount: Int,
    onNextSection: () -> Unit,
    onSectionClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    var sectionState by remember(state) { mutableStateOf(state) }

    val bottomPadding = when (sectionState) {
        SectionState.FIXED -> Spacing.Spacing40
        else -> Spacing16
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .run {
                if (sectionState != SectionState.FIXED) {
                    bottomBorder(1.dp, Ash600)
                } else {
                    this
                }
            }
            .padding(top = Spacing.Spacing8, bottom = bottomPadding),
        verticalArrangement = spacedBy(Spacing24),
    ) {
        SectionHeader(
            title = title,
            description = description,
            completedFields = completedFields,
            totalFields = totalFields,
            sectionState = sectionState,
            errorCount = errorCount,
            warningCount = warningCount,
            onSectionClick = {
                sectionState = sectionState.getNextState()
                onSectionClick()
            },
        )
        AnimatedVisibility(
            visible = sectionState != SectionState.CLOSE,
            enter = expandVertically(expandFrom = Alignment.CenterVertically),
            exit = shrinkVertically(shrinkTowards = Alignment.CenterVertically),
        ) {
            SectionContent(verticalArrangement = spacedBy(Spacing24)) { content() }
        }
        if (!isLastSection && sectionState == SectionState.OPEN) {
            NextSectionButton(
                modifier = Modifier.align(Alignment.End),
            ) { onNextSection() }
        }
    }
}

@Composable
internal fun SectionContent(
    verticalArrangement: Arrangement.HorizontalOrVertical,
    content:
    @Composable()
    (ColumnScope.() -> Unit),
) {
    Column(
        modifier = Modifier
            .testTag(SectionTestTag.CONTENT)
            .fillMaxWidth(),
        verticalArrangement = verticalArrangement,
    ) {
        content()
    }
}

@Composable
internal fun SectionHeader(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    completedFields: Int,
    totalFields: Int,
    sectionState: SectionState,
    errorCount: Int,
    warningCount: Int,
    showFieldsLabel: String = provideStringResource("show_fields"),
    hideFieldsLabel: String = provideStringResource("hide_fields"),
    onSectionClick: () -> Unit,
) {
    val sectionStateButtonLabel by remember(sectionState) {
        derivedStateOf {
            when (sectionState) {
                SectionState.OPEN -> hideFieldsLabel
                SectionState.CLOSE -> showFieldsLabel
                SectionState.FIXED -> ""
                SectionState.NO_HEADER -> ""
            }
        }
    }

    val sectionStateButtonIcon by remember(sectionState) {
        derivedStateOf {
            when (sectionState) {
                SectionState.OPEN -> Icons.Filled.KeyboardArrowUp
                SectionState.CLOSE -> Icons.Filled.KeyboardArrowDown
                SectionState.FIXED -> null
                SectionState.NO_HEADER -> null
            }
        }
    }

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .testTag(SectionTestTag.HEADER)
            .semantics {
                stateSemantic = sectionState
            }
            .fillMaxWidth()
            .background(color = Color.White, Shape.Small)
            .clip(Shape.Small)
            .let {
                if (sectionState == SectionState.FIXED) {
                    it
                } else {
                    it.clickable(
                        role = Role.Button,
                        interactionSource = interactionSource,
                        indication = rememberRipple(
                            color = SurfaceColor.Primary,
                        ),
                    ) {
                        onSectionClick()
                    }
                }
            }
            .padding(vertical = Spacing.Spacing8),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                SectionTitle(
                    modifier.fillMaxWidth()
                        .testTag(SectionTestTag.TITLE),
                    title = title,
                )
                description?.let {
                    SupportingText(
                        modifier = Modifier.testTag(SectionTestTag.DESCRIPTION),
                        text = it,
                        maxLines = 2,
                        paddingValues = PaddingValues(Spacing.Spacing0),
                        onNoInteraction = {
                            Pair(interactionSource, onSectionClick)
                        },
                    )
                }
            }

            CompletionLabel(
                modifier = Modifier.testTag(SectionTestTag.FIELD_PROGRESS),
                completedFields = completedFields,
                totalFields = totalFields,
            )
        }

        Spacer(modifier = Modifier.size(Spacing.Spacing8))
        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(Spacing.Spacing8),
        ) {
            if (sectionState != SectionState.FIXED) {
                StateIndicator(
                    label = sectionStateButtonLabel,
                    icon = sectionStateButtonIcon,
                )
            }
            if (errorCount > 0) {
                Tag(
                    modifier = Modifier.testTag(SectionTestTag.ERROR_LABEL),
                    label = provideQuantityStringResource("error", errorCount),
                    type = TagType.ERROR,
                )
            }
            if (warningCount > 0) {
                Tag(
                    modifier = Modifier.testTag(SectionTestTag.WARNING_LABEL),
                    label = provideQuantityStringResource("warning", warningCount),
                    type = TagType.WARNING,
                )
            }
        }
    }
}

@Composable
internal fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = TextColor.OnSurface,
    )
}

@Composable
internal fun CompletionLabel(
    modifier: Modifier = Modifier,
    completedFields: Int,
    totalFields: Int,
) {
    Box(
        modifier = Modifier.padding(
            start = Spacing.Spacing8,
            top = Spacing.Spacing4,
            bottom = Spacing.Spacing4,
        ),
    ) {
        Text(
            modifier = modifier,
            text = "$completedFields/$totalFields",
            style = MaterialTheme.typography.bodyMedium,
            color = TextColor.OnSurfaceLight,
        )
    }
}

@Composable
internal fun StateIndicator(label: String, icon: ImageVector?) {
    Row(
        modifier = Modifier
            .testTag(SectionTestTag.STATE_LABEL)
            .requiredHeight(20.dp)
            .padding(end = Spacing.Spacing4),
        horizontalArrangement = spacedBy(Spacing.Spacing8),
    ) {
        icon?.let {
            Icon(imageVector = it, "Icon Button", tint = SurfaceColor.Primary)
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = SurfaceColor.Primary,
        )
    }
}

@Composable
internal fun NextSectionButton(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        style = ButtonStyle.TEXT,
        text = provideStringResource("action_next"),
        icon = {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Icon Button",
            )
        },
        onClick = onClick,
    )
}

internal object SectionTestTag {
    const val CONTENT = "CONTENT"
    const val HEADER = "HEADER"
    const val TITLE = "TITLE"
    const val DESCRIPTION = "DESCRIPTION"
    const val FIELD_PROGRESS = "FIELD_PROGRESS"
    const val STATE_LABEL = "STATE_LABEL"
    const val ERROR_LABEL = "ERROR_LABEL"
    const val WARNING_LABEL = "WARNING_LABEL"
}

internal object SectionSemantics {
    val State = SemanticsPropertyKey<SectionState>("STATE")
    var SemanticsPropertyReceiver.stateSemantic by State
}
