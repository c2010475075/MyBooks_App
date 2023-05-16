package com.example.mybooks.screens

sealed class AddBookUIEvent{
    object TitleChanged : AddBookUIEvent()
    object YearChanged: AddBookUIEvent()
    object AuthorChanged: AddBookUIEvent()
    object ISBNChanged: AddBookUIEvent()
    object submit: AddBookUIEvent()
}
