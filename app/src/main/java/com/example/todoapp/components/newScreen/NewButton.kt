package com.example.todoapp.components.newScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.data.database.TaskRepository
import com.example.todoapp.ui.screens.newTask.NewViewModel
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.SecondaryBlueColor

@Composable
fun NewButton(text: String, onClick: () -> Unit, containerColor: Color, contentColor: Color) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = Modifier.height(28.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}
