package com.example.basic_todo_app.ui.screens.taskscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basic_todo_app.DatastoreManager
import com.example.basic_todo_app.data.Task
import com.example.basic_todo_app.getImageFromAssets
import com.example.basic_todo_app.ui.components.ModalSheet
import com.example.basic_todo_app.ui.theme.BasictodoappTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID


@ExperimentalMaterial3Api
@Composable
fun TaskScreen(
    goToSettings: () -> Unit,
//    Using the view model factory to create the view model
    taskViewModel: TaskViewModel = viewModel(factory = TaskViewModel.Factory),
) {

//    To Control the bottom sheet
    val bottomSheetState = rememberModalBottomSheetState()

// To call the bottomSheet hide and show function
    val coroutineScope = rememberCoroutineScope()

//    pass the task to the modal on update and null on add
    var currentTask by remember { mutableStateOf<Task?>(null) }

//    Variable to control the opening and closing of the modal
    var openModal by remember { mutableStateOf(false) }

//    Context object to initialize the database instance
    val context = LocalContext.current

//    Datastore instance initialization
    val datastoreManager = DatastoreManager(context)

//    Observing the stored number through the flow
    val number by datastoreManager.getNumber().collectAsState(initial = null)

//    used to pass the appropriate mode to the modal
    var mode by remember { mutableStateOf("Add") }

//    TaskList for displaying the tasks
    val taskList = taskViewModel.taskList.collectAsState().value

//    Tasklist grouped by date
    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)

    val groupedTasks: List<Pair<String, List<Task>>> = buildList {
        // One group for all overdue tasks
        val overdueTasks = taskList.filter { it.date.isBefore(today) }
        if (overdueTasks.isNotEmpty()) {
            add("Overdue" to overdueTasks)
        }

        // One group for today
        val todayTasks = taskList.filter { it.date.isEqual(today) }
        if (todayTasks.isNotEmpty()) {
            add("Today" to todayTasks)
        }

        // One group for tomorrow
        val tomorrowTasks = taskList.filter { it.date.isEqual(tomorrow) }
        if (tomorrowTasks.isNotEmpty()) {
            add("Tomorrow" to tomorrowTasks)
        }

        // Separate group for each future date beyond tomorrow
        val futureTasks = taskList.filter { it.date.isAfter(tomorrow) }
            .groupBy { it.date }
            .toSortedMap()

        futureTasks.forEach { (date, tasks) ->
            val label = date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
            add(label to tasks)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Todo App",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            goToSettings()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                expandedHeight = 45.dp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    making current task null in the add case
                    currentTask = null
//                    setting mode to Add
                    mode = "Add"
//                    finally opening the modal
                    openModal = true
                },
                modifier = Modifier.padding(8.dp),
                 containerColor = MaterialTheme.colorScheme.secondaryContainer,
                 contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                )
            }
        },
    ) { paddingValues ->
        if (number != null) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Background Image
                    Image(
                        bitmap = getImageFromAssets(LocalContext.current, "theme_${number}.webp")!!,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(top = 16.dp),
                ) {
                    groupedTasks.forEach { (label, tasksForDate) ->
                        item {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 16.dp),
                                color = Color.White
                            )
                        }

                        items(tasksForDate, key = { it.id }) { task ->
                            TaskItem(
                                task = task,
                                onRemove = { taskViewModel.removeTask(it) },
                                onEdit = { it ->
                                    currentTask = it
                                    mode = "Edit"
                                    openModal = true
                                },
                                onCheckedChange = { isChecked ->
                                    taskViewModel.updateTask(task.copy(isCompleted = isChecked))
                                }
                            )
                        }
                    }
                }

            }
            if (openModal) {
                ModalSheet(
//                passing the task or null
                    task = currentTask,
//                passing mode
                    mode = mode,
//                hiding the modal on cancel
                    onCancel = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            openModal = false
                        }
                    },
                    //                saving the task on save or edit
                    onSave = { task ->
                        if (mode == "Add") {
                            taskViewModel.addTask(task)
                        } else {
                            taskViewModel.updateTask(task)
                        }
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            openModal = false
                        }
                    },
//                hiding the modal on dismiss
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                            openModal = false
                        }

                    },
//                passing the bottom sheet state
                    bottomSheetState = bottomSheetState
                )
            }
        }
        else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f), // Optional: Ensure it’s above other content
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Red, // Customize color if you like
                    strokeWidth = 4.dp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    onRemove: (Task) -> Unit,
    onEdit: (Task) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
//    state for Swippable box
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { it ->
            when(it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onRemove(task)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    onEdit(task)
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
            Item(
                task = task,
                onCheckedChange = onCheckedChange,
                containerColor = Color(0x34333232),
                headlineColor = Color(0xFFFFFFFF),
                supportingColor = Color(0xFFFFFFFF),
            )
        }
    )
}

//for background of swippable box
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
//    Changing the color based on swipe direction
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> Color(0x77FF1744)
        SwipeToDismissBoxValue.EndToStart -> Color(0x854CAF50)
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
//        Changing the icon based on swipe direction
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


@Composable
fun Item(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    containerColor: Color,
    headlineColor: Color,
    supportingColor: Color
) {
    ListItem(
        headlineContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFFFFFFFF),
                        uncheckedColor = Color(0xFFFFFFFF),
                        checkmarkColor = Color(0xFF000000)
                    )
                )
                Text(
                    text = task.task,
                    style = MaterialTheme.typography.titleLarge.copy(
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    )
                )
            }
        },
        supportingContent = {
            Row(
                modifier= Modifier.padding(start = 48.dp)
            ) {
                Text(
                    text = "${task.time}", // or format how you prefer
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ListItemDefaults.colors(
            containerColor = containerColor,
            headlineColor = headlineColor,
            supportingColor = supportingColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ItemsPreview() {
    BasictodoappTheme {
            Item(
                task = Task(
                    id = UUID.randomUUID(),
                    task = "Task 1",
                    date = LocalDate.now(),
                    time = LocalTime.now(),
                    isCompleted = false
                ),
                onCheckedChange = {},
                containerColor = Color.Black,
                headlineColor = Color(0xFFFFFFFF),
                supportingColor = Color(0xFFFFFFFF)
            )
    }
}