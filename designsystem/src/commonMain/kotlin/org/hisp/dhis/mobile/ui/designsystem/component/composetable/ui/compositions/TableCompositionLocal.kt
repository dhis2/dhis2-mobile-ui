package org.hisp.dhis.mobile.ui.designsystem.component.composetable.ui.compositions

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions.DefaultValidator
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions.TableInteractions
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions.TableResizeActions
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.actions.Validator
import org.hisp.dhis.mobile.ui.designsystem.component.composetable.model.TableCell

val LocalCurrentCellValue = compositionLocalOf<() -> String?> { { "" } }
val LocalUpdatingCell = compositionLocalOf<TableCell?> { null }
val LocalInteraction = compositionLocalOf<TableInteractions> { object : TableInteractions {} }
val LocalTableResizeActions =
    compositionLocalOf<TableResizeActions> { object : TableResizeActions {} }
val LocalValidator = staticCompositionLocalOf<Validator> { DefaultValidator() }
