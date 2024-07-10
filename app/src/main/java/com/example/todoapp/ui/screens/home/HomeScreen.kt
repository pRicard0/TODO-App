package com.example.todoapp.ui.screens.home

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.components.BottomButton
import com.example.todoapp.components.homeScreen.HomeTopBar
import com.example.todoapp.components.homeScreen.TaskList
import com.example.todoapp.data.database.TaskRepository
import com.example.todoapp.ui.AppViewModelProvider
import com.example.todoapp.ui.navigation.NavigationDestination
import com.example.todoapp.ui.theme.MainBackgroundColor
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.TODOAppTheme

object HomeDestination: NavigationDestination {
    override val route = "home"
    override val title = "Project"
}

@Composable
fun HomeScreen(
    onCreateClick: () -> Unit,
    onChangeClick: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    TODOAppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            topBar = {
                HomeTopBar()
            },
            bottomBar = {
                BottomButton(
                    text = "Add Task",
                    onClick = { onCreateClick() },
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                TaskList(
                    homeUiState = homeUiState,
                    viewModel = viewModel,
                    onChangeClick = onChangeClick
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TODOAppTheme {
        HomeScreen(onCreateClick = {}, onChangeClick = {})
    }
}