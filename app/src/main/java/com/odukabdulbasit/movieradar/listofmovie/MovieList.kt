package com.odukabdulbasit.movieradar.listofmovie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.movieradar.R
import com.odukabdulbasit.movieradar.databinding.FragmentMovieListBinding

class MovieList : Fragment() {

    val viewModel : MovieListViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, MovieListViewModel.Factory(activity.application)).get(MovieListViewModel::class.java)
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
           // findNavController().navigate(MovieLis.actionMovieListToMovieDetail(movie))
            findNavController().navigate(MovieListDirections.actionMovieListToMovieDetail(movie))
        })


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.recommend_film -> {
            //findNavController().navigate(MovieListDirections.actionMovieListToRecommendFilmFragment())
            findNavController().navigate(MovieListDirections.actionMovieListToRecommendFilmFragment())
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}