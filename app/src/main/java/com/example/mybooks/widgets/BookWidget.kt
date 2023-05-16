package com.example.mybooks.widgets



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mybooks.models.Book
import com.example.mybooks.models.getBooks
import com.example.mybooks.ui.theme.Shapes

@Preview
@Composable
fun BookRow(
    book: Book = getBooks()[0],
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = Shapes.large,
        elevation = 10.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                DoneIcon()
            }

            BookDetails(modifier = Modifier.padding(12.dp), book = book)
        }
    }
}


@Composable
fun DoneIcon() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Icon(
            tint = MaterialTheme.colors.secondary,
            imageVector = Icons.Default.Done,
            contentDescription = "book already read")
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

        IconButton(
            modifier = Modifier.weight(1f),
            onClick = { expanded = !expanded }) {
            Icon(imageVector =
            if (expanded) Icons.Filled.KeyboardArrowDown
            else Icons.Filled.KeyboardArrowUp,
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
            Text(text = "Author: ${book.author}", style = MaterialTheme.typography.caption)
            Text(text = "Released: ${book.year}", style = MaterialTheme.typography.caption)
            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.caption)

            Divider(modifier = Modifier.padding(3.dp))

        }
    }
}

