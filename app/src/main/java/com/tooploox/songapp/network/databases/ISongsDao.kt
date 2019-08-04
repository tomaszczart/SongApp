package com.tooploox.songapp.network.databases

import com.tooploox.songapp.network.databases.offline.SongOfflineDto

interface ISongsDao {
    /**
     * This method filters out songs from given artist.
     */
    suspend fun search(query: String): List<SongOfflineDto>
}
