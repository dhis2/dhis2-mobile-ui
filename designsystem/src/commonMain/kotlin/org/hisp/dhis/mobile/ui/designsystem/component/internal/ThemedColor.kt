package org.hisp.dhis.mobile.ui.designsystem.component.internal

import androidx.compose.ui.graphics.Color

/**
 * Returns [themed] when this color is still the light palette [default] of a public model, so that defaults
 * follow the current theme while colors set explicitly by consumers are kept.
 */
internal fun Color.themedIfDefault(
    default: Color,
    themed: Color,
): Color = if (this == default) themed else this
