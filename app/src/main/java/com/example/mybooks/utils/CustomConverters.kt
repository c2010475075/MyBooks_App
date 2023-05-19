package com.example.mybooks.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class CustomConverters {


    @TypeConverter
    fun stringListToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}