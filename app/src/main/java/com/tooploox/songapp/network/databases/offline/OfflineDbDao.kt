package com.tooploox.songapp.network.databases.offline

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class OfflineDbDao @Inject constructor(private val context: Context, private val gson: Gson) {

    private val databaseFilename = "songs-list.json"
    private val listOfAllSongs: List<SongOfflineDto>? = null

    /**
     * This method returns loaded songs or loads them if it is first call
     */
    suspend fun getAllSongs() = listOfAllSongs ?: loadAllSongs()

    /**
     * This method filters out songs from given artist.
     */
    suspend fun search(query: String) = withContext(Dispatchers.Default) {
        return@withContext getAllSongs().filter { it.artist.contains(query) }
    }

    /**
     * This method loads list of all songs form file.
     */
    private suspend fun loadAllSongs() = withContext(Dispatchers.Default) {
        try {
            //Read file
            val reader = BufferedReader(InputStreamReader(context.assets.open(databaseFilename)))
            val jsonFileContent = reader.readText()
            reader.close()

            //Parse JSON form the file content
            val allSongs = gson.fromJson(jsonFileContent, Array<SongOfflineDto>::class.java)
            Timber.d("Loaded list of songs: $listOfAllSongs")

            return@withContext allSongs.toList()
        } catch (e: Exception) {
            Timber.e(e)
        }

        return@withContext listOf<SongOfflineDto>()
    }

}