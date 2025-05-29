package com.example.basic_todo_app.ui.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.basic_todo_app.ui.screens.settings.SettingsScreen
import com.example.basic_todo_app.ui.screens.taskscreen.TaskScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
//    getting controller with remember so that it can keep it's state across recompositions
    val navController = rememberNavController()

//    The container for all the screens in the app
    NavHost(
        navController = navController,
        startDestination = "tasks",
        enterTransition = { slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(
                durationMillis = 1000, // longer = more relaxed
                easing = FastOutSlowInEasing // or LinearOutSlowInEasing for smoother end
        )
        ) },
        popEnterTransition = {
            slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(
                durationMillis = 1000, // longer = more relaxed
                easing = FastOutSlowInEasing // or LinearOutSlowInEasing for smoother end
                )
            )
        },
    ) {
//        displaying TaskScreen when navigating to "tasks"
        composable("tasks") {
            TaskScreen(goToSettings = { navController.navigate("settings") })
        }
//        displaying SettingsScreen when navigating to "settings"
        composable("settings") {
            SettingsScreen(
                goBack = { navController.popBackStack() }
            )
        }
    }
}