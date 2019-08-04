package com.tooploox.songapp.network.databases.online

import retrofit2.http.GET
import retrofit2.http.Query

interface AppleiTunesService {

    /**
     * Get songs by given phrase.
     *
     * @param term Phrase to look songs by.
     */
    @GET("/search?kind=song")
    suspend fun getSongs(@Query("term") term: String): SongsListDto

}