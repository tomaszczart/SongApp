package com.tooploox.songapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tooploox.songapp.dagger.ApplicationScope
import com.tooploox.songapp.network.databases.offline.OfflineDbDao
import com.tooploox.songapp.network.databases.offline.SongOfflineDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ApplicationScope
class SongsRepository @Inject constructor(private val offlineDbDao: OfflineDbDao) {

    /**
     * Types of databases:
     * OFFLINE_DB - gets data from local json file (assets/songs-list.json)
     * ONLINE_DB - gets data from iTunes API
     * HYBRID_DB - gets data from both sources, ONLINE_DB and OFFLINE_DB
     */
    enum class DatabaseType {
        OFFLINE_DB, ONLINE_DB, HYBRID_DB
    }

    /**
     * This is the main scope for all coroutines launched by SongsRepository.
     */
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Default + job)

    /* Currently set source that data is taken from */
    var selectedDatabase: DatabaseType = DatabaseType.OFFLINE_DB

    fun search(query: String): LiveData<List<SongOfflineDto>> {

        val allSongsLiveData = MutableLiveData<List<SongOfflineDto>>()

        coroutineScope.launch {
            allSongsLiveData.postValue(offlineDbDao.search(query))
        }

        return allSongsLiveData
    }


}