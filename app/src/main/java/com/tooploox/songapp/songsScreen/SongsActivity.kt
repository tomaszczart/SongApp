package com.tooploox.songapp.songsScreen

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tooploox.songapp.R
import com.tooploox.songapp.common.BaseActivity
import com.tooploox.songapp.databinding.ActivitySongsBinding
import com.tooploox.songapp.songsScreen.recyclerView.SongsListAdapter
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

        songsActivityViewModel =
                ViewModelProviders.of(this, songsActivityViewModelFactory).get(SongsActivityViewModel::class.java)

        val songsListAdapter = SongsListAdapter(this)

        binding.apply {
            lifecycleOwner = this@SongsActivity
            viewModel = songsActivityViewModel
            songsList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = songsListAdapter
            }
            //do not allow to refresh by swipe
            swipeToRefresh.isEnabled = false
        }

        //Observe offline switch
        binding.viewModel?.useOffline?.observe(this, Observer {
            Timber.d("useOffline: $it")
            binding.viewModel?.useOfflineDatabase(it ?: false)
        })

        //Observe online switch
        binding.viewModel?.useOnline?.observe(this, Observer {
            Timber.d("useOnline: $it")
            binding.viewModel?.useOnlineDatabase(it ?: false)
        })

        //perform search action after clicking the button
        binding.searchButton.setOnClickListener {
            val query = binding.queryPhrase.editText?.text.toString()
            songsActivityViewModel.searchSong(query)
        }

        //Observe changes in the songs list and add them to recycler view
        songsActivityViewModel.songsList.observe(this, Observer {
            songsListAdapter.submitList(it)
        })

        //show/hide loading indicator
        songsActivityViewModel.loadingSongs.observe(this, Observer {
            binding.swipeToRefresh.isRefreshing = it
        })
    }
}
