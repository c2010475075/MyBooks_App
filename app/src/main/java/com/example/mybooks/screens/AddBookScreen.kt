package com.example.mybooks.screens



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybooks.viewmodels.BooksViewModel
import com.example.mybooks.R
import com.example.mybooks.widgets.SimpleTextField
import com.example.mybooks.widgets.SimpleTopAppBar

@Composable
fun AddBookScreen(
    navController: NavController,
    booksViewModel: BooksViewModel
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_book))
            }
        },
    ) { padding ->
        MainContent(
            Modifier.padding(padding),
            booksViewModel = booksViewModel,
            navController = navController
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    booksViewModel: BooksViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        BookBody(
            bookUiState = booksViewModel.bookUiState,
            onBookValueChange = { newUiState, event -> booksViewModel.updateUIState(newUiState, event)},
            onSaveClick = {
                booksViewModel.saveBook()
                navController.navigate(Screen.MainScreen.route)
            }
        )
    }
}

@Composable
fun BookBody(
    bookUiState: AddBookUiState,
    onBookValueChange: (AddBookUiState, AddBookUIEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        BookInputForm(bookUiState = bookUiState, onBookValueChange = onBookValueChange)

        Button(
            enabled = bookUiState.actionEnabled,
            onClick = onSaveClick) {
            Text(text = stringResource(R.string.add))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookInputForm(
    bookUiState: AddBookUiState,
    onBookValueChange: (AddBookUiState, AddBookUIEvent) -> Unit,
){
    SimpleTextField(
        value = bookUiState.title,
        label = stringResource(R.string.enter_book_title),
        isError = bookUiState.titleErr,
        errMsg = stringResource(id = R.string.title_required)
    ) { input ->
        onBookValueChange(bookUiState.copy(title = input), AddBookUIEvent.TitleChanged)
    }

    SimpleTextField(
        value = bookUiState.year.toString(),
        label = stringResource(id = R.string.enter_book_year),
        errMsg = stringResource(id = R.string.year_required),
        isError = bookUiState.yearErr
    ) { input ->
        onBookValueChange(bookUiState.copy(year = input.toInt()), AddBookUIEvent.YearChanged)
    }

    SimpleTextField(
        value = bookUiState.author,
        label = stringResource(R.string.enter_author),
        errMsg = stringResource(id = R.string.author_required),
        isError = bookUiState.authorErr,
    ) { input ->
        onBookValueChange(
            bookUiState.copy(author = input),
            AddBookUIEvent.AuthorChanged
        )
    }
SimpleTextField(

        value = bookUiState.isbn,
        label = stringResource(R.string.enter_ISBN),
        errMsg = stringResource(id = R.string.ISBN_required),
        isError = bookUiState.isbnErr,
    ) { input->
         onBookValueChange(
            bookUiState.copy(isbn = input),
            AddBookUIEvent.ISBNChanged
        )
    }

}
