package com.example.mybooks.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mybooks.common.InjectorUtils
import com.example.mybooks.models.Book
import com.example.mybooks.models.getBooks
import com.example.mybooks.viewmodels.BooksViewModel
import com.example.mybooks.widgets.BookRow
import com.example.mybooks.widgets.HomeTopAppBar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){

    val viewModel: BooksViewModel = viewModel(factory = InjectorUtils.provideBooksViewModelFactory(
        LocalContext.current))

    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Home",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.AddBookScreen.route) }) {
                    Row {
                        Text(text = "New Book", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
            }
        )
    }) { padding ->
        MainContent(modifier = Modifier.padding(padding),
            viewModel = viewModel)
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    viewModel: BooksViewModel
) {
    BookList(
        modifier = modifier,
        viewModel = viewModel
    )
}

@Composable
fun BookList(
    modifier: Modifier = Modifier,
    viewModel: BooksViewModel
) {
    val bookListState by viewModel.bookListState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val items = bookListState // Your list of items

    LazyColumn {
        if (items.isEmpty()) {
            item {
                Text(
                    text = "List is empty",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        } else {
            items(items) {bookItem -> BookRow(book = bookItem,  onRead  = { book ->
                coroutineScope.launch{ viewModel.updateReadBooks(book) }})
            }
        }
    }
}


