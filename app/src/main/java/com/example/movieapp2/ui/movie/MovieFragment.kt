package com.example.movieapp2.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movieapp2.R
import com.example.movieapp2.core.Resource
import com.example.movieapp2.data.model.Movie
import com.example.movieapp2.data.remote.MovieDataSource
import com.example.movieapp2.databinding.FragmentMovieBinding
import com.example.movieapp2.presentation.MovieViewModel
import com.example.movieapp2.presentation.MovieViewModelFactory
import com.example.movieapp2.repository.MovieRepositoryImpl
import com.example.movieapp2.repository.RetrofitClient
import com.example.movieapp2.ui.movie.adapters.MovieAdapter
import com.example.movieapp2.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.movieapp2.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.movieapp2.ui.movie.adapters.concat.UpcomingConcatAdapter
import java.util.*

class MovieFragment : Fragment(R.layout.fragment_movie),MovieAdapter.onMovieClickListener  {

    private lateinit var  binding : FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        MovieDataSource(RetrofitClient.webService)
    )) }
    private lateinit var concatAdapter : ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner,{
            result->
            when(result){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0,UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                        addAdapter(1,TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                        addAdapter(2,PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                    }

                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure ->{
                    binding.progressBar.visibility = View.GONE
                    Log.d("LiveData", "${result.exception}")
                }
            }
        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }

}