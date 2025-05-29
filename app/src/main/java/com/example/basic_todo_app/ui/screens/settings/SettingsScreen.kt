package com.example.basic_todo_app.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basic_todo_app.DatastoreManager
import com.example.basic_todo_app.getImageFromAssets
import kotlinx.coroutines.launch

val themes = listOf(
    "theme_1.webp",
    "theme_2.webp",
    "theme_3.webp",
    "theme_4.webp",
    "theme_5.webp",
    "theme_6.webp",
    "theme_7.webp",
    "theme_8.webp",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    goBack: () -> Unit,
) {
//    Context object to initialize the datastoreManager
    val context = LocalContext.current
//    datastoreManager instance initialization
    val datastoreManager = DatastoreManager(context)
//    coroutineScope to launch to save function of the manager
    val coroutineScope = rememberCoroutineScope()
//    flow to get the current saved number
    val savedNumber = datastoreManager.getNumber().collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { goBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors= TopAppBarDefaults.topAppBarColors(
                    containerColor= MaterialTheme.colorScheme.surface,
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "Choose Theme",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            items(themes) { themeName ->
                val index = themes.indexOf(themeName) + 1
                val isSelected = savedNumber.value == index
                val imageBitmap = getImageFromAssets(context, themeName)
//                let block to handle nullable bitmap
                imageBitmap?.let { bitmap ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                coroutineScope.launch {
                                    datastoreManager.saveNumber(index)
                                }
                            }
                    ) {
                        // Background image
                        Image(
                            bitmap = bitmap,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )

                        // Scrim overlay if selected
                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(Color(0x774CAF50)) // light translucent green
                            )
                        }
                    }
                }
            }
        }
    }
}


