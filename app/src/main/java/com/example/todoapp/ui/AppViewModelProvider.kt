package com.example.todoapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoapp.TodoApplication
import com.example.todoapp.ui.screens.changeTask.ChangeViewModel
import com.example.todoapp.ui.screens.home.HomeViewModel
import com.example.todoapp.ui.screens.newTask.NewViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                todoApplication().container.taskRepository
            )
        }

        initializer {
            NewViewModel(
                todoApplication().container.taskRepository
            )
        }

        initializer {
            ChangeViewModel(
                todoApplication().container.taskRepository,
                this.createSavedStateHandle()
            )
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)