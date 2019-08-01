package com.tooploox.songapp.songsScreen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tooploox.songapp.songsScreen.recyclerView.SongItem
import javax.inject.Inject

class SongsActivityViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {

    val songsList = MutableLiveData<List<SongItem>>()

    init {
        songsList.value = listOf(
            SongItem("NF", "The Search", 2019),
            SongItem("Survivor", "Eye Of The Tiger", 1999),
            SongItem("Queen", "We Are The Champions", 1977),
            SongItem("Eminem", "Lose Yourself", 2001)
        )
    }

}