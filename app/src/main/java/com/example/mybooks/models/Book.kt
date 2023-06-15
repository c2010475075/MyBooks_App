package com.example.mybooks.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val dbId: Long = 0,
    @ColumnInfo(name = "original_id")
    val title: String = "",
    val author: String = "",
    val year: Int,
    val isbn: String = "",
    var isRead : Boolean = false
    ) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
