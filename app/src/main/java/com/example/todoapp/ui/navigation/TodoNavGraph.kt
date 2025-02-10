package com.example.todoapp.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.ui.screens.ThemeViewModel
import com.example.todoapp.ui.screens.changeTask.ChangeDestination
import com.example.todoapp.ui.screens.changeTask.ChangeScreen
import com.example.todoapp.ui.screens.home.HomeDestination
import com.example.todoapp.ui.screens.home.HomeScreen
import com.example.todoapp.ui.screens.newTask.NewDestination
import com.example.todoapp.ui.screens.newTask.NewScreen

@Composable
fun TodoNavHost(
    navController: NavHostController,
    themeViewModel: ThemeViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                onCreateClick = { navController.navigate("${NewDestination.route}/$it")},
                onChangeClick = { navController.navigate("${ChangeDestination.route}/$it") },
                themeViewModel = themeViewModel
            )
        }

        composable(
            route = NewDestination.routeWithArgs,
            arguments = listOf(navArgument(NewDestination.option) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val option = navBackStackEntry.arguments?.getString(NewDestination.option)
            if (option != null) {
                NewScreen(
                    onCloseClick = { navController.navigate(HomeDestination.route) },
                    themeViewModel = themeViewModel,
                    option = option
                )
            } else {
                Text(text = "Erro: Argumento 'option' não encontrado.")

            }
        }

        composable(
            route = ChangeDestination.routeWithArgs,
            arguments = listOf(navArgument(ChangeDestination.taskIdArg) {
                type = NavType.IntType
            })
        ) {
            ChangeScreen(
                onCloseClick = { navController.navigate(HomeDestination.route) },
                themeViewModel = themeViewModel
            )
        }
    }
}