package org.hisp.dhis.android

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import org.hisp.dhis.common.App

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val res = LocalContext.current.resources
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
