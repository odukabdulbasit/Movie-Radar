package com.odukabdulbasit.movieradar.detailofmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.odukabdulbasit.movieradar.R
import com.odukabdulbasit.movieradar.databinding.FragmentMovieDetailBinding

class MovieDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentMovieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        val selectedMovieProp = MovieDetailArgs.fromBundle(arguments!!).selectedMovie
        binding.selectedMovie = selectedMovieProp
        return binding.root
    }
}