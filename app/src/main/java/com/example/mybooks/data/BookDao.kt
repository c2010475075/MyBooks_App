package com.example.mybooks.data

import androidx.room.*
import com.example.mybooks.models.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun add(book : Book)

    @Update
    suspend fun update(book : Book)

    @Delete
    fun delete(book : Book)

    @Query("SELECT * from book")
    fun getAll(): Flow<List<Book>>

    @Query("SELECT * from book WHERE isRead = 1")
    fun getReadBooks(): Flow<List<Book>>

    @Query("SELECT * from book WHERE dbId =:id")
    fun get(id: Long): Flow<Book>

    @Query("DELETE from book")
    suspend fun deleteAll()
}