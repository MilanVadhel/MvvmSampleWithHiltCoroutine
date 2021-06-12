package com.example.mvvmsamplewithhiltandcoroutine.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmsamplewithhiltandcoroutine.databinding.FragmentCharacterListBinding
import com.example.mvvmsamplewithhiltandcoroutine.extension.hide
import com.example.mvvmsamplewithhiltandcoroutine.extension.show
import com.example.mvvmsamplewithhiltandcoroutine.model.Result
import com.example.mvvmsamplewithhiltandcoroutine.view.adapters.CharacterListAdapter
import com.example.mvvmsamplewithhiltandcoroutine.view.common.UiState
import com.example.mvvmsamplewithhiltandcoroutine.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val TAG = "CharacterListFragment"
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private var counter = 1

    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter(::onItemClick)
    }

    private val viewModel: CharacterViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        //loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        initRecyclerView()
        loadData()
        initLiveDataObserver()
    }

    private fun initRecyclerView() {
        binding.rvCharacters.adapter = characterListAdapter
    }

    private fun loadData() {
        Log.d(TAG, "Api Call -> $counter")
        viewModel.getCharacterList()
        counter++
    }

    private fun initLiveDataObserver() {
        Log.d(TAG, "initLiveDataObserver: ")
        val hasActiveObservers = viewModel.characterListLiveEvent.hasActiveObservers()
        Log.d(TAG, "hasObservers : ${viewModel.characterListLiveEvent.hasObservers()}")
        if (!hasActiveObservers) {
            Log.d(TAG, "hasActiveObservers : $hasActiveObservers")
            viewModel.characterListLiveEvent.observe(viewLifecycleOwner,
                { response ->
                    when (response) {
                        is UiState.Loading -> {
                            binding.pbData.show()
                        }
                        is UiState.Error -> {
                            Log.e(TAG, "Error => ", response.throwable)
                            binding.pbData.hide()
                        }
                        is UiState.Success -> {
                            characterListAdapter.submitList(response.data.results as ArrayList<Result>)
                            binding.pbData.hide()
                        }
                    }
                })
        }
        Log.d(TAG, "hasActiveObservers : ${viewModel.characterListLiveEvent.hasActiveObservers()}")
        Log.d(TAG, "hasObservers : ${viewModel.characterListLiveEvent.hasObservers()}")
    }

    private fun onItemClick(result: Result) {
        findNavController().navigate(
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                result
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
        Log.d(
            TAG,
            "hasObservers After onDestroyView: ${viewModel.characterListLiveEvent.hasObservers()}"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.d(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }
}