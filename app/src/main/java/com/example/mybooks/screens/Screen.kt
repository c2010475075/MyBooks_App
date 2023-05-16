package com.example.mybooks.screens


sealed class Screen (val route: String) {
    object MainScreen : Screen("main")
    object AddBookScreen : Screen("addBook")
}