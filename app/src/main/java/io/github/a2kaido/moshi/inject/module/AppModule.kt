package io.github.a2kaido.moshi.inject.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.a2kaido.moshi.ui.MainActivity

@Module
interface AppModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}