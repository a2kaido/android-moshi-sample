package io.github.a2kaido.moshi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.a2kaido.moshi.R
import kotlinx.android.synthetic.main.activity_screenshot.*

class ScreenshotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screenshot)

        screenshot_button.setOnClickListener {
            ScreenshotDialogFragment().show(supportFragmentManager, null)
        }
    }
}
