package com.example.basic_todo_app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties



@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit, // Callback when the dialog is dismissed
    confirmButton: @Composable () -> Unit,  // Composable for the confirm button
    dismissButton: @Composable (() -> Unit), // Composable for the dismiss button
    content: @Composable () -> Unit, // Composable for the content of the dialog we will pass timePicker
) {

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier.width(IntrinsicSize.Min) // Adjust width to fit the children
                .height(IntrinsicSize.Min) // Adjust height to fit the children
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select Time",
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 20.dp),
                    style = MaterialTheme.typography.headlineLarge
                )
//                Display the content composable passed to the dialog
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
//                    Display the dismiss button passed to the dialog
                    dismissButton()
//                    Display the confirm button passed to the dialog
                    confirmButton()
                }
            }
        }
    }
}