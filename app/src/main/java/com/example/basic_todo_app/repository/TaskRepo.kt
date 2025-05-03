package com.example.basic_todo_app.repository
import com.example.basic_todo_app.data.Task
import com.example.basic_todo_app.data.TaskDao
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface TaskRepo {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllTasks(): Flow<List<Task>>


    /**
     * Insert item in the data source
     */
    suspend fun insertTask(task: Task)

    /**
     * Delete item from the data source
     */
    suspend fun deleteTask(task: Task)

    /**
     * Update item in the data source
     */
    suspend fun updateTask(task: Task)
}


class OfflineTaskRepo(private val taskDao: TaskDao) : TaskRepo {

    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllItems()
    override suspend fun insertTask(task: Task) = taskDao.insert(task)
    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    override suspend fun updateTask(task: Task) = taskDao.updateTask(task)

}