package com.example.todoapp.ui.screens.newTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.components.BottomButton
import com.example.todoapp.components.TaskTopBar
import com.example.todoapp.components.newScreen.NewButton
import com.example.todoapp.ui.navigation.NavigationDestination
import com.example.todoapp.ui.theme.BlackParagraphColor
import com.example.todoapp.ui.theme.MainBackgroundColor
import com.example.todoapp.ui.theme.ParagraphColor
import com.example.todoapp.ui.theme.SpacerColor
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
        },
        bottomBar = {
            BottomButton(text = "Criar", onClick = {})
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NewForm()
        }
    }
}

@Composable
fun NewForm() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
            label = { Text("Descrição", color = BlackParagraphColor) },
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
                NewButton(text = "PENDENTE", onClick = {})
                NewButton(text = "EM PROGRESSO", onClick = {})
                NewButton(text = "TERMINADO", onClick = {})
            }
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(SpacerColor)
            .fillMaxWidth())
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Descrição", color = BlackParagraphColor) },
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