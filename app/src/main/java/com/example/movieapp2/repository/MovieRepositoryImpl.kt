package com.example.movieapp2.repository

import com.example.movieapp2.data.model.MovieList
import com.example.movieapp2.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRateMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

}