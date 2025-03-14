package org.hisp.dhis.showcaseapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import org.hisp.dhis.showcaseapp.App
import org.hisp.dhis.mobile.ui.designsystem.component.model.LocationItemModel

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val size = calculateWindowSizeClass(this)
            val res = LocalContext.current.resources
            SetStatusBarColor()
            App(
                sizeClass = size,
                imageBitmapLoader = {
                    BitmapFactory.decodeResource(
                        res,
                        android.R.drawable.ic_search_category_default,
                        BitmapFactory.Options()
                            .also { it.inPreferredConfig = Bitmap.Config.ARGB_8888 },
                    ).asImageBitmap()
                },
                onLocationRequest = { locationQuery, locationSearchCallback ->

                    if (locationQuery.isNotBlank()) {
                        val fakeList = buildList<LocationItemModel> {
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

@Composable
fun SetStatusBarColor() {
    val context = LocalContext.current
    val window = (context as? ComponentActivity)?.window

    SideEffect {
        window?.let {
            WindowCompat.getInsetsController(it, it.decorView).apply {
                isAppearanceLightStatusBars = true
            }
        }
    }
}