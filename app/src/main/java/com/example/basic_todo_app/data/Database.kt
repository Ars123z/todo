package com.example.basic_todo_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


// Database class with a singleton Instance object.
@Database(entities = [Task::class], version = 1, exportSchema = false)
//Type converter to convert date and time to string and vice versa
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {

//    Dao objects to interact with the database only one dao object is needed for this project
    abstract fun taskDao(): TaskDao

    //    Singleton Instance object to hold the database instance
    companion object {
//        object to the hold the database instance or null if none exist
        @Volatile
        private var Instance: TaskDatabase? = null

//        function to get the database instance or create a new one if none exist
        fun getDatabase(context: Context): TaskDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TaskDatabase::class.java, "task_database")
                    .build()
                    // assign the Instance variable to the newly created database instance.
                    .also { Instance = it }
            }
        }
    }
}