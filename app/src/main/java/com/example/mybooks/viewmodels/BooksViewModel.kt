package com.example.mybooks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooks.models.Book
import com.example.mybooks.repositories.BookRepository
import com.example.mybooks.screens.toBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class BooksViewModel(private val repository: BookRepository): ViewModel() {
    private val _bookListState = MutableStateFlow(listOf<Book>())
    val bookListState: StateFlow<List<Book>> = _bookListState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllBooks().distinctUntilChanged()
                .collect{ listOfBooks ->
                    if(listOfBooks.isNullOrEmpty()){
                        Log.d("BooksViewModel", "Empty books")
                    } else {
                        _bookListState.value = listOfBooks
                    }
                }
        }
    }
    suspend fun getBookById(dbId: Long?) = bookListState.value.filter { it.dbId == dbId }
    suspend fun updateReadBooks(book: Book) {
        book.isRead =! book.isRead
        repository.updateBook(book)
    }

    suspend fun removeBook(){
        //val book = Mut
       // repository.deleteBook()
    }

}