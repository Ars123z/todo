package com.example.basic_todo_app.ui.screens.taskscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.basic_todo_app.TaskApplication
import com.example.basic_todo_app.data.Task
import com.example.basic_todo_app.repository.TaskRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(val taskRepo: TaskRepo) : ViewModel() {
    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: StateFlow<List<Task>> = _taskList.asStateFlow()

    init {
        viewModelScope.launch {
            taskRepo.getAllTasks().collect { tasks ->
                _taskList.value = tasks
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepo.insertTask(task)
        }
    }

    fun removeTask(task: Task) {
        viewModelScope.launch {
            taskRepo.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepo.updateTask(task)
        }
    }


//    because the viewModal doesn't support parameters by default,  we need to create a factory to pass the parameters to the view model
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
//                Getting the application object we created from the view model factory
                val application = (this[APPLICATION_KEY] as TaskApplication)
//                Getting the task repo from the container object we created in the application class
                val taskRepo = application.container.taskRepo
                // Creating the view model with the task repo
                TaskViewModel(taskRepo = taskRepo)
            }
        }
    }
}