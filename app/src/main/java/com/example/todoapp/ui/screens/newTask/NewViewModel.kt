package com.example.todoapp.ui.screens.newTask

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.database.Task
import com.example.todoapp.data.database.TaskRepository


class NewViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    data class TaskUiState(
        val taskDetails: TaskDetails = TaskDetails(),
        val isEntryValid: Boolean = false
    )

    data class TaskDetails(
        var title: String = "",
        var description: String = "",
        val status: String = "",
        val time: String = ""
    )

    data class ButtonDetails(
        var text: String,
    )

    fun TaskDetails.toTask() = Task(
        title = title,
        description = description,
        status = status,
        time = time
    )
    fun Task.toTaskDetails(): TaskDetails = TaskDetails(
        title = title,
        description = description,
        status = status,
        time = time
    )

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    var activeButton by mutableStateOf<Int?>(null)

    var buttonList: Array<ButtonDetails> = arrayOf(
        ButtonDetails(text = "TO DO"),
        ButtonDetails(text = "IN PROGRESS"),
        ButtonDetails(text = "DONE")
    )

    fun updateUiState(taskDetails: TaskDetails) {
        taskUiState =
            TaskUiState(taskDetails = taskDetails, isEntryValid = validateInput(taskDetails))
    }
    fun clearUiState() {
        val emptyTask = createEmptyTask()
        taskUiState =
            TaskUiState(taskDetails = emptyTask, isEntryValid = false)
    }
    private fun createEmptyTask(): TaskDetails{
        return TaskDetails(
            title = "",
            description = "",
            status = "",
            time = ""
        )
    }

    fun validateInput(uiState: TaskDetails = taskUiState.taskDetails): Boolean {
        return with(uiState){
            title.isNotBlank() && description.isNotBlank() && status.isNotBlank() && time.isNotBlank()
        }
    }

    suspend fun insertTask() {
        if (validateInput()) {
            taskRepository.insertTask(taskUiState.taskDetails.toTask())
        }
    }

    fun setActiveButton(index: Int) {
        activeButton = index
    }
}

