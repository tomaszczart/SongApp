package com.tooploox.songapp.songsScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tooploox.songapp.R
import com.tooploox.songapp.common.BaseActivity
import com.tooploox.songapp.databinding.ActivitySongsBinding

class SongsActivity : BaseActivity() {

    private lateinit var binding: ActivitySongsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_songs)
    }
}
