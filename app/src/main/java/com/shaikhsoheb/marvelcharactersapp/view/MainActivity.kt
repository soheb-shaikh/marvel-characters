package com.shaikhsoheb.marvelcharactersapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.shaikhsoheb.marvelcharactersapp.data.Status
import com.shaikhsoheb.marvelcharactersapp.data.local.model.MarvelCharacterL
import com.shaikhsoheb.marvelcharactersapp.ui.theme.MarvelCharactersAppTheme
import com.shaikhsoheb.marvelcharactersapp.viewmodel.MarvelCharactersViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MarvelCharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelCharactersAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SwipeRefreshing(viewModel = viewModel)
                }
            }
        }
        viewModel.fetchCharacters()
    }
}

@Composable
fun SwipeRefreshing(viewModel: MarvelCharactersViewModel) {
    val charactersState = mutableStateOf(viewModel.characters.value)
    SwipeRefresh(
        state = rememberSwipeRefreshState( charactersState.value?.status == Status.LOADING),
        onRefresh = { viewModel.fetchCharacters() },
    ) {
        if (charactersState.value?.status == Status.ERROR) {
            Text(text = charactersState.value?.message.orEmpty())
        } else {
            LazyColumn {
                items(charactersState.value?.data.orEmpty()) { item ->
                    CreateMarvelCharacterCard(character = item)
                }
            }
        }
    }
}

@Composable
fun CreateMarvelCharacterCard(character: MarvelCharacterL) {
    Column {
        GlideImage(
            imageModel = character.thumbnail_url
        )
        Text(text = character.name)
    }
}