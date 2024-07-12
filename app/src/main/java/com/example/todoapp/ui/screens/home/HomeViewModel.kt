package com.example.todoapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.text2.input.TextFieldState.Saver.save
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.data.database.Task
import com.example.todoapp.data.database.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class HomeViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val _option = mutableStateOf("TO DO")
    val option: MutableState<String> = _option

    private val _showSearch = mutableStateOf(false)
    val showSearch: MutableState<Boolean> = _showSearch

    private val _searchQuery = mutableStateOf("")
    val searchQuery: MutableState<String> = _searchQuery

    data class HomeUiState(val taskList: List<Task> = listOf())
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    private val _searchUiState = MutableStateFlow(HomeUiState())
    val searchUiState: StateFlow<HomeUiState> = _searchUiState


    init {
        fetchTasksByStatus(option.value)
    }

    var taskExtended by mutableStateOf<Int?>(null)


    fun showSearchField() {
        _showSearch.value = true
    }

    fun hideSearchField() {
        _showSearch.value = false
    }

    fun onSearchValueChange(text: String) {
        _searchQuery.value = text
        fetchTasksBySearch(text)
    }

    fun fetchTasksBySearch(text: String) {
        viewModelScope.launch {
            taskRepository.getTaskBySearch(text)
                .map { HomeUiState(it) }
                .collect { _searchUiState.value = it }
        }
        Log.d("HomeViewModel", "Query: $text, Size: ${searchUiState.value.taskList.size}, Search state: ${searchUiState.value}")
    }

    fun showExtended(index: Int) {
        if (taskExtended == index) {
            taskExtended = null
        } else {
            taskExtended = index
        }
    }

    fun setOption(option: String) {
        _option.value = option
        fetchTasksByStatus(option)
    }

    private fun fetchTasksByStatus(status: String) {
        viewModelScope.launch {
            if(status == "PROGRESS") {
                taskRepository.getAllTasksByStatus("IN PROGRESS")
                    .map { HomeUiState(it) }
                    .collect { _homeUiState.value = it }
            } else {
                taskRepository.getAllTasksByStatus(status)
                    .map { HomeUiState(it) }
                    .collect { _homeUiState.value = it }
            }
        }
    }

    suspend fun deleteTask(id: Int) {
        taskRepository.deleteTask(id)
    }
}