package org.hisp.dhis.android

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import org.hisp.dhis.common.App

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val res = LocalContext.current.resources
            SetStatusBarColor()
            App(
                imageBitmapLoader = {
                    BitmapFactory.decodeResource(
                        res,
                        android.R.drawable.ic_search_category_default,
                        BitmapFactory.Options()
                            .also { it.inPreferredConfig = Bitmap.Config.ARGB_8888 },
                    ).asImageBitmap()
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
            it.statusBarColor = 0xFFE2F2FF.toInt()
        }
    }
}
