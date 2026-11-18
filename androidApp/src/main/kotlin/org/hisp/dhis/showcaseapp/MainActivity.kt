package org.hisp.dhis.showcaseapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel
import androidx.compose.ui.platform.LocalResources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.graphics.Color
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val size = calculateWindowSizeClass(this)
            val res = LocalResources.current
            val isSystemDark = isSystemInDarkTheme()
            var isDarkMode by rememberSaveable { mutableStateOf(isSystemDark) }
            LaunchedEffect(isDarkMode) {
                // Bar scrims follow the app theme, not only the system one.
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { isDarkMode },
                    navigationBarStyle = SystemBarStyle.auto(LIGHT_NAVIGATION_SCRIM, DARK_NAVIGATION_SCRIM) { isDarkMode },
                )
            }
            SetStatusBarColor(isDarkMode)
            App(
                sizeClass = size,
                onDarkModeChanged = { isDarkMode = it },
                imageBitmapLoader = {
                    BitmapFactory
                        .decodeResource(
                            res,
                            android.R.drawable.ic_search_category_default,
                            BitmapFactory
                                .Options()
                                .also { it.inPreferredConfig = Bitmap.Config.ARGB_8888 },
                        ).asImageBitmap()
                },
                onLocationRequest = { locationQuery, locationSearchCallback ->

                    if (locationQuery.isNotBlank()) {
                        val fakeList =
                            buildList<LocationItemModel> {
                                repeat(20) {
                                    add(
                                        LocationItemModel.SearchResult(
                                            "Fake Location Title #$it",
                                            "Fake Location Address, Fake Country, Fake City",
                                            0.0,
                                            0.0,
                                        ),
                                    )
                                }
                            }
                        locationSearchCallback(fakeList)
                    } else {
                        locationSearchCallback(emptyList())
                    }
                },
            )
        }
    }
}

// Same scrim colors as androidx.activity's default edge-to-edge navigation bar style.
private val LIGHT_NAVIGATION_SCRIM = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val DARK_NAVIGATION_SCRIM = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

@Composable
fun SetStatusBarColor(isDarkMode: Boolean) {
    val context = LocalContext.current
    val window = (context as? ComponentActivity)?.window

    SideEffect {
        window?.let {
            WindowCompat.getInsetsController(it, it.decorView).apply {
                isAppearanceLightStatusBars = !isDarkMode
                isAppearanceLightNavigationBars = !isDarkMode
            }
        }
    }
}
