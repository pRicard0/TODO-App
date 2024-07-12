package com.example.todoapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    //Home
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>
    @Query("SELECT * FROM task WHERE status = :status")
    fun getAllTasksByStatus(status: String): Flow<List<Task>>
    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: Int): Flow<Task>
    @Query("SELECT * FROM task WHERE title LIKE :query || '%'")
    fun getTaskBySearch(query: String): Flow<List<Task>>

    //Add
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    //Edit
    @Update
    suspend fun updateTask(task: Task)

    //Delete
    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)
}