package com.example.movieapp2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.movieapp2.core.Resource
import com.example.movieapp2.repository.MovieRepository
import kotlinx.coroutines.Dispatchers


class MovieViewModel(private val repo:MovieRepository): ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(),repo.getTopRateMovies(),repo.getPopularMovies())))
        }
        catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }


}

class MovieViewModelFactory(private  val repo: MovieRepository):ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}

data class NTruple4<T1,T2,T3,T4>(val t1:T1, val t2:T2, val te:T3, val t4:T4)