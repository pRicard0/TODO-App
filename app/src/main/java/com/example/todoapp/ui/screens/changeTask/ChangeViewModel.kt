package com.example.todoapp.ui.screens.changeTask

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.database.Task
import com.example.todoapp.data.database.TaskRepository
import com.example.todoapp.ui.screens.newTask.NewViewModel.TaskDetails
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ChangeViewModel(private val taskRepository: TaskRepository, savedStateHandle: SavedStateHandle) : ViewModel() {
    private val taskId: Int = checkNotNull(savedStateHandle[ChangeDestination.taskIdArg])

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    var activeButton by mutableStateOf<Int?>(null)

    var buttonList: Array<ButtonDetails> = arrayOf(
        ButtonDetails(text = "TO DO"),
        ButtonDetails(text = "IN PROGRESS"),
        ButtonDetails(text = "DONE")
    )

    init {
        viewModelScope.launch {
            taskUiState = taskRepository.getTaskByIdStream(taskId)
                .filterNotNull()
                .first()
                .toTaskUiState(true)
        }
    }

    fun Task.toTaskUiState(isEntryValid: Boolean = false): TaskUiState = TaskUiState(
        taskDetails = this.toTaskDetails(),
        isEntryValid = isEntryValid
    )

    fun Task.toTaskDetails(): TaskDetails = TaskDetails(
        title = title,
        description = description,
        status = status,
        time = time
    )

    fun TaskDetails.toTask() = Task(
        id = taskId,
        title = title,
        description = description,
        status = status,
        time = time
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

    suspend fun updateTask() {
        if (validateInput()) {
            taskRepository.updateTask(taskUiState.taskDetails.toTask())
        } else {
        }
    }

    fun setActiveButton(index: Int) {
        activeButton = index
    }

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
}

