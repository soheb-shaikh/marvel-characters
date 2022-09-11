package com.shaikhsoheb.marvelcharactersapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.shaikhsoheb.marvelcharactersapp.ui.theme.MarvelCharactersAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelCharactersAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Trial code for Swipe Refresh
                    SwipeRefresh()
                }
            }
        }
    }
}

@Composable
fun SwipeRefresh() {
    val list = List(1000) { "$it"}
    SwipeRefresh(
        state = rememberSwipeRefreshState( true ),
        onRefresh = {  },
    ) {
        LazyColumn {
            items(list.size) { index ->
                Text(text = index.toString())
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MarvelCharactersAppTheme {
        SwipeRefresh()
    }
}