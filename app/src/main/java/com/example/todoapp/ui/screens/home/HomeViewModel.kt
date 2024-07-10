package com.example.todoapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.text2.input.TextFieldState.Saver.save
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.database.Task
import com.example.todoapp.data.database.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    data class HomeUiState(val taskList: List<Task> = listOf())
    val homeUiState: StateFlow<HomeUiState> = taskRepository.getAllTasksStream().map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var taskExtended by mutableStateOf<Int?>(null)

    fun showExtended(index: Int) {
        if (taskExtended == index) {
            taskExtended = null
        } else {
            taskExtended = index
        }
    }

    suspend fun deleteTask(id: Int) {
        taskRepository.deleteTask(id)
    }
}