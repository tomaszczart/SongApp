package com.tooploox.songapp.network.databases.offline

import android.content.Context
import com.google.gson.Gson
import com.tooploox.songapp.BuildConfig
import com.tooploox.songapp.network.databases.ISongsDao
import com.tooploox.songapp.network.databases.SongDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class OfflineDbDao @Inject constructor(private val context: Context, private val gson: Gson) : ISongsDao {

    private val databaseFilename = "songs-list.json"
    private val listOfAllSongs: List<SongDto>? = null

    /**
     * This method filters out songs by given phrase.
     */
    override suspend fun search(query: String): List<SongDto> = withContext(Dispatchers.Default) {
        return@withContext getAllSongs().filter { it.artist.contains(query) }
    }

    /**
     * This method returns loaded songs or loads them if it is first call
     */
    private suspend fun getAllSongs(): List<SongDto> = listOfAllSongs ?: loadAllSongs()

    /**
     * This method loads list of all songs form file.
     */
    private suspend fun loadAllSongs(): List<SongDto> = withContext(Dispatchers.Default) {
        try {
            //Read file
            val reader = BufferedReader(InputStreamReader(context.assets.open(databaseFilename)))
            val jsonFileContent = reader.readText()
            reader.close()

            //Parse JSON form the file content
            val allSongs = gson.fromJson(jsonFileContent, Array<SongOfflineDto>::class.java)
            Timber.d("Loaded list of songs: $listOfAllSongs")

            return@withContext allSongs.map { SongDto(it.artist, it.title, it.releaseYear.toIntOrNull() ?: 0) }.toList()
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) throw e else Timber.e(e)

        }

        return@withContext listOf<SongDto>()
    }

}