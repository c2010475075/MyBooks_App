package com.example.mybooks.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mybooks.models.Book
import com.example.mybooks.models.getBooks
import com.example.mybooks.viewmodels.BooksViewModel
import com.example.mybooks.widgets.BookRow
import com.example.mybooks.widgets.HomeTopAppBar

@Composable
fun HomeScreen(navController: NavController = rememberNavController(), booksViewModel: BooksViewModel){
    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Home",
            menuContent = {
                DropdownMenuItem(onClick = { /*navController.navigate(Screen.FavoriteScreen.route) */}) {
                    Row {
                        Text(text = "New Book", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
            }
        )
    }) { padding ->
        MainContent(modifier = Modifier.padding(padding), navController = navController)
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController
) {
    val books = getBooks()
    BookList(
        modifier = modifier,
        navController = navController,
        books = books
    )
}

@Composable
fun BookList(
    modifier: Modifier = Modifier,
    navController: NavController,
    books: List<Book> = getBooks()
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(books) { book ->
            BookRow(
                book = book
            )
        }
    }
}


