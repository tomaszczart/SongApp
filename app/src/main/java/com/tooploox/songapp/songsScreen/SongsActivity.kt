package com.tooploox.songapp.songsScreen

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tooploox.songapp.R
import com.tooploox.songapp.common.BaseActivity
import com.tooploox.songapp.databinding.ActivitySongsBinding
import com.tooploox.songapp.songsScreen.viewModel.SongsActivityViewModel
import com.tooploox.songapp.songsScreen.viewModel.SongsActivityViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class SongsActivity : BaseActivity() {

    @Inject
    lateinit var songsActivityViewModelFactory: SongsActivityViewModelFactory
    lateinit var songsActivityViewModel: SongsActivityViewModel

    private lateinit var binding: ActivitySongsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_songs)

        songsActivityViewModel = ViewModelProviders.of(this, songsActivityViewModelFactory).get(SongsActivityViewModel::class.java)

        binding.apply {
            lifecycleOwner = this@SongsActivity
            viewModel = songsActivityViewModel
        }

    }
}
