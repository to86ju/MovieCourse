package com.example.movieapp2.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp2.core.BaseConcatHolder
import com.example.movieapp2.databinding.UpcomingMovieRowBinding
import com.example.movieapp2.ui.movie.adapters.MovieAdapter

class UpcomingConcatAdapter(private val moviesAdapter: MovieAdapter) : RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = UpcomingMovieRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1



    private inner class ConcatViewHolder(val binding: UpcomingMovieRowBinding):
        BaseConcatHolder<MovieAdapter>(binding.root){

        override fun bind(adpater: MovieAdapter) {
            binding.rvUpcomingMovies.adapter = adpater
        }

    }
}
