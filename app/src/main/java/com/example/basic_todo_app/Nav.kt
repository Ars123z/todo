package com.example.basic_todo_app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.basic_todo_app.ui.screens.settings.SettingsScreen
import com.example.basic_todo_app.ui.screens.taskscreen.TaskScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nav() {
//    getting controller with remember so that it can keep it's state across recompositions
    val navController = rememberNavController()

//    The container for all the screens in the app
    NavHost(navController = navController, startDestination = "tasks") {
//        displaying TaskScreen when navigating to "tasks"
        composable("tasks") {
            TaskScreen(navController)
        }
//        displaying SettingsScreen when navigating to "settings"
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}