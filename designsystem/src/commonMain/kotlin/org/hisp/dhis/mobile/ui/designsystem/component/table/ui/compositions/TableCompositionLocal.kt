package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.DefaultValidator
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableInteractions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.Validator

/**
 * Composition local for table interactions.
 */
val LocalInteraction = compositionLocalOf<TableInteractions> { object : TableInteractions {} }

/**
 * Composition local for table resize actions.
 */
val LocalTableResizeActions =
    compositionLocalOf<TableResizeActions> { object : TableResizeActions {} }

/**
 * Static composition local for the validator.
 */
val LocalValidator = staticCompositionLocalOf<Validator> { DefaultValidator() }
