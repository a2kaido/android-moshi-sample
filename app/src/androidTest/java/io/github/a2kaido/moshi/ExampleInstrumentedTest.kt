package io.github.a2kaido.moshi

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import androidx.test.runner.screenshot.Screenshot
import io.github.a2kaido.moshi.ui.ScreenshotActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.WRITE_EXTERNAL_STORAGE"
    )

    @get:Rule
    val activityScenarioRule = activityScenarioRule<ScreenshotActivity>()

    @Test
    fun useAppContext() {
        onView(withId(R.id.screenshot_button)).perform(click())

        Thread.sleep(1000)

        getActivity()?.let {
            // ScreenShotter2.takeScreenshot("dialog", it, getDecorView(it))
            com.facebook.testing.screenshot.Screenshot.snapActivity(it).record()
            Screenshot.capture().process(
                setOf(
                    Sc()
                )
            )
        }
    }

    private fun getDecorView(activity: Activity): View {
        return getDialog(activity)
            .dialog!!.window!!.decorView.rootView
    }

    private fun getDialog(activity: Activity): DialogFragment {
        return ((activity as AppCompatActivity).supportFragmentManager.fragments[0] as DialogFragment)
    }

    private fun getActivity(): Activity? {
        var activity: Activity? = null
        activityScenarioRule.scenario.onActivity {
            activity = it
        }
        return activity
    }
}

class Sc : BasicScreenCaptureProcessor() {

    init {
        mTag = "Sc"
        mFileNameDelimiter = "-"
        mDefaultFilenamePrefix = "screenshot"
        mDefaultScreenshotPath = File("/sdcard/screenshots/")
    }
}
