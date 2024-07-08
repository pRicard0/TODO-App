package com.example.todoapp.components.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.TODOAppTheme
import com.example.todoapp.ui.theme.Typography

@Composable
fun HomeTopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Switch(
            checked = false,
            onCheckedChange = {}
        )
        Text(
            text = "Projeto",
            style = Typography.headlineSmall
        )
        Box(
            modifier = Modifier.clickable { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Search",
                tint = MainBlueColor
            )
        }
    }
}

@Preview
@Composable
fun HomeTopBarPreview() {
    TODOAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {

        }
        HomeTopBar()
    }
}