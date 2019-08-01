package com.tooploox.songapp.songsScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SongsActivityViewModelFactory @Inject constructor(private var viewModel: SongsActivityViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongsActivityViewModel::class.java)) return viewModel as T
        throw IllegalArgumentException("Unknown class name $modelClass")
    }
}