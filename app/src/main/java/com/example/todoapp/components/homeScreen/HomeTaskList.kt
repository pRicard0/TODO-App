package com.example.todoapp.components.homeScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.screens.home.HomeViewModel
import com.example.todoapp.ui.theme.ParagraphColor
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun TaskList(
    viewModel: HomeViewModel,
    onChangeClick: (Int) -> Unit
) {
    val homeUiState = viewModel.homeUiState.collectAsState()
    val searchUiState = viewModel.searchUiState.collectAsState()
    Log.d("HomeTask", "Size: ${searchUiState.value.taskList.size}, Search state: ${searchUiState.value}")
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        if(!viewModel.showSearch.value) {
            if (homeUiState.value.taskList.isEmpty()) {
                item {
                    val option = viewModel.getOption()
                    Column(
                        modifier = Modifier.fillMaxWidth().fillParentMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        when(option) {
                            "TO DO" -> Text(text = "No pending tasks", color = ParagraphColor)
                            "PROGRESS" -> Text(text = "No tasks in progress", color = ParagraphColor)
                            "DONE" -> Text(text = "No completed tasks", color = ParagraphColor)
                        }
                    }
                }
            }
            items(homeUiState.value.taskList.size) { taskIndex ->
                if (viewModel.taskExtended == taskIndex) {
                    CardExtended(
                        title = homeUiState.value.taskList[taskIndex].title,
                        description = homeUiState.value.taskList[taskIndex].description,
                        status = homeUiState.value.taskList[taskIndex].status,
                        time = homeUiState.value.taskList[taskIndex].time,
                        onClick = { viewModel.showExtended(taskIndex) },
                        id = homeUiState.value.taskList[taskIndex].id,
                        onChangeClick = onChangeClick,
                        viewModel = viewModel
                    )
                } else {
                    CardMinimized(
                        title = homeUiState.value.taskList[taskIndex].title,
                        onClick = { viewModel.showExtended(taskIndex) }
                    )
                }
            }
        } else {
            Log.d("HomeTaskLoop", "Size: ${searchUiState.value.taskList.size}, Search state: ${searchUiState.value}")
            items(searchUiState.value.taskList.size) { taskIndex ->
                if (viewModel.taskExtended == taskIndex) {
                    CardExtended(
                        title = searchUiState.value.taskList[taskIndex].title,
                        description = searchUiState.value.taskList[taskIndex].description,
                        status = searchUiState.value.taskList[taskIndex].status,
                        time = searchUiState.value.taskList[taskIndex].time,
                        onClick = { viewModel.showExtended(taskIndex) },
                        id = searchUiState.value.taskList[taskIndex].id,
                        onChangeClick = onChangeClick,
                        viewModel = viewModel
                    )
                } else {
                    CardMinimized(
                        title = searchUiState.value.taskList[taskIndex].title,
                        onClick = { viewModel.showExtended(taskIndex) }
                    )
                }
            }
        }
    }
}

@Composable
fun CardMinimized(title: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun CardExtended(
    title: String,
    description: String,
    status: String,
    time: String,
    onClick: () -> Unit,
    id: Int,
    onChangeClick: (Int) -> Unit,
    viewModel: HomeViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
            .clickable { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1.2f)
                )
                Spacer(
                    modifier = Modifier
                        .height(128.dp)
                        .width(1.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
                ){
                    Column {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(96.dp)
                                .height(24.dp)
                                .clip(RoundedCornerShape(percent = 100))
                                .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Text(
                                text = status.uppercase(Locale.ROOT),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        Text(
                            text = "01/01/2024",
                            fontSize = 12.sp
                        )
                        Text(
                            text = time,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(96.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(percent = 100))
                        .background(MaterialTheme.colorScheme.secondary)
                        .clickable { onChangeClick(id) }
                ) {
                    Text(
                        text = "CHANGE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(96.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(percent = 100))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable {
                            coroutineScope.launch {
                                viewModel.deleteTask(id)
                            }
                        }
                ) {
                    Text(
                        text = "DELETE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun HomeTaskCardPreview() {
    TODOAppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            CardMinimized(title = "tenso", onClick = {})
            CardExtended(
                title = "Home Screen",
                description = "Eu tenho que fazer a home screen com tal coisa e tal coisa com tal coisa.",
                status = "Finished",
                time = "19:30",
                onClick = {},
                onChangeClick = {},
                id = 1

            )
        }
    }
}
*/
