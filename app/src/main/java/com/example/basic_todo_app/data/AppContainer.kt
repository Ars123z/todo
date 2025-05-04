package com.example.basic_todo_app.data

import android.content.Context
import com.example.basic_todo_app.repository.OfflineTaskRepo
import com.example.basic_todo_app.repository.TaskRepo

//interface for the new container
interface AppContainer {
    val taskRepo: TaskRepo
}

// implementation of the container
class DefaultAppContainer(private val context: Context): AppContainer {
    override val taskRepo: TaskRepo by lazy {
//        passing the actual dao to the repo from the database instance
        OfflineTaskRepo(TaskDatabase.getDatabase(context).taskDao())
    }
}