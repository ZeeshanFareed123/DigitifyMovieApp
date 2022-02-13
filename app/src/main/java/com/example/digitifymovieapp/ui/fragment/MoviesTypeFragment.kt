package com.example.digitifymovieapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.digitifymovieapp.databinding.FragmentMoviesTypeBinding
import com.example.digitifymovieapp.ui.paging.MoviePagingAdapter
import com.example.digitifymovieapp.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesTypeFragment : Fragment() {
    private lateinit var binding: FragmentMoviesTypeBinding

    val viewModel: MovieViewModel by viewModels()

    private val movieAdapter = MoviePagingAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviesTypeBinding.inflate(inflater, container, false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecyclerView()

        binding.movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        movieAdapter.onMovieClick {
            val action = MoviesTypeFragmentDirections.actionMovieFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        viewModel.list.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }


    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }


}