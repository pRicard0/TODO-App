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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.screens.home.HomeViewModel
import com.example.todoapp.ui.theme.BlackParagraphColor
import com.example.todoapp.ui.theme.MainBackgroundColor
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.ParagraphColor
import com.example.todoapp.ui.theme.SpacerColor
import com.example.todoapp.ui.theme.TODOAppTheme
import java.util.Locale


@Composable
fun TaskList(
    homeUiState: HomeViewModel.HomeUiState,
    viewModel: HomeViewModel,
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
                    time = homeUiState.taskList[taskIndex].time
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
            .border(width = 1.dp, color = SpacerColor, shape = RoundedCornerShape(12.dp))
            .background(MainBackgroundColor)
            .padding(20.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = title,
            color = BlackParagraphColor
        )
    }
}

@Composable
fun CardExtended(
    title: String,
    description: String,
    status: String,
    time: String,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = SpacerColor, shape = RoundedCornerShape(12.dp))
            .background(MainBackgroundColor)
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = title,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = description,
                    color = ParagraphColor,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1.2f)
                )
                Spacer(
                    modifier = Modifier
                        .height(128.dp)
                        .width(1.dp)
                        .background(SpacerColor)
                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
                ){
                    Column {
                        Text(
                            text = "01/01/2024",
                            fontSize = 12.sp
                        )
                        Text(
                            text = time,
                            fontSize = 12.sp
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(96.dp)
                            .height(28.dp)
                            .clip(RoundedCornerShape(percent = 100))
                            .background(MainBlueColor)
                    ) {
                        Text(
                            text = status.uppercase(Locale.ROOT),
                            fontSize = 12.sp,
                            color = MainBackgroundColor
                        )
                    }
                }
            }
        }
    }
}

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
                time = "19:30"
            )
        }
    }
}