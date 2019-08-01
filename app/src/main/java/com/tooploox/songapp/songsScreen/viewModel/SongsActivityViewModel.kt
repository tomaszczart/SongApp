package com.tooploox.songapp.songsScreen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class SongsActivityViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {

    val testData = MutableLiveData<String>()

    init {
        testData.value = "test"
    }

}