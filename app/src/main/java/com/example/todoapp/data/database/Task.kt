package com.example.todoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

enum class TaskStatus {
    TODO,
    PROGRESS,
    FINISHED
}

class TaskStatusConverter {
    @TypeConverter
    fun fromTaskStatus(status: TaskStatus): String {
        return when (status) {
            TaskStatus.TODO -> "TODO"
            TaskStatus.PROGRESS -> "PROGRESS"
            TaskStatus.FINISHED -> "FINISHED"
        }
    }
}

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val status: TaskStatus,
    val time: Long
)