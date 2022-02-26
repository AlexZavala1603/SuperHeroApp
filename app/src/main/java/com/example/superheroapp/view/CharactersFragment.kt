package com.example.superheroapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroapp.data.adapters.CharactersAdapter
import com.example.superheroapp.databinding.FragmentCharactersBinding
import com.example.superheroapp.viewmodel.CharactersViewModel

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val adapter: CharactersAdapter = CharactersAdapter()
    private val viewModel: CharactersViewModel by viewModels()
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        binding.fabBack.setOnClickListener { activity?.onBackPressed() }

        binding.recyclerSuperheros.apply {
            adapter = this@CharactersFragment.adapter
            layoutManager = GridLayoutManager(context, 2)
        }

        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v?.getChildAt(0)?.measuredHeight!! - v.measuredHeight) {
                // When reach last item position
                // Increase page size
                if (!viewModel.isLoading.value!! && !viewModel.isInitializing.value!!) {
                    viewModel.getSuperheros(getOffset())
                }
            }
        })

        viewModel.onCreate()

        activity?.let {
            viewModel.superherosList.observe(it) { superherosList ->
                adapter.setCharactersList(superherosList)
            }
        }

        activity?.let {
            viewModel.currentPage.observe(it) { currentPage ->
                this.currentPage = currentPage
            }
        }

        activity?.let {
            viewModel.isInitializing.observe(it) { visibility ->
                binding.progressBar.isVisible = visibility
            }
        }

        activity?.let {
            viewModel.isLoading.observe(it) { visibility ->
                binding.progressBarPagination.isVisible = visibility
            }
        }

        viewModel.getSuperheros(getOffset())
    }

    private fun getOffset(): Int {
        return when {
            currentPage > 0 -> currentPage * 100
            else -> 0
        }
    }

}