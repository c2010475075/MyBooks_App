package com.example.mybooks.widgets



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mybooks.models.Book
import com.example.mybooks.ui.theme.Shapes
import com.example.mybooks.viewmodels.BooksViewModel
import kotlinx.coroutines.launch


@Composable
fun BookRow(
    book: Book,
    modifier: Modifier = Modifier,
    onRead: (Book) -> Unit = {},
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = Shapes.large,
        elevation = 10.dp
    ) {
        Column ( horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
               //DeleteIcon(book)
                DoneIcon(book, onRead)
                DeleteIcon(book)
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "edit book")
                }


            }

            BookDetails(modifier = Modifier.padding(12.dp), book = book)
        }
    }
}


@Composable
fun DoneIcon(book: Book, onRead: (Book) -> Unit  ) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Icon(modifier = Modifier.clickable { onRead(book) },

            tint = if(book.isRead) {
                Color.Blue
            } else {
                Color.LightGray
            },
            imageVector = Icons.Default.Done,
            contentDescription = "book already read")


    }
}
@Composable
fun DeleteIcon(book: Book,bookViewModel: BooksViewModel = viewModel() ) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        contentAlignment = Alignment.TopStart
    ){
        IconButton(onClick = { coroutineScope.launch {bookViewModel.deleteBook(book) }}){

            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete book")

            }
        }
    }



@Composable
fun BookDetails(modifier: Modifier = Modifier, book: Book) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

            Text(
                book.title,
                modifier = Modifier.weight(6f),
                style = MaterialTheme.typography.h6
            )

            Text(
                "Author: " + book.author,
                modifier = Modifier.weight(6f),
                style = MaterialTheme.typography.h6
            )




        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { expanded =!expanded }) {
            Icon(imageVector =
            if (expanded) Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown,
                contentDescription = "expand",
                modifier = Modifier
                    .size(25.dp),
                tint = Color.DarkGray
            )
        }
    }

    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column (modifier = modifier) {
            val newValue = "${book.isbn[0]}${book.isbn[1]}${book.isbn[2]}-${book.isbn[3]}-${book.isbn[4]}${book.isbn[5]}${book.isbn[6]}-${book.isbn[7]}${book.isbn[8]}${book.isbn[9]}${book.isbn[10]}${book.isbn[11]}-${book.isbn[12]}"
            Text(text = "Released: ${book.year}", style = MaterialTheme.typography.caption)
            Text(text = "ISBN: $newValue", style = MaterialTheme.typography.caption)

            Divider(modifier = Modifier.padding(3.dp))

        }
    }
}

