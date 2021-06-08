package com.odukabdulbasit.movieradar.listofmovie

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odukabdulbasit.movieradar.Movie
import com.odukabdulbasit.movieradar.databinding.MovieListItemBinding

class MovieListAdapter(val onClickListener: OnClickListener) : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallBack){

    object DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(movieProperty : Movie){
                binding.movieProperty = movieProperty
                binding.executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MovieListAdapter.MovieViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
        val movieProp = getItem(position)
        holder.bind(movieProp)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(movieProp)
        }
    }

    class OnClickListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }

}