package com.example.basic_todo_app

import android.app.Application
import com.example.basic_todo_app.data.AppContainer
import com.example.basic_todo_app.data.DefaultAppContainer


// Application class to hold the container object
class TaskApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}