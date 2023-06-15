package com.example.mybooks.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mybooks.common.InjectorUtils

import com.example.mybooks.viewmodels.BooksViewModel
import com.example.mybooks.widgets.BookRow
import com.example.mybooks.widgets.HomeTopAppBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    val viewModel: BooksViewModel = viewModel(
        factory = InjectorUtils.provideBooksViewModelFactory(LocalContext.current)
    )
    val searchQueryState = remember { mutableStateOf("") }

    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Home",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.AddBookScreen.route) }) {
                    Row {
                        Text(
                            text = "New Book",
                            modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp)
                        )
                    }
                }
            },
            sortMode = viewModel.sortMode.value,
            onSortModeChanged = { viewModel.updateSortMode(it) },
            searchQuery = searchQueryState.value,
            onSearchQueryChanged = { viewModel.searchBooks(it) }
        )
    }) { padding ->
        MainContent(modifier = Modifier.padding(padding), viewModel = viewModel, searchQueryState = searchQueryState)
    }
}

@Composable
fun MainContent(modifier: Modifier, viewModel: BooksViewModel, searchQueryState: MutableState<String>) {
    val coroutineScope = rememberCoroutineScope()
    val bookListState by viewModel.bookListState.collectAsState()
    val sortedBookList by viewModel.sortedBookList

    val filteredBooks = remember(searchQueryState.value, bookListState) {
        if (searchQueryState.value.isNotBlank()) {
            val searchQueryLower = searchQueryState.value.lowercase()
            bookListState.filter {
                it.title.lowercase().contains(searchQueryLower) ||
                        it.author.lowercase().contains(searchQueryLower)
            }
        } else {
            bookListState
        }
    }

    Column(modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQueryState.value,
            onValueChange = { searchQueryState.value = it },
            label = { Text("Suche nach Büchern") }
        )

        LazyColumn {
            if (sortedBookList.isEmpty() && filteredBooks.isEmpty()) {
                item {
                    Text(
                        text = "Es wurden noch keine Bücher angelegt",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            } else {
                items(filteredBooks) { bookItem ->
                    BookRow(book = bookItem) { book ->
                        coroutineScope.launch {
                            viewModel.updateReadBooks(book)
                        }
                    }
                }
            }
        }
    }
}
