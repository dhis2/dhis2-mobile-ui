import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.hisp.dhis.common.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
