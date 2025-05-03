package com.example.basic_todo_app.ui.screens.taskscreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basic_todo_app.data.Task
import com.example.basic_todo_app.ui.components.ModalSheet
import com.example.basic_todo_app.ui.components.TopBar
import kotlinx.coroutines.launch




@ExperimentalMaterial3Api
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel(factory = TaskViewModel.Factory),
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var currentTask by remember { mutableStateOf<Task?>(null) }
    var openModal by remember { mutableStateOf(false) }
    var mode by remember { mutableStateOf("Add") }
    val taskList = taskViewModel.taskList.collectAsState().value
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            IconButton(
                onClick = {
                    currentTask = null
                    mode = "Add"
                    openModal = true
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top= 16.dp),

        ) {
            items(taskList, key= {it.id}) { task ->
                TaskItem(
                    task = task,
                    onRemove = { taskViewModel.removeTask(it) },
                    onEdit = { it ->
                        currentTask = it
                        mode = "Edit"
                        openModal = true
                    }
                )
            }
        }
        if (openModal) {
            ModalSheet(
                task = currentTask,
                mode = mode,
                onCancel = { coroutineScope.launch { bottomSheetState.hide() }
                            openModal = false
                           },
                onSave = { task ->
                    coroutineScope.launch {
                        if (mode == "Add") {
                            taskViewModel.addTask(task)
                        } else {
                            taskViewModel.updateTask(task)
                        }
                        bottomSheetState.hide()
                    }
                    openModal = false
                },
                onDismiss = { coroutineScope.launch { bottomSheetState.hide() }
                                openModal = false
                            },
                bottomSheetState = bottomSheetState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onRemove: (Task) -> Unit,
    onEdit: (Task) -> Unit
) {
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(task)
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { it ->
            when(it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onRemove(currentItem)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    onEdit(currentItem)
                    Toast.makeText(context, "Item archived", Toast.LENGTH_SHORT).show()
                    return@rememberSwipeToDismissBoxState false

                }
                SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState false
            }
            return@rememberSwipeToDismissBoxState true
        },
        // positional threshold of 25%
        positionalThreshold = { it * .25f }
    )
    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        backgroundContent = { DismissBackground(dismissState)},
        content = {
            ListItem(
                headlineContent = {
                    Text(
                        text = task.task,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                supportingContent = {
                    Row() {
                        Text(
                        text = "${task.date}, ${task.time}", // or format how you prefer
                        style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> Color(0xFFFF1744)
        SwipeToDismissBoxValue.EndToStart -> Color(0xFF4CAF50)
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (dismissState.dismissDirection == SwipeToDismissBoxValue.StartToEnd) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "delete"
            )
        }
        Spacer(modifier = Modifier)
        if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Delete"
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}

