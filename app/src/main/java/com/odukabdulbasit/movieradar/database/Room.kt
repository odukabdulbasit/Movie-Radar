package com.odukabdulbasit.movieradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.odukabdulbasit.movieradar.Converters

@Dao
interface MovieDao{

    //get all movies from database
    @Query("select * from databasemovie")
    fun getMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<DatabaseMovie>)

}

@Database(entities = [DatabaseMovie::class], version = 1)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase(){
    abstract val movieDao : MovieDao
}

private lateinit var INSTANCE : MoviesDatabase

fun getDatabase(context: Context) : MoviesDatabase{
    synchronized(!::INSTANCE.isInitialized){
        INSTANCE = Room.databaseBuilder(context.applicationContext,
        MoviesDatabase::class.java,
        "movies").build()
    }

    return INSTANCE
}