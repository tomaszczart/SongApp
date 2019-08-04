package com.tooploox.songapp.network.databases.online

import com.tooploox.songapp.network.databases.ISongsDao
import com.tooploox.songapp.network.databases.SongDao
import javax.inject.Inject

class OnlineDbDao @Inject constructor(private val appleiTunesService: AppleiTunesService) : ISongsDao {

    /**
     * This method filters out songs by given phrase.
     */
    override suspend fun search(query: String): List<SongDao> =
        appleiTunesService.getSongs(query).results.map { SongDao(it.artistName, it.trackName, 0) }
}