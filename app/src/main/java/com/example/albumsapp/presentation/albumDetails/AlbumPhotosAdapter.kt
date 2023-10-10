package com.example.albumsapp.presentation.albumDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.albumsapp.R
import com.example.albumsapp.databinding.ItemAlbumPhotoBinding
import com.example.albumsapp.domain.model.AlbumPhoto

class AlbumPhotosAdapter(private val albumPhotosList: List<AlbumPhoto>) :
    RecyclerView.Adapter<AlbumPhotosAdapter.AlbumPhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumPhotosViewHolder {
        val itemAlbumPhotoBinding: ItemAlbumPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_album_photo, parent, false
        )
        return AlbumPhotosViewHolder(itemAlbumPhotoBinding)
    }

    override fun onBindViewHolder(holder: AlbumPhotosViewHolder, position: Int) {
        holder.bind(albumPhotosList[position])
    }

    override fun getItemCount() = albumPhotosList.size

    class AlbumPhotosViewHolder(
        private val itemAlbumPhotoBinding: ItemAlbumPhotoBinding
    ) : RecyclerView.ViewHolder(itemAlbumPhotoBinding.root) {
        fun bind(albumPhoto: AlbumPhoto?) {
            itemAlbumPhotoBinding.apply {
                tvTitle.text = albumPhoto?.title
                Glide.with(this.root.context)
                    .load(albumPhoto?.thumbnailUrl)
                    .placeholder(R.drawable.bg_image_placeholder)
                    .error(R.drawable.bg_image_placeholder)
                    .into(ivPhoto)
            }
        }
    }
}