package com.example.todoapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.TodoNavHost
import com.example.todoapp.ui.screens.ThemeViewModel

@Composable
fun TodoApp(
    navController: NavHostController = rememberNavController()
) {
    val themeViewModel: ThemeViewModel = viewModel()
    TodoNavHost(navController = navController, themeViewModel = themeViewModel)
}