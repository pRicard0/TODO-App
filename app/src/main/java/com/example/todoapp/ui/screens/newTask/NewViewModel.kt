package com.example.todoapp.ui.screens.newTask

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.database.Task
import com.example.todoapp.data.database.TaskRepository
import com.example.todoapp.data.database.TaskStatus
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.SecondaryBlueColor

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
        var background: Color = SecondaryBlueColor,
        var contentColor: Color = MainBlueColor
    )

    fun TaskDetails.toTask() = Task(
        title = title,
        description = description,
        status = status,
        time = time
    )

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    var buttonList: Array<ButtonDetails> = arrayOf(
        ButtonDetails(text = "TO DO"),
        ButtonDetails(text = "IN PROGRESS"),
        ButtonDetails(text = "DONE")
    )

    fun updateUiState(taskDetails: TaskDetails) {
        taskUiState =
            TaskUiState(taskDetails = taskDetails, isEntryValid = validateInput(taskDetails))
    }

    fun validateInput(uiState: TaskDetails = taskUiState.taskDetails): Boolean {
        return with(uiState){
            title.isNotBlank() && description.isNotBlank() && status. isNotBlank() && time.isNotBlank()
        }
    }

    suspend fun insertTask() {
        if (validateInput()) {
            taskRepository.insertTask(taskUiState.taskDetails.toTask())
        }
    }

    fun changeButtonUi(index: Int) {
        for (i in buttonList.indices) {
            if (i == index) {
                buttonList[i].background = MainBlueColor
                buttonList[i].contentColor = Color.White
            } else {
                buttonList[i].background = SecondaryBlueColor
                buttonList[i].contentColor = MainBlueColor
            }
        }
    }
}

