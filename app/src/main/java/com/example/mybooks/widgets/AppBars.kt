package com.example.mybooks.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTopAppBar(arrowBackClicked: () -> Unit = {}, content: @Composable () -> Unit){
    TopAppBar(elevation = 3.dp) {
        Row {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable {
                    arrowBackClicked()
                }
            )

            Spacer(modifier = Modifier.width(20.dp))

            content()
        }
    }
}

@Composable
fun HomeTopAppBar(
    title: String = "default",
    menuContent: @Composable () -> Unit,
    sortMode: SortMode,
    searchQuery: String,
    onSortModeChanged: (SortMode) -> Unit,
    onSearchQueryChanged: (String) -> Unit
){
    var showMenu by remember { mutableStateOf(false) }


    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchQuery,
        onValueChange = { onSearchQueryChanged(it) },
        label = { Text("Search Books") }
    )

    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            IconButton(
                onClick = { onSortModeChanged(SortMode.ASCENDING) },
                content = { Icon(Icons.Default.KeyboardArrowUp, "Sortiere aufsteigend") }
            )

            IconButton(
                onClick = { onSortModeChanged(SortMode.DESCENDING) },
                content = { Icon(Icons.Default.KeyboardArrowDown, "Sortiere absteigend") }
            )
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                menuContent()
            }
        }
    )
}