package com.tooploox.songapp.songsScreen.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tooploox.songapp.databinding.SongsListItemBinding

class SongsListAdapter(context: Context) : ListAdapter<SongItem, SongsListAdapter.MyViewHolder>(
    object : DiffUtil.ItemCallback<SongItem>() {
        override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean {
            return oldItem.artist == newItem.artist && oldItem.title == newItem.title && oldItem.releaseYear == newItem.releaseYear
        }

        override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean =
            oldItem.artist == newItem.artist && oldItem.title == newItem.title
    }
) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SongsListItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(private val binding: SongsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(songItem: SongItem) {
            binding.song = songItem
        }
    }

}