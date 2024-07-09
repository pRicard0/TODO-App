package com.example.todoapp.components.newScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.SecondaryBlueColor

@Composable
fun NewButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SecondaryBlueColor,
            contentColor = MainBlueColor
        ),
        modifier = Modifier.height(28.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun NewButtonPreview() {
    NewButton(text = "PENDENTE", onClick = {})
}