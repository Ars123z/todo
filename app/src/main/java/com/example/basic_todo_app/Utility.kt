package com.example.basic_todo_app

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

//Function to get image from assets folder
fun getImageFromAssets(
    context: Context,
    fileName: String,
): ImageBitmap? {
//    Use try catch to catch any type of exception during retrieval
    return try {
        context.assets.open(fileName).use { inputStream ->
            BitmapFactory.decodeStream(inputStream).asImageBitmap()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}