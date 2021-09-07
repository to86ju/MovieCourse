package com.example.movieapp2.repository

import com.example.movieapp2.data.model.MovieList

interface MovieRepository {

    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRateMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}