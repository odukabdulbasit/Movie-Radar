package com.odukabdulbasit.movieradar.recommendfilm

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.odukabdulbasit.movieradar.listofmovie.ListViewModel
import java.lang.IllegalArgumentException

class RecommendFilmViewModel(app: Application) : ViewModel() {

    private var _isPhoneShaked = MutableLiveData<Boolean>()
    val isPhoneShaked : LiveData<Boolean>
    get() = _isPhoneShaked

    init {
        _isPhoneShaked.value = false
    }


    //sallaninca true olacak  random film cekildikten sonra yine false yapilacak
    fun setPhoneShaked(){
        _isPhoneShaked.value = true
        Log.i("RecommandViewModel", "setPhoneShaked called")
        Log.i("isPhoneShaked", "$isPhoneShaked")
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