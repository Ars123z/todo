package com.example.basic_todo_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID


//Task object to store in the database
@Entity(
    tableName = "tasks",
)
data class Task(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var date: LocalDate,
    var time: LocalTime,
    var task: String,
    var isCompleted: Boolean = false
)