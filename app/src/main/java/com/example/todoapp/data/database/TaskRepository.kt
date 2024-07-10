package com.example.todoapp.data.database

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasksStream(): Flow<List<Task>>

    fun getAllTasksByStatus(status: String): Flow<List<Task>>

    fun getTaskByIdStream(id: Int): Flow<Task>

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(id: Int)
}