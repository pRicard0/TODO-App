package com.example.todoapp.ui.screens.changeTask

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.components.BottomButton
import com.example.todoapp.components.TaskTopBar
import com.example.todoapp.components.newScreen.NewButton
import com.example.todoapp.ui.AppViewModelProvider
import com.example.todoapp.ui.navigation.NavigationDestination
import com.example.todoapp.ui.screens.ThemeViewModel
import com.example.todoapp.ui.screens.newTask.NewForm
import com.example.todoapp.ui.screens.newTask.NewViewModel
import com.example.todoapp.ui.theme.TODOAppTheme
import kotlinx.coroutines.launch

object ChangeDestination: NavigationDestination {
    override val route = "change"
    override val title = "Change Task"
    const val taskIdArg = "taskId"
    val routeWithArgs = "$route/{$taskIdArg}"
}

@Composable
fun ChangeScreen(
    onCloseClick: () -> Unit,
    viewModel: ChangeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    themeViewModel: ThemeViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    TODOAppTheme(themeViewModel = themeViewModel) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .systemBarsPadding(),
            topBar = {
                TaskTopBar(
                    onCloseClick = onCloseClick,
                    text = "Change Task",
                    onClearClick = { viewModel.clearUiState()}
                )
            },
            bottomBar = {
                BottomButton(
                    text = "Alterar",
                    onClick = {
                        coroutineScope.launch {
                            viewModel.updateTask()
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
                ChangeForm(
                    taskDetails = viewModel.taskUiState.taskDetails,
                    onValueChange = viewModel::updateUiState,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun ChangeForm(
    taskDetails: ChangeViewModel.TaskDetails,
    onValueChange: (ChangeViewModel.TaskDetails) -> Unit,
    viewModel: ChangeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        OutlinedTextField(
            value = taskDetails.title,
            onValueChange = { onValueChange(taskDetails.copy(title = it)); Log.d("NewScreen", "Title input = $it")},
            label = { Text("Title", color = MaterialTheme.colorScheme.onSurface) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth())
        OutlinedTextField(
            value = taskDetails.description,
            onValueChange = { onValueChange(taskDetails.copy(description = it))},
            label = { Text("Description", color = MaterialTheme.colorScheme.onSurface) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth())
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Status",
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.buttonList.forEachIndexed { index, button ->
                    if ( index == viewModel.activeButton) {
                        NewButton(
                            text = button.text,
                            onClick = {
                                onValueChange(taskDetails.copy(status = button.text))
                            },
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        NewButton(
                            text = button.text,
                            onClick = {
                                viewModel.setActiveButton(index)
                                onValueChange(taskDetails.copy(status = button.text))
                                Log.d("NewScreen", "Status input = ${button.text}")
                            },
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth())
        OutlinedTextField(
            value = taskDetails.time,
            onValueChange = { onValueChange(taskDetails.copy(time = it))},
            label = { Text("Time", color = MaterialTheme.colorScheme.onSurface) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth())
    }

}