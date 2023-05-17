package com.example.mybooks.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Book(
    val title: String = "",
    val author: String = "",
    val year: Int,
    val isbn: String = "",
    val isRead : Boolean = false
    ) {
    var bookIsRead by mutableStateOf(isRead)
}
fun getBooks(): List<Book> {
    return listOf(
        Book(
            title = "Herr der Ringe - Die zwei Türme",
            author = "J. R. R. Tolkien",
            year = 2012,
            isbn = "9783608939828"
        ),

        Book(
            title = "Harry Poter",
            author = "J. K. Rowling",
            year = 2005,
            isbn = "9783551354013",
        ),

        Book(
            title = "Augensammler",
            author = "Sebastian Fitzek",
            year = 2009,
            isbn = "9783426503751"
        ),

        Book(
            title = "Die Leiden des jungen Werthers",
            author = "Johann Wolfgang von Goethe",
            year = 1774,
            isbn = "9783150000670",
        ),

        Book(
            title = "Offline",
            author = "Arno Strobel",
            year = 2019,
            isbn = "9783596703944",
        )
        )

}