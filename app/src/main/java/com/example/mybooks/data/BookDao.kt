package com.example.mybooks.data

import androidx.room.*
import com.example.mybooks.models.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun add(book : Book)
    @Query("SELECT * FROM book WHERE lower(isbn) LIKE lower(:query) OR lower(author) LIKE lower(:query)")
    fun searchBooks(query: String): Flow<List<Book>>

    @Update
    suspend fun update(book : Book)

    @Delete
    suspend fun deleteBook(book : Book)

    @Query("SELECT * from book")
    fun getAll(): Flow<List<Book>>

    @Query("SELECT * from book WHERE isRead = 1")
    fun getReadBooks(): Flow<List<Book>>

    @Query("SELECT * from book WHERE dbId =:id")
    fun get(id: Long): Flow<Book>

    @Query("DELETE from book")
    suspend fun deleteAll()
}