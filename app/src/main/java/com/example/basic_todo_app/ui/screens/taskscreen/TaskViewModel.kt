package com.example.basic_todo_app.ui.screens.taskscreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basic_todo_app.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel() : ViewModel() {
    private val _taskList = MutableStateFlow<List<Task>>(emptyList())

    val taskList: StateFlow<List<Task>> = _taskList.asStateFlow()

    fun addTask(task: Task) {
        viewModelScope.launch {
            _taskList.update { it + task }
        }
    }

    fun removeTask(task: Task) {
        viewModelScope.launch {
            _taskList.update { it.filter { it.id != task.id } }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            _taskList.update {
                it.map {
                    if (task.id == it.id) task else it
                }
            }
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            _taskList.update {
                it.map {
                    if (task.id == it.id ) {
                        if (it.isCompleted) task.copy(isCompleted = false) else task.copy(isCompleted = true)
                    } else it
                }
            }
        }
    }
}