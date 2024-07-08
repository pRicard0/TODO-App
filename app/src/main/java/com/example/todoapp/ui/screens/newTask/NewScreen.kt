package com.example.todoapp.ui.screens.newTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.components.TaskTopBar
import com.example.todoapp.ui.navigation.NavigationDestination
import com.example.todoapp.ui.theme.MainBackgroundColor
import com.example.todoapp.ui.theme.TODOAppTheme

object NewDestination: NavigationDestination {
    override val route = "new"
    override val title = "New Task"
}

@Composable
fun NewScreen(
    onCloseClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
            .padding(16.dp),
        topBar = {
           TaskTopBar(onCloseClick = onCloseClick)
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
fun NewScreenPreview() {
    TODOAppTheme {
        NewScreen(onCloseClick = {})
    }
}