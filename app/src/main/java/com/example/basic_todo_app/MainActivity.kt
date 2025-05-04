package com.example.basic_todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.basic_todo_app.ui.screens.taskscreen.TaskScreen
import com.example.basic_todo_app.ui.theme.BasictodoappTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasictodoappTheme {
//                Displaying the task screen
                TaskScreen()
            }
        }
    }
}









