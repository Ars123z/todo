package com.example.basic_todo_app.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {

//    variable to hold the date formatter
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

//    Variable to hold the time formatter
    private val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME


//    Function to convert date to string
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(dateFormatter)
    }

//    Function to convert string to date
    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it, dateFormatter) }
    }

//    Function to convert time to string
    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? {
        return time?.format(timeFormatter)
    }

//    Function to convert string to time
    @TypeConverter
    fun toLocalTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(it, timeFormatter) }
    }
}
