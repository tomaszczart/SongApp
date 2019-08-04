package com.tooploox.songapp.network.databases.online

/**
 * Response from iTunes.
 */
data class SongsListDto(val results : Array<SongOnlineDto>)

/**
 * Song item returned from iTunes.
 */
data class SongOnlineDto(
    val artistName: String?,
    val trackName: String?,
    val releaseDate: String?
)