package com.example.todoapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.database.Task
import com.example.todoapp.data.database.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(val taskList: List<Task> = listOf())

class HomeViewModel(taskRepository: TaskRepository) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = taskRepository.getAllTasksStream().map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}