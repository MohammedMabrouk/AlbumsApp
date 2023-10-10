package com.example.albumsapp.presentation.albumDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.albumsapp.BR
import com.example.albumsapp.databinding.FragmentAlbumDetailsBinding
import com.example.albumsapp.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {
    val vm: AlbumsDetailsViewModel by viewModels()
    private var _binding: FragmentAlbumDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        binding.setVariable(BR.vm, vm)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAlbumPhotos.layoutManager = GridLayoutManager(activity, 3)
        observe(vm.albumsPhotosList) {
            it?.let {
                val albumsAdapter = AlbumPhotosAdapter(it)
                binding.rvAlbumPhotos.adapter = albumsAdapter
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val newPhotoList = vm.albumsPhotosList.value?.filter {
                    it.title.contains(query?: "")
                } ?: listOf()
                val albumsAdapter = AlbumPhotosAdapter(newPhotoList)
                binding.rvAlbumPhotos.adapter = albumsAdapter
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newPhotoList = vm.albumsPhotosList.value?.filter {
                    it.title.contains(newText?: "")
                } ?: listOf()
                val albumsAdapter = AlbumPhotosAdapter(newPhotoList)
                binding.rvAlbumPhotos.adapter = albumsAdapter
                return false
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}