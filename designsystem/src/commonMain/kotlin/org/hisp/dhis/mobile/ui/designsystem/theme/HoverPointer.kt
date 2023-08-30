package org.hisp.dhis.mobile.ui.designsystem.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon

internal fun Modifier.hoverPointerIcon(
    enabled: Boolean,
    modifier: Modifier = Modifier
) = this.then(
    modifier.pointerHoverIcon(if (enabled) PointerIcon.Hand else PointerIcon.Default)
)

internal fun Modifier.textFieldHoverPointerIcon(
    enabled: Boolean,
    modifier: Modifier = Modifier
) = this.then(
    modifier.pointerHoverIcon(PointerIcon.Default, overrideDescendants = !enabled)
)
