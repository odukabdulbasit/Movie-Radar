package com.odukabdulbasit.movieradar.api

import com.odukabdulbasit.Constants.BASE_URL
import com.odukabdulbasit.movieradar.Movie
import com.odukabdulbasit.movieradar.MovieObjects
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService{

    @GET("/3/discover/movie")
    suspend fun getMovieList(
       @Query("api_key") apiKey : String,
    @Query("popularity")  popularity : String
    ) : MovieObjects
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object MovieApi {
    val movieService : MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
