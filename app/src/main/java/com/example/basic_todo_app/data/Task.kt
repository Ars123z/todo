package com.example.basic_todo_app.data

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID


data class Task(
    val id: UUID = UUID.randomUUID(),
    var date: LocalDate,
    var time: LocalTime,
    var task: String,
    var isCompleted: Boolean = false
)