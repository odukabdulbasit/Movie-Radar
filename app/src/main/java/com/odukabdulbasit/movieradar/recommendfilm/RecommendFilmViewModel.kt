package com.odukabdulbasit.movieradar.recommendfilm

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.odukabdulbasit.movieradar.Movie
import com.odukabdulbasit.movieradar.database.getDatabase
import com.odukabdulbasit.movieradar.repository.MoviesRepository
import java.lang.IllegalArgumentException

class RecommendFilmViewModel(app: Application) : ViewModel() {

    private val _isPhoneShaked = MutableLiveData<Boolean>()
    val isPhoneShaked : LiveData<Boolean>
    get() = _isPhoneShaked


    private val database = getDatabase(app)
    private val moviesRepository = MoviesRepository(database)


    //bu degerin icinde tum liste var ui kisminda gidip bundan random deger alacam
    val randomMovie = moviesRepository.getRandomMovie(rand(0, 15))

    init {
        _isPhoneShaked.value = false
    }


    //sallaninca true olacak  random film cekildikten sonra yine false yapilacak
    fun setPhoneShaked(){
        _isPhoneShaked.value = true
        Log.i("RecommandViewModel", "setPhoneShaked called")
        Log.i("isPhoneShaked", "${_isPhoneShaked.value}")

    }

    fun completePhoneShaked(){
        _isPhoneShaked.value = false
    }

    private fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
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