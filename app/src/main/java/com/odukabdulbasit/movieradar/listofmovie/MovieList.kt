package com.odukabdulbasit.movieradar.listofmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.movieradar.R
import com.odukabdulbasit.movieradar.databinding.FragmentMovieListBinding
import com.odukabdulbasit.movieradar.databinding.MovieListItemBinding

class MovieList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMovieListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)

        val viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.movieListProperty = viewModel

        //burada onClick islemleri yapilacak
        binding.recyclerView.adapter = MovieListAdapter(MovieListAdapter.OnClickListener{ movie ->
            findNavController().navigate(MovieListDirections.actionMovieListToMovieDetail(movie))
        })


        return binding.root
    }

}