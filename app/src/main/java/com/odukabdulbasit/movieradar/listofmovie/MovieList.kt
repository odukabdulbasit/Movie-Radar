package com.odukabdulbasit.movieradar.listofmovie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.movieradar.R
import com.odukabdulbasit.movieradar.databinding.FragmentMovieListBinding
import com.odukabdulbasit.movieradar.databinding.MovieListItemBinding

class MovieList : Fragment() {

    val viewModel : ListViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, ListViewModel.Factory(activity.application)).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMovieListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)


        binding.lifecycleOwner = this
        binding.movieListProperty = viewModel

        //burada onClick islemleri yapilacak
        binding.recyclerView.adapter = MovieListAdapter(MovieListAdapter.OnClickListener{ movie ->
            findNavController().navigate(MovieListDirections.actionMovieListToMovieDetail(movie))
        })


        return binding.root
    }

}