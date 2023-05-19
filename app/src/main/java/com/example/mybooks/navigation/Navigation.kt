package com.example.mybooks.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mybooks.screens.*
import com.example.mybooks.viewmodels.AddBookViewModel
import com.example.mybooks.viewmodels.BooksViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController)
        }


        
        composable(Screen.AddBookScreen.route) {
            AddBookScreen(navController = navController)
        }


    }
}