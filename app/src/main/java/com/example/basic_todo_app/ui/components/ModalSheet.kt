package com.example.basic_todo_app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.basic_todo_app.data.Task
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSheet(
    task: Task?,
    mode: String,
    onCancel: () -> Unit,
    onSave: (Task) -> Unit,
    onDismiss: () -> Unit, // Needed to close the bottom sheet externally
    bottomSheetState: SheetState // Pass in ModalBottomSheetState from parent
) {
//    Variable to control the date dialogue box
    var openDatePicker by remember { mutableStateOf(false) }

//    Variable to control the time dialogue box
    var openTimePicker by remember { mutableStateOf(false) }

//    the interaction source need to capture click on the date field
    val dateSource = remember { MutableInteractionSource() }

//    the interaction source to capture the click on the time field
    val timeSource = remember { MutableInteractionSource() }

    LaunchedEffect(dateSource) {
//        Capture and react to press interactions on the date field
        dateSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                openDatePicker = true
            }
        }
    }

    LaunchedEffect(timeSource) {
//        Capture and react to press interactions on the time field
        timeSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                openTimePicker = true
            }
        }
    }

//    variable to hold the task name or null
    var taskName by remember { mutableStateOf<String?>(task?.task) }

//    Variable to hold task date or null
    var date by remember { mutableStateOf<LocalDate?>(task?.date) }

//    Variable to hold the task time or null
    var time by remember { mutableStateOf<LocalTime?>(task?.time) }

//    state to pass to the date picker with initial date selection
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date?.toEpochDay()?.times(86400000) ?: System.currentTimeMillis()
    )

//    stte to pass to the time picker with initial time selection
    val timePickerState = rememberTimePickerState(
        initialHour = time?.hour ?: LocalTime.now().hour,
        initialMinute = time?.minute ?: LocalTime.now().minute,
        is24Hour = false
    )

//    Actual Bottom Sheet
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        containerColor = Color(0x4B000000),
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
//            Setting the heading based on mode
            Text(
                text = if (mode == "Edit") "Edit Task" else "Add Task",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

//            text field for task name
            OutlinedTextField(
                value = taskName ?: "",
                onValueChange = { taskName = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                label = {
                    Text(
                    text = "Task Name",
                        color = Color.White
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

//            Text field for the date
            OutlinedTextField(
                value = date?.toString() ?: "",
                interactionSource = dateSource,
                onValueChange = {},
                label = {
                    Text(
                        text = "Date",
                        color = Color.White
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Pick Date",
                        tint = Color.White
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

//            Text field for the time
            OutlinedTextField(
                value = time?.format(DateTimeFormatter.ofPattern("hh:mm a")) ?: "",
                onValueChange = {},
                interactionSource = timeSource,
                label = {
                    Text(
                        text = "Time",
                        color = Color.White
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Pick Time",
                        tint = Color.White
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

//            Cancel and save or update button
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,            // A rich green (Material Design primary green)
                        contentColor = Color.White,
                    ),
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF4CAF50), // Green border
                            shape = ShapeDefaults.Medium
                        )
                ) {
                    Text(
                        text= "Cancel",
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    val name = taskName ?: return@Button
                    val pickedDate = date ?: return@Button
                    val pickedTime = time ?: return@Button

//                    in the task screen to call the appropriate function based on the mode
//                    Copying the task and updating necessary the fields
                    if (mode == "Edit" && task != null) {
                        val updatedTask = task.copy(
                            task = name,
                            date = pickedDate,
                            time = pickedTime,
                        )
                        onSave(updatedTask)
                    } else {
//                        Creating a new task
                        onSave(Task(task = name, date = pickedDate, time = pickedTime))
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),            // A rich green (Material Design primary green)
                    contentColor = Color.White,                    // White text for contrast
                    disabledContainerColor = Color(0xFFBDBDBD),     // Medium gray for disabled background
                    disabledContentColor = Color(0xFFEEEEEE)        // Light gray text for disabled state
                ),
                enabled = taskName != null && date != null && time != null,
                shape = ShapeDefaults.Medium
            ) {
                Text(
                    text= if (mode == "Edit") "Update" else "Save",
                )
            }
            }
        }
    }

//    opening the date picker dialogue box
    if (openDatePicker) {
        DatePickerDialog(
            onDismissRequest = { openDatePicker = false },
            confirmButton = {
                Button(onClick = {
//                    when we chose a date it will be converted to millis and then to LocalDate
//                    the selected datemillis is actually the count in millisecond from the linux(1 jan 1970) epoch to the first millisecond of the selected date
                    datePickerState.selectedDateMillis?.let { millis ->
//                        converted to the appropriate date in utc by dividing by 86400000 and then to local date
                        date = LocalDate.ofEpochDay(millis / 86_400_000)
                        openDatePicker = false
                    }
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
//            the date picker with the state
            DatePicker(state = datePickerState)
        }
    }

    if (openTimePicker) {
        TimePickerDialog(
            onDismissRequest = { openTimePicker = false },
            confirmButton = {
                Button(onClick = {
//                    getting the time from time picker state
                    time = LocalTime.of(timePickerState.hour, timePickerState.minute)
                    openTimePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { openTimePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
//            time picker with the appropriate state
            TimePicker(state = timePickerState)
        }
    }
}


