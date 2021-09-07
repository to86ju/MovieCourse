package com.example.movieapp2.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp2.application.AppConstants
import com.example.movieapp2.core.BaseViewHolder
import com.example.movieapp2.data.model.Movie
import com.example.movieapp2.databinding.MovieItemBinding

class MovieAdapter(private val moviesList: List<Movie>, private val itemClickListener : onMovieClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onMovieClickListener{
        fun onMovieClick(movie : Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = MoviesViewHolder(itemBinding,parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION}
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(moviesList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(moviesList[position])
        }
    }

    override fun getItemCount(): Int  = moviesList.size



    private inner class MoviesViewHolder(val binding: MovieItemBinding, val context: Context): BaseViewHolder<Movie> (binding.root){
        override fun bind(item: Movie) {
            Glide.with(context).load("${AppConstants.BASE_URL_IMG}${item.poster_path}").centerCrop().into(binding.imgMovie)
        }

    }
}