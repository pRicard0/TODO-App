package com.example.todoapp.ui.screens.newTask

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.components.BottomButton
import com.example.todoapp.components.TaskTopBar
import com.example.todoapp.components.newScreen.NewButton
import com.example.todoapp.ui.AppViewModelProvider
import com.example.todoapp.ui.navigation.NavigationDestination
import com.example.todoapp.ui.theme.BlackParagraphColor
import com.example.todoapp.ui.theme.MainBackgroundColor
import com.example.todoapp.ui.theme.SpacerColor
import com.example.todoapp.ui.theme.TODOAppTheme
import kotlinx.coroutines.launch

object NewDestination: NavigationDestination {
    override val route = "new"
    override val title = "New Task"
}

@Composable
fun NewScreen(
    onCloseClick: () -> Unit,
    viewModel: NewViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
            .padding(16.dp),
        topBar = {
           TaskTopBar(onCloseClick = onCloseClick)
        },
        bottomBar = {
            BottomButton(
                text = "Criar",
                onClick = {
                    coroutineScope.launch {
                        viewModel.insertTask()
                        onCloseClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NewForm(
                taskDetails = viewModel.taskUiState.taskDetails,
                onValueChange = viewModel::updateUiState,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun NewForm(
    taskDetails: NewViewModel.TaskDetails,
    onValueChange: (NewViewModel.TaskDetails) -> Unit,
    viewModel: NewViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth().background(MainBackgroundColor)
    ) {
        OutlinedTextField(
            value = taskDetails.title,
            onValueChange = { onValueChange(taskDetails.copy(title = it)); Log.d("NewScreen", "Title input = $it")},
            label = { Text("Title", color = BlackParagraphColor) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(SpacerColor)
            .fillMaxWidth())
        OutlinedTextField(
            value = taskDetails.description,
            onValueChange = { onValueChange(taskDetails.copy(description = it))},
            label = { Text("Description", color = BlackParagraphColor) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(SpacerColor)
            .fillMaxWidth())
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Status",
                color = BlackParagraphColor
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.buttonList.forEachIndexed { index, button ->
                    NewButton(
                        text = button.text,
                        onClick = {
                            viewModel.changeButtonUi(index)
                            onValueChange(taskDetails.copy(status = button.text))
                            Log.d("NewScreen", "Status input = ${button.text}")
                        },
                        button = button
                    )
                }
            }
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(SpacerColor)
            .fillMaxWidth())
        OutlinedTextField(
            value = taskDetails.time,
            onValueChange = { onValueChange(taskDetails.copy(time = it))},
            label = { Text("Time", color = BlackParagraphColor) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(SpacerColor)
            .fillMaxWidth())
    }
}

@Preview
@Composable
fun NewScreenPreview() {
    TODOAppTheme {
        NewScreen(onCloseClick = {})
    }
}