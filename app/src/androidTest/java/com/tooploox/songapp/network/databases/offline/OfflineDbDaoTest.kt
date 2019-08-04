package com.tooploox.songapp.network.databases.offline

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OfflineDbDaoTest {

    private lateinit var offlineDbDao: OfflineDbDao

    private var context = InstrumentationRegistry.getContext()

    @Before
    fun setUp() {
        val gson = GsonBuilder().create()
        offlineDbDao = OfflineDbDao(context, gson)
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