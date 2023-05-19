package com.example.mybooks.common

import android.content.Context

import com.example.mybooks.data.BookDatabase
import com.example.mybooks.repositories.BookRepository
import com.example.mybooks.viewmodels.AddBookViewModelFactory
import com.example.mybooks.viewmodels.BooksViewModelFactory

object InjectorUtils {
    private fun getBookRepository(context: Context): BookRepository{
        return BookRepository.getInstance(BookDatabase.getDatabase(context.applicationContext).bookDao())
    }

    fun provideBooksViewModelFactory(context: Context): BooksViewModelFactory{
        val repository = getBookRepository(context)
        return BooksViewModelFactory(repository)
    }

    fun provideAddBookViewModelFactory(context: Context): AddBookViewModelFactory{
        val repository = getBookRepository(context)
        return AddBookViewModelFactory(repository)
    }
}