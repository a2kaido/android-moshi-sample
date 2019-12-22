package io.github.a2kaido.moshi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.a2kaido.moshi.ui.repository.PokeRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val pokeRepository: PokeRepository
) : ViewModel() {

    private val _poke = MutableLiveData<String>()
    val poke: LiveData<String>
        get() = _poke

    fun getPoke(name: String) {
        viewModelScope.launch {
            runCatching {
                pokeRepository.getPoke(name)
            }.onSuccess {
                _poke.value = it.name
            }.onFailure {
                println(it.localizedMessage)
            }
        }
    }
}
