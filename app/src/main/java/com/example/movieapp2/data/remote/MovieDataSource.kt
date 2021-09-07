package com.example.movieapp2.data.remote

import com.example.movieapp2.application.AppConstants
import com.example.movieapp2.data.model.MovieList
import com.example.movieapp2.repository.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList {
        return webService.getUpcomingMovies(AppConstants.API_KEY)
    }

    suspend fun getTopRatedMovies(): MovieList{
        return webService.getTopRateMovies(AppConstants.API_KEY)
    }

    suspend fun getPopularMovies(): MovieList{
        return webService.getPopularMovies(AppConstants.API_KEY)
    }
}