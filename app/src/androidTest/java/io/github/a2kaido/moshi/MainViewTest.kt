package io.github.a2kaido.moshi

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.libraries.cloudtesting.screenshots.ScreenShotter
import io.github.a2kaido.moshi.ui.MainActivity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainViewTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    private var mIdlingResource: IdlingResource? = null

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity {
            mIdlingResource = it.getIdlingResource()
            IdlingRegistry.getInstance().register(mIdlingResource)
        }
    }

    @After
    fun tearDown() {
        mIdlingResource?.let {
            IdlingRegistry.getInstance().unregister(it)
        }
    }

    @Test
    fun useAppContext() {
        onView(withId(R.id.button_main)).check { view, _ ->
            view?.let {
                assertEquals("pikachu", (it as TextView).text)
            }
        }
    }
}
