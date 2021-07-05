package com.odukabdulbasit.movieradar.repository

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.odukabdulbasit.Constants.apiKey
import com.odukabdulbasit.movieradar.Movie
import com.odukabdulbasit.movieradar.api.MovieApi
import com.odukabdulbasit.movieradar.api.NetworkMovieContainer
import com.odukabdulbasit.movieradar.api.asDatabaseModel
import com.odukabdulbasit.movieradar.database.MoviesDatabase
import com.odukabdulbasit.movieradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val database: MoviesDatabase) {

    val movies: LiveData<List<Movie>> = Transformations.map(database.movieDao.getMovies()){
        it.asDomainModel()
    }


    suspend fun refreshMovies(){
        withContext(Dispatchers.IO){
            //val movieList = MovieApi.movieService.getMovieList(apiKey, "/discover/movie?sort_by=popularity.desc").movieList
            val movieList = MovieApi.movieService.getMovieList(apiKey).movieList

            ///val movieList = MovieApi.movieService.getMovieList(apiKey, "primary_release_year=2010&sort_by=vote_average.desc").movieList
            database.movieDao.insertAll(NetworkMovieContainer(movieList).asDatabaseModel().toList())
        }
    }
}