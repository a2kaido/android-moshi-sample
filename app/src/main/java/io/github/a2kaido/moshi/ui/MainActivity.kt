package io.github.a2kaido.moshi.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.IdlingResource
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.a2kaido.moshi.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main.setOnClickListener {
            startActivity(Intent(this, ScreenshotActivity::class.java))
        }

        viewModel.poke.observe(this, Observer {
            button_main.text = it
        })

        viewModel.getPoke("pikachu")
    }

    @VisibleForTesting
    fun getIdlingResource(): IdlingResource {
        return viewModel.getIdlingResource()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
