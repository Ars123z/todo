package com.example.basic_todo_app.data

import android.content.Context
import com.example.basic_todo_app.repository.OfflineTaskRepo
import com.example.basic_todo_app.repository.TaskRepo

interface AppContainer {
    val taskRepo: TaskRepo
}

class DefaultAppContainer(private val context: Context): AppContainer {
    override val taskRepo: TaskRepo by lazy {
        OfflineTaskRepo(TaskDatabase.getDatabase(context).taskDao())
    }

}