package com.example.basic_todo_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier= Modifier.fillMaxWidth()
            .background(color= Color(0xFFE3E3E3))
    ) {
        Text(
            text= "ToDo App",
            style= MaterialTheme.typography.headlineMedium,
            modifier= Modifier.padding(top = 32.dp, start=8.dp)
                .fillMaxWidth()
        )
    }
}



