package com.tooploox.songapp.network.databases.offline

import androidx.test.InstrumentationRegistry
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OfflineDbDaoTest {

    private lateinit var offlineDbDao: OfflineDbDao

    private var test = InstrumentationRegistry.getContext()

    @Before
    fun setUp() {
        val gson = GsonBuilder().create()
        offlineDbDao = OfflineDbDao(test, gson)
    }

    /**
     * Test if test asset has proper size.
     */
    @Test
    fun search() {
        val numOfAllSongs = 4
        val result = runBlocking {
            offlineDbDao.search("")
        }
        assertEquals(numOfAllSongs, result.size)
    }
}