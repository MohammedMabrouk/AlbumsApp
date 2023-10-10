package com.example.albumsapp.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albumsapp.BR
import com.example.albumsapp.R
import com.example.albumsapp.databinding.FragmentProfileBinding
import com.example.albumsapp.presentation.profile.ProfileViewModel.Companion.ARG_ALBUM_ID
import com.example.albumsapp.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    val vm: ProfileViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAlbums.layoutManager = LinearLayoutManager(activity)
        observe(vm.albumsList) {
            it?.let {
                val albumsAdapter = AlbumsAdapter(vm, it)
                binding.rvAlbums.adapter = albumsAdapter
            }
        }
        observe(vm.event) { onEventReceived(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        vm.loadData()
    }

    private fun onEventReceived(event: ProfileEvent) {
        if (event is ProfileEvent.NavigateToAlbumDetails) {
            val bundle = bundleOf(ARG_ALBUM_ID to event.albumId)
            findNavController()
                .navigate(R.id.action_ProfileFragment_to_AlbumDetailsFragment, bundle)
        }
    }
}