package com.example.mybooks.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mybooks.common.Validator
import com.example.mybooks.repositories.BookRepository
import com.example.mybooks.screens.AddBookUiState
import com.example.mybooks.screens.AddBookUIEvent
import com.example.mybooks.screens.hasError
import com.example.mybooks.screens.toBook


// inherit from ViewModel class
class AddBookViewModel(private val repository: BookRepository): ViewModel() {

    var bookUiState by mutableStateOf(AddBookUiState())
        private set

    fun updateUIState(newBookUiState: AddBookUiState, event: AddBookUIEvent){
        var state = AddBookUiState() // this is needed because copy always creates a new instance

        when (event) {
            is AddBookUIEvent.TitleChanged -> {
                val titleResult = Validator.validateBookTitle(newBookUiState.title)
                state = if(!titleResult.successful) newBookUiState.copy(titleErr = true) else newBookUiState.copy(titleErr = false)
            }
            is AddBookUIEvent.YearChanged -> {
                val yearResult = Validator.validateBookYear(newBookUiState.year)
                state = if(!yearResult.successful) newBookUiState.copy(yearErr = true) else newBookUiState.copy(yearErr = false)
            }

            is AddBookUIEvent.AuthorChanged -> {
                val directorResult = Validator.validateBookAuthor(newBookUiState.author)
                state = if(!directorResult.successful) newBookUiState.copy(authorErr = true) else newBookUiState.copy(authorErr = false)
            }

            is AddBookUIEvent.ISBNChanged -> {
                val actorsResult = Validator.validateBookIsbn(newBookUiState.isbn)
                state = if(!actorsResult.successful) newBookUiState.copy(isbnErr = true) else newBookUiState.copy(isbnErr = false)
            }


            else -> {}
        }

        bookUiState = state.copy(actionEnabled = !newBookUiState.hasError())
    }




    suspend fun saveBook() {
        val book = bookUiState.toBook()

        repository.addBook(book)
    }
    suspend fun removeBook(){
       //repository.deleteBook(book = Book())
    }

}