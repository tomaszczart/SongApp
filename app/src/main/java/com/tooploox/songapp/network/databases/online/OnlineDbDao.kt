package com.tooploox.songapp.network.databases.online

import com.tooploox.songapp.BuildConfig
import com.tooploox.songapp.network.databases.ISongsDao
import com.tooploox.songapp.network.databases.SongDao
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class OnlineDbDao @Inject constructor(private val appleiTunesService: AppleiTunesService) : ISongsDao {

    /**
     * This method filters out songs by given phrase.
     */
    override suspend fun search(query: String): List<SongDao> =
            appleiTunesService.getSongs(query).results.map {
                SongDao(it.artistName ?: "", it.trackName ?: "", parseYear(it.releaseDate ?: ""))
            }

    /**
     * This method parses date string into a year integer.
     *
     * @param Date string
     * @return Year as integer value
     */
    private fun parseYear(dateString: String): Int {
        return try {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            calendar.time = sdf.parse(dateString)
            calendar.get(Calendar.YEAR)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) Timber.e("Year parsing error ${e.message}")
            0
        }
    }
}