package com.odukabdulbasit.movieradar.listofmovie

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.*
import com.odukabdulbasit.movieradar.database.getDatabase
import com.odukabdulbasit.movieradar.repository.MoviesRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class MovieListViewModel(app: Application) : ViewModel() {

    private val database = getDatabase(app)
    private val moviesRepository = MoviesRepository(database)


    init {
        if (isNetworkAvailable(app)) {
            getMovieProperty()
        }
    }

    val movieProperty = moviesRepository.movies

    private fun getMovieProperty() {

        viewModelScope.launch {
            moviesRepository.refreshMovies()
        }
    }

    private fun isNetworkAvailable(application: Application): Boolean {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


    class Factory(val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieListViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MovieListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
