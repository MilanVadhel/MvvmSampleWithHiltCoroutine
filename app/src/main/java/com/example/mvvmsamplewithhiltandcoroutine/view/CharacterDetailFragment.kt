package com.example.mvvmsamplewithhiltandcoroutine.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mvvmsamplewithhiltandcoroutine.R
import com.example.mvvmsamplewithhiltandcoroutine.databinding.FragmentCharacterDetailBinding
import com.example.mvvmsamplewithhiltandcoroutine.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val TAG = "CharacterDetailFragment"
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.character = args.character
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}