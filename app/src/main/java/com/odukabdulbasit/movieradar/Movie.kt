package com.odukabdulbasit.movieradar

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class MovieObjects(
    val page : Int,
    @Json(name = "results") val movieList : List<Movie>,
    val total_results: Int,
    val total_pages: Int
)

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val release_date: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: Array<Int>
    //val genre_ids: List<Genre>,
    //@Json(name = "genre_ids") val genres: List<Genre>,
) : Parcelable

@Parcelize
data class Genre(val id: Int, val name: String) : Parcelable