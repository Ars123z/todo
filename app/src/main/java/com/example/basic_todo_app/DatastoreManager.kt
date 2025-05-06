package com.example.basic_todo_app

import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.map

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DatastoreManager(private val context: Context) {

    // Save number
    suspend fun saveNumber(value: Int) {
        context.preferencesDataStore.edit { datastore ->
            datastore[NUMBER_KEY] = value
        }
    }

    // Read number
    fun getNumber() = context.preferencesDataStore.data
        .map { datastore ->
            datastore[NUMBER_KEY] ?: 1
        }

    companion object {
        private val NUMBER_KEY = intPreferencesKey("stored_number")
    }
}