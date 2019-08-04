package com.tooploox.songapp.songsScreen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.tooploox.songapp.network.SongsRepository
import com.tooploox.songapp.songsScreen.recyclerView.SongItem
import javax.inject.Inject

class SongsActivityViewModel @Inject constructor(app: Application, private val songsRepository: SongsRepository) :
        AndroidViewModel(app) {

    /**
     * Value of the database enabled/disabled switches
     */
    val useOffline = MutableLiveData<Boolean>()
    val useOnline = MutableLiveData<Boolean>()

    /**
     * Search text input value.
     */
    val queryText = MutableLiveData<String>()

    val songsList = Transformations.map(songsRepository.searchResults) {
        it.map { songOfflineDto -> SongItem(songOfflineDto.artist, songOfflineDto.title, songOfflineDto.releaseYear) }
    }

    init {
        //set online bd enabled
        useOnline.postValue(true)
    }

    /**
     * This method can enable or disable offline database
     *
     * @param use If true offline database will be used
     */
    fun useOfflineDatabase(use: Boolean) {
        songsRepository.useOffline = use
    }

    /**
     * This method can enable or disable online database
     *
     * @param use If true online database will be used
     */
    fun useOnlineDatabase(use: Boolean) {
        songsRepository.useOnline = use
    }

    /**
     * This method calls selected database and returns list of songs.
     *
     * @param query Phrase to look songs by.
     */
    fun searchSong(query: String) {
        songsRepository.search(query)
    }

    override fun onCleared() {
        //cancel all requests from repository
        songsRepository.cancel()
        super.onCleared()
    }

}