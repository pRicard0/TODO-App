package com.example.todoapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.TodoNavHost

@Composable
fun TodoApp(
    navController: NavHostController = rememberNavController()
) {
    TodoNavHost(navController = navController)
}