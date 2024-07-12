package com.example.todoapp.data.database

import kotlinx.coroutines.flow.Flow

class OfflineTaskRepository(private val taskDao: TaskDao): TaskRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()

    override fun getAllTasksByStatus(status: String): Flow<List<Task>> = taskDao.getAllTasksByStatus(status)

    override fun getTaskByIdStream(id: Int): Flow<Task> = taskDao.getTaskById(id)

    override fun getTaskBySearch(searchQuery: String): Flow<List<Task>> = taskDao.getTaskBySearch(searchQuery)

    override suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    override suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    override suspend fun deleteTask(id: Int) = taskDao.deleteTask(id)
}