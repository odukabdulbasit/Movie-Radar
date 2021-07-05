package com.odukabdulbasit.movieradar.recommendfilm

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.odukabdulbasit.movieradar.database.getDatabase
import com.odukabdulbasit.movieradar.repository.MoviesRepository
import java.lang.IllegalArgumentException

class RecommendFilmViewModel(app: Application) : ViewModel() {

    private val _isPhoneShaked = MutableLiveData<Boolean>()
    val isPhoneShaked : LiveData<Boolean>
    get() = _isPhoneShaked

    private val database = getDatabase(app)
    private val moviesRepository = MoviesRepository(database)

    //whole movie list
    val movieProperty = moviesRepository.movies

    init {
        _isPhoneShaked.value = false
    }


    //sallaninca true olacak  random film cekildikten sonra yine false yapilacak
    fun setPhoneShaked(){
        _isPhoneShaked.value = true
        Log.i("RecommandViewModel", "setPhoneShaked called")
        Log.i("isPhoneShaked", "${_isPhoneShaked.value}")
    }

    class Factory(val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RecommendFilmViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return RecommendFilmViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}