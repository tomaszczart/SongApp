package com.tooploox.songapp.network.databases

interface ISongsDao {
    /**
     * This method filters out songs by given phrase.
     */
    suspend fun search(query: String): List<SongDto>
}
