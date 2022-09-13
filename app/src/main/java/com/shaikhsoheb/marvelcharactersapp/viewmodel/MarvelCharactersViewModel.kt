package com.shaikhsoheb.marvelcharactersapp.viewmodel

import androidx.lifecycle.*
import com.shaikhsoheb.marvelcharactersapp.data.MarvelCharacterResource
import com.shaikhsoheb.marvelcharactersapp.data.remote.NetworkHelper
import com.shaikhsoheb.marvelcharactersapp.data.remote.model.MarvelCharacterR
import com.shaikhsoheb.marvelcharactersapp.repository.MarvelCharactersRepository
import com.shaikhsoheb.marvelcharactersapp.repository.mapToMarvelCharacterL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelCharactersViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val repository: MarvelCharactersRepository
): ViewModel() {
    private val charactersR = MutableLiveData<MarvelCharacterResource<List<MarvelCharacterR>>>()

    val characters = repository.loadCharactersList().asLiveData()

    val bookMarkedCharacters = repository.getBookmarkedCharacters().asLiveData()

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            charactersR.postValue(MarvelCharacterResource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                repository.loadCharactersFromApi().let {
                    if (it.code == 200) {
                        try {
                            val charactersList = it.data.results as? List<MarvelCharacterR>
                            if (charactersList.isNullOrEmpty()) {
                                charactersR.postValue(MarvelCharacterResource.error("List Unavailable", null))
                            } else {
                                charactersR.postValue(MarvelCharacterResource.success(charactersList))
                                val charactersLocal = charactersList.map { character ->
                                    character.mapToMarvelCharacterL()
                                }
                                repository.saveCharacters(charactersLocal)
                            }
                        } catch (e: Exception) {
                            charactersR.postValue(MarvelCharacterResource.error("List Unavailable", null))
                        }
                    } else {
                        charactersR.postValue(MarvelCharacterResource.error(it.status, null))
                    }
                }
            } else {
                charactersR.postValue(MarvelCharacterResource.error("No Internet", null))
            }
        }
    }
}