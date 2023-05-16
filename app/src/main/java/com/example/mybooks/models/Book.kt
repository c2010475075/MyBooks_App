package com.example.mybooks.models

data class Book(
    val title: String = "",
    val author: String = "",
    val year: Int,
    val isbn: String = "",
    ) {
}
fun getBooks(): List<Book> {
    return listOf(
        Book(
            title = "Herr der Ringe - Die zwei Türme",
            author = "J. R. R. Tolkien",
            year = 2012,
            isbn = "978-3-608-93982-8"
        ),

        Book(
            title = "Harry Poter",
            author = "J. K. Rowling",
            year = 2005,
            isbn = "978-3-551-35401-3",
        ),

        Book(
            title = "Augensammler",
            author = "Sebastian Fitzek",
            year = 2009,
            isbn = "978-3-426-50375-1"
        ),

        Book(
            title = "Die Leiden des jungen Werthers",
            author = "Johann Wolfgang von Goethe",
            year = 1774,
            isbn = "978-3-15-000067-0",
        ),

        Book(
            title = "Offline",
            author = "Arno Strobel",
            year = 2019,
            isbn = "978-3-596-70394-4",
        )
    )
}