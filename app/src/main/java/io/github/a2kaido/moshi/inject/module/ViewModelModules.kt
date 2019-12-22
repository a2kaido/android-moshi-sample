package io.github.a2kaido.moshi.inject.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.github.a2kaido.moshi.ui.MainViewModel
import io.github.a2kaido.moshi.ui.repository.PokeRepository

@Module
class ViewModelModules {

    @Provides
    fun provideMainViewModelFactory(pokeRepository: PokeRepository): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(pokeRepository) as T
            }
        }
    }
}