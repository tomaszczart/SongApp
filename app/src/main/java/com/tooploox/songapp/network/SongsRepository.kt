package com.tooploox.songapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tooploox.songapp.dagger.ApplicationScope
import com.tooploox.songapp.network.databases.SongDto
import com.tooploox.songapp.network.databases.offline.OfflineDbDao
import com.tooploox.songapp.network.databases.online.OnlineDbDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ApplicationScope
class SongsRepository @Inject constructor(
        private val offlineDbDao: OfflineDbDao,
        private val onlineDbDao: OnlineDbDao
) {

    val searchResults = MutableLiveData<List<SongDto>>()
    val loadingSongs = MutableLiveData<Boolean>()

    //For emitting error messages
    val errors = MutableLiveData<String>()

    /**
     * If true offline database will be used
     */
    var useOffline = false
    /**
     * If true online database will be used
     */
    var useOnline = false
    /**
     * If both are true results from offline and online requests will be merged.
     */

    /**
     * This is the main scope for all coroutines launched by SongsRepository.
     */
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Default + job)

    /**
     * This method calls selected database and returns list of songs.
     */
    fun search(query: String): LiveData<List<SongDto>> {

        if (loadingSongs.value != true) {

            val result = mutableListOf<SongDto>()

            coroutineScope.launch {
                loadingSongs.postValue(true)
                if (useOffline) {
                    try {
                        result.addAll(offlineDbDao.search(query))
                    } catch (e: Exception) {
                        Timber.e(e)
                        errors.postValue(e.localizedMessage)
                    }
                }
                if (useOnline) {
                    try {
                        result.addAll(onlineDbDao.search(query))
                    } catch (e: Exception) {
                        Timber.e(e)
                        errors.postValue(e.localizedMessage)
                    }
                }

                //sort results by artist
                result.sortBy { it.artist }

                Timber.d("Results $result")
                loadingSongs.postValue(false)
                searchResults.postValue(result)
            }
        }

        return searchResults
    }

    /**
     * Cleanup when destroying the object.
     */
    fun cancel() {
        job.cancel()
    }

}