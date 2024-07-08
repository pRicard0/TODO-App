package com.example.todoapp.ui.screens.home

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.components.BottomButton
import com.example.todoapp.components.homeScreen.HomeTopBar
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
    onCreateClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
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
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TODOAppTheme {
        HomeScreen(onCreateClick = {})
    }
}