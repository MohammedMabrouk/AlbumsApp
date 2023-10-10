package com.example.albumsapp.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsapp.databinding.ItemAlbumBinding
import com.example.albumsapp.domain.model.Album
import com.example.albumsapp.R

class AlbumsAdapter(val vm: ProfileViewModel, val albumsList: List<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemAlbumBinding: ItemAlbumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_album, parent, false
        )
        return AlbumsViewHolder(vm, itemAlbumBinding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albumsList[position])
    }


    override fun getItemCount() = albumsList.size


    class AlbumsViewHolder(
        private val vm: ProfileViewModel,
        private val itemAlbumBinding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(itemAlbumBinding.root) {
        fun bind(album: Album?) {
            itemAlbumBinding.apply {
                root.setOnClickListener {
                    vm.navigateToAlbumDetails(album)
                }
                tvTitle.text = album?.title
            }
        }
    }
}