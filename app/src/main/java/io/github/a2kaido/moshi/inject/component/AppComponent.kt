package io.github.a2kaido.moshi.inject.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.a2kaido.moshi.App
import io.github.a2kaido.moshi.inject.module.AppModule
import io.github.a2kaido.moshi.inject.module.DataModules
import io.github.a2kaido.moshi.inject.module.ViewModelModules
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DataModules::class,
        ViewModelModules::class
    ]
)
interface AppComponent {

    fun inject(app: App)
}
