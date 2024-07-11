package com.example.todoapp.ui.screens

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class Theme {
    LIGHT, DARK, AUTO
}

class ThemeViewModel : ViewModel() {
    private val _theme = MutableLiveData(Theme.AUTO)
    val theme: LiveData<Theme> = _theme

    fun onThemeChanged(newTheme: Theme) {
        _theme.value = newTheme
    }
}

