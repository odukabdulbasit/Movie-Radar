package com.odukabdulbasit.movieradar

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.odukabdulbasit.movieradar.listofmovie.MovieListAdapter

@BindingAdapter("imageUrl")
fun loadImage(view : ImageView, url : String?){
    Glide
        .with(view.context)
        .load("https://image.tmdb.org/t/p/w500/$url")
        .apply(
            RequestOptions()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image))
        .into(view);
}

@BindingAdapter("listData")
fun bindRecyclerViewList(recyclerView: RecyclerView, movieProp : List<Movie>?){

    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(movieProp)
}