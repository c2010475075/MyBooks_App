package com.example.mybooks.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooks.models.Book
import com.example.mybooks.repositories.BookRepository
import com.example.mybooks.screens.toBook
import com.example.mybooks.widgets.SortMode
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
        book.isRead = !book.isRead
        repository.updateBook(book)
    }

    suspend fun deleteBook(book: Book){
       repository.deleteBook(book)
        val updatedList = _bookListState.value.toMutableList()
        updatedList.remove(book)
        _bookListState.value = updatedList
    }

    private val _searchResultsState = MutableStateFlow<List<Book>>(emptyList())
    val searchResultsState: StateFlow<List<Book>> = _searchResultsState.asStateFlow()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            repository.searchBooks(query)
                .collect { searchResults ->
                    _searchResultsState.value = searchResults
                }
        }
    }
    private val _sortMode = mutableStateOf(SortMode.ASCENDING)
    val sortMode: State<SortMode> = _sortMode

    private val _sortedBookList = mutableStateOf<List<Book>>(emptyList())
    val sortedBookList: State<List<Book>> = _sortedBookList

    // Funktion zum Aktualisieren des Sortiermodus
    fun updateSortMode(mode: SortMode) {
        _sortMode.value = mode
        sortBookList()
    }

    // Funktion zum Sortieren der Buchliste basierend auf dem Sortiermodus
    private fun sortBookList() {
        val unsortedList = bookListState.value.toMutableList()
        _sortedBookList.value = when (_sortMode.value) {
            SortMode.ASCENDING -> unsortedList.sortedBy { it.year }
            SortMode.DESCENDING -> unsortedList.sortedByDescending { it.year }
        }
    }

}