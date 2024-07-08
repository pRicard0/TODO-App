package com.example.todoapp.data.database

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAllTasksStream(): Flow<List<Task>>

    suspend fun getAllTasksByStatus(status: String): Flow<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(id: Int)
}