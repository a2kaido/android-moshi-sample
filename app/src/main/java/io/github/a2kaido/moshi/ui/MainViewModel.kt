package io.github.a2kaido.moshi.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.a2kaido.moshi.ui.repository.PokeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val pokeRepository: PokeRepository,
    private val idlingResource: SimpleIdlingResource
) : ViewModel() {

    private val _poke = MutableLiveData<String>()
    val poke: LiveData<String>
        get() = _poke

    fun getPoke(name: String) {
        idlingResource.setIdleState(false)

        viewModelScope.launch {
            runCatching {
                delay(3000)
                pokeRepository.getPoke(name)
            }.onSuccess {
                _poke.value = it.name
                idlingResource.setIdleState(true)
            }.onFailure {
                println(it.localizedMessage)
            }
        }
    }

    @VisibleForTesting
    fun getIdlingResource(): SimpleIdlingResource {
        return idlingResource
    }
}
