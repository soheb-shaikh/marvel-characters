package com.shaikhsoheb.marvelcharactersapp.viewmodel

import androidx.lifecycle.*
import com.shaikhsoheb.marvelcharactersapp.data.MarvelCharacterResource
import com.shaikhsoheb.marvelcharactersapp.data.local.model.MarvelCharacterL
import com.shaikhsoheb.marvelcharactersapp.data.remote.NetworkHelper
import com.shaikhsoheb.marvelcharactersapp.repository.MarvelCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelCharactersViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val repository: MarvelCharactersRepository
): ViewModel() {
    private val _characters = MutableLiveData<MarvelCharacterResource<List<MarvelCharacterL>>>()

    val characters: LiveData<MarvelCharacterResource<List<MarvelCharacterL>>>
        get() { return _characters }

    val bookMarkedCharacters = repository.getBookmarkedCharacters().asLiveData()

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            _characters.postValue(MarvelCharacterResource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                repository.loadCharacters().let {
                    _characters.postValue(it)
                }
            } else {
                _characters.postValue(MarvelCharacterResource.error("No Internet", null))
            }
        }
    }
}