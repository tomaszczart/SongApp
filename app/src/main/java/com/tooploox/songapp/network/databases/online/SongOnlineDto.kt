package com.tooploox.songapp.network.databases.online

data class SongsListDto(val results : Array<SongOnlineDto>)

data class SongOnlineDto(
    val artistName: String,
    val trackName: String,
    val releaseDate: String
)