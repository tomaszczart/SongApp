package com.tooploox.songapp.network

class SongsRepository {

    /**
     * Types of databases:
     * OFFLINE_DB - gets data from local json file (assets/songs-list.json)
     * ONLINE_DB - gets data from iTunes API
     * HYBRID_DB - gets data from both sources, ONLINE_DB and OFFLINE_DB
     */
    enum class DatabaseType {
        OFFLINE_DB, ONLINE_DB, HYBRID_DB
    }

    /* Currently set source that data is taken from */
    var selectedDatabase: DatabaseType = DatabaseType.OFFLINE_DB

    fun search(query: String) {

    }


}