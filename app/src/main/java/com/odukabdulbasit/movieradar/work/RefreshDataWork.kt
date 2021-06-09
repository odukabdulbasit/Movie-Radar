package com.odukabdulbasit.movieradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.odukabdulbasit.movieradar.database.getDatabase
import com.odukabdulbasit.movieradar.repository.MoviesRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = MoviesRepository(database)
        return try {
            repository.refreshMovies()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}