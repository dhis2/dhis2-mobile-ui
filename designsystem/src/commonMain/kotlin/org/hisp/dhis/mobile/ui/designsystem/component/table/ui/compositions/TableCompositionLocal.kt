package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.DefaultValidator
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableInteractions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.Validator
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell

/**
 * Composition local for the current cell value.
 */
val LocalCurrentCellValue = compositionLocalOf<() -> String?> { { "" } }

/**
 * Composition local for the currently updating cell.
 */
val LocalUpdatingCell = compositionLocalOf<TableCell?> { null }

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
