package com.tooploox.songapp.songsScreen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tooploox.songapp.network.SongsRepository
import com.tooploox.songapp.songsScreen.recyclerView.SongItem
import javax.inject.Inject

class SongsActivityViewModel @Inject constructor(app: Application, private val songsRepository: SongsRepository) :
    AndroidViewModel(app) {

    val songsList: LiveData<List<SongItem>> = Transformations.map(songsRepository.search("Eminem")) {
        it.map { songOfflineDto -> SongItem(songOfflineDto.artist, songOfflineDto.title, 0) }
    }

}