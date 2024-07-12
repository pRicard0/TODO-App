package com.example.todoapp.components.homeScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.ui.screens.Theme
import com.example.todoapp.ui.screens.ThemeViewModel
import com.example.todoapp.ui.screens.home.HomeViewModel
import com.example.todoapp.ui.theme.MainBackgroundColor
import com.example.todoapp.ui.theme.MainBlueColor
import com.example.todoapp.ui.theme.TODOAppTheme
import com.example.todoapp.ui.theme.Typography

@Composable
fun HomeTopBar(
    themeViewModel: ThemeViewModel,
    homeViewModel: HomeViewModel
) {
    val currentTheme by themeViewModel.theme.observeAsState(Theme.AUTO)
    val checkedState = when (currentTheme) {
        Theme.DARK -> true
        Theme.LIGHT -> false
        else -> isSystemInDarkTheme()
    }
    if (!homeViewModel.showSearch.value) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 12.dp, bottom = 12.dp, start = 0.dp, end = 8.dp)
        ) {
            Switch(
                checked = checkedState,
                onCheckedChange = { isChecked ->
                    Log.d("HomeTopBar", "isChecked = $isChecked. Current theme = $currentTheme")
                    val newTheme = if (isChecked) Theme.DARK else Theme.LIGHT
                    themeViewModel.onThemeChanged(newTheme)
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            )
            Text(
                text = "Projeto",
                style = Typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(
                modifier = Modifier.clickable { homeViewModel.showSearchField() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .size(32.dp)
                )
            }
        }
    } else {
        OutlinedTextField(
            value = homeViewModel.searchQuery.value,
            onValueChange = {homeViewModel.onSearchValueChange(it)},
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .size(32.dp)
                        .clickable { homeViewModel.hideSearchField() }
                )
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun HomeTopBarPreview() {
    TODOAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {

        }
    }
}