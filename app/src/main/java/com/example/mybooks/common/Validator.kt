package com.example.mybooks.common

import java.util.regex.Matcher
import java.util.regex.Pattern


object Validator {


    fun validateBookTitle(title: String): ValidationResult {
        return ValidationResult(title.isNotBlank())
    }

    fun validateBookYear(year: Int): ValidationResult {
        val result = year.toString().isNotBlank() && year != null && year.let { it in 0..2023 }
        return ValidationResult(result)
    }

    fun validateBookAuthor(author: String): ValidationResult {
        return ValidationResult(author.isNotBlank())
    }
//    public static boolean isValidISBNCode(String str)
    fun validateBookIsbn(isbn: String): ValidationResult {
        // Regex to check valid ISBN Code
    //"^(?=(?:[^0-9]*[0-9]){10}(?:(?:[^0-9]*[0-9]){3})?$)[\\d-]+$"  for 10 or 13 digit isbn
        val regex = "^(?=(?:[^0-9]*[0-9]){13}$)[\\d-]+$"
        // Compile the ReGex
        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(isbn)

      val result = isbn.isNotBlank() && isbn != null && m.matches()

        return ValidationResult(result)
    }

}

data class ValidationResult(
    val successful: Boolean
)
