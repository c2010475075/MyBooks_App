package com.example.mybooks.repositories


import com.example.mybooks.models.Book
import com.example.mybooks.data.BookDao
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    suspend fun addBook(book : Book) = bookDao.add(book = book)

    suspend fun updateBook(book : Book) = bookDao.update(book = book)

    suspend fun deleteBook(book : Book) = bookDao.deleteBook(book = book)

    fun getAllBooks() : Flow<List<Book>> = bookDao.getAll()

    fun getReadBooks() : Flow<List<Book>> = bookDao.getReadBooks()

    fun searchBooks(query: String): Flow<List<Book>> = bookDao.searchBooks(query)



    companion object {
        // For Singleton instantiation
        @Volatile private var instance: BookRepository? = null

        fun getInstance(dao: BookDao) =
            instance ?: synchronized(this) {
                instance ?: BookRepository(dao).also { instance = it }
            }
    }
}