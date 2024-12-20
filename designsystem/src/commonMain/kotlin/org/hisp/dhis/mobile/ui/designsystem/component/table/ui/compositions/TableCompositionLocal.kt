package org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.DefaultValidator
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableInteractions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.TableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.Validator
import org.hisp.dhis.mobile.ui.designsystem.component.table.model.TableCell

val LocalCurrentCellValue = compositionLocalOf<() -> String?> { { "" } }
val LocalUpdatingCell = compositionLocalOf<TableCell?> { null }
val LocalInteraction = compositionLocalOf<TableInteractions> { object : TableInteractions {} }
val LocalTableResizeActions =
    compositionLocalOf<TableResizeActions> { object : TableResizeActions {} }
val LocalValidator = staticCompositionLocalOf<Validator> { DefaultValidator() }
