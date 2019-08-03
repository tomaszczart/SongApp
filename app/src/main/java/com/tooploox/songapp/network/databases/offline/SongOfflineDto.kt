package com.tooploox.songapp.network.databases.offline

import com.google.gson.annotations.SerializedName

data class SongOfflineDto(
    @SerializedName("ARTIST CLEAN") val artist: String,
    @SerializedName("Song Clean") val title: String,
    @SerializedName("Release Year") val releaseYear: String
)
