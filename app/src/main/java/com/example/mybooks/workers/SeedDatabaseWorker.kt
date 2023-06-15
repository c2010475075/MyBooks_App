package com.example.mybooks.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mybooks.data.BookDatabase

import kotlinx.coroutines.coroutineScope

/**
 * Class that seeds the database at first app start
 */
class SeedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            val database = BookDatabase.getDatabase(applicationContext)
          //  populateDatabase(database)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }}

   /* private suspend fun populateDatabase(database: BookDatabase){
        val dao = database.bookDao()

        dao.deleteAll()
        getBooks().forEach{
            dao.add(it)
        }
    }*/