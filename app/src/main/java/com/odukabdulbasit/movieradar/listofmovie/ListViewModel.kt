package com.odukabdulbasit.movieradar.listofmovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odukabdulbasit.Constants.apiKey
import com.odukabdulbasit.movieradar.Movie
import com.odukabdulbasit.movieradar.MovieObjects
import com.odukabdulbasit.movieradar.api.MovieApi
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.launch
import timber.log.Timber


class ListViewModel : ViewModel() {

private val _movieProperty = MutableLiveData<List<Movie>>()
    val movieProperty : LiveData<List<Movie>>
    get() = _movieProperty

    //bunu ikinci sayfaya veri gondermek icin kullancam
    private val _gelenDeger = MutableLiveData<String>()
    val gelenDeger : LiveData<String>
    get() = _gelenDeger


    init {
        getMovieProperty()
    }

    private fun getMovieProperty() {

        viewModelScope.launch {
            try {
                _movieProperty.value = MovieApi.retrofitService.getMovieList(apiKey).movieList
                //_gelenDeger.value = MovieApi.retrofitService.getMovieList(apiKey)
            }catch (e: Exception){
                Timber.d(e, e.message)
                Log.i("ListMovieHata", "$e.message")
            }
        }
    }

}


//val movieObjects: MovieObjects? = jsonAdapter.fromJson(MovieApi.retrofitService.getMovieList(apiKey))
// _movieProperty.value = movieObjects?.movieList?.get(1)