package org.hisp.dhis.mobile.ui.designsystem.component.table.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.DefaultValidator
import org.hisp.dhis.mobile.ui.designsystem.component.table.actions.Validator
import org.hisp.dhis.mobile.ui.designsystem.component.table.ui.compositions.LocalValidator
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.hisp.dhis.mobile.ui.designsystem.theme.LocalDHIS2ThemeProvided

/**
 * Composable function to provide table-related theme settings to the content.
 *
 * @param tableColors The colors to be used in the table.
 * @param tableDimensions The dimensions to be used in the table.
 * @param tableConfiguration The configuration settings for the table.
 * @param tableValidator The validator to be used for table validation.
 * @param content The composable content to which the theme settings will be applied.
 */
@Composable
fun TableTheme(
    tableColors: TableColors = LocalTableColors.current,
    tableDimensions: TableDimensions = LocalTableDimensions.current,
    tableConfiguration: TableConfiguration = LocalTableConfiguration.current,
    tableValidator: Validator = DefaultValidator(),
    content: @Composable () -> Unit,
) {
    // Default colors follow the current DHIS2 theme; custom colors are used as given.
    val resolvedTableColors = if (tableColors == DefaultTableColors) themedTableColors() else tableColors
    val tableContent: @Composable () -> Unit = {
        CompositionLocalProvider(
            LocalTableColors provides resolvedTableColors,
            LocalTableDimensions provides tableDimensions,
            LocalTableConfiguration provides tableConfiguration,
            LocalValidator provides tableValidator,
            content = content,
        )
    }
    // Only set up a DHIS2Theme when there is none above, so the app's light/dark choice is kept.
    if (LocalDHIS2ThemeProvided.current) {
        tableContent()
    } else {
        DHIS2Theme(content = tableContent)
    }
}

/**
 * Object to access the current table theme settings.
 */
object TableTheme {
    /**
     * The current table colors. Default colors follow the current DHIS2 theme, also when the table is not
     * wrapped in a [TableTheme].
     */
    val colors: TableColors
        @Composable
        get() = LocalTableColors.current.let { if (it == DefaultTableColors) themedTableColors() else it }

    /**
     * The current table dimensions.
     */
    val dimensions: TableDimensions
        @Composable
        get() = LocalTableDimensions.current

    /**
     * The current table configuration.
     */
    val configuration: TableConfiguration
        @Composable
        get() = LocalTableConfiguration.current

    /**
     * The current table selection.
     */
    val tableSelection
        @Composable
        get() = LocalTableSelection.current

    /**
     * The current table validator.
     */
    val validator
        @Composable
        get() = LocalValidator.current
}
