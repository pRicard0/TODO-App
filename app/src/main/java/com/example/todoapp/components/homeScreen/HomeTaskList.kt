package com.example.todoapp.components.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.screens.home.HomeViewModel
import com.example.todoapp.ui.theme.BlackParagraphColor
import com.example.todoapp.ui.theme.MainBackgroundColor
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.ParagraphColor
import com.example.todoapp.ui.theme.SpacerColor
import com.example.todoapp.ui.theme.TODOAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun TaskList(
    homeUiState: HomeViewModel.HomeUiState,
    viewModel: HomeViewModel,
    onChangeClick: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        items(homeUiState.taskList.size) { taskIndex ->
            if (viewModel.taskExtended == taskIndex) {
                CardExtended(
                    title = homeUiState.taskList[taskIndex].title,
                    description = homeUiState.taskList[taskIndex].description,
                    status = homeUiState.taskList[taskIndex].status,
                    time = homeUiState.taskList[taskIndex].time,
                    onClick = { viewModel.showExtended(taskIndex) },
                    id = homeUiState.taskList[taskIndex].id,
                    onChangeClick = onChangeClick,
                    viewModel = viewModel
                )
            } else {
                CardMinimized(
                    title = homeUiState.taskList[taskIndex].title,
                    onClick = { viewModel.showExtended(taskIndex) }
                )
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
                        text = "ALTERAR",
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
