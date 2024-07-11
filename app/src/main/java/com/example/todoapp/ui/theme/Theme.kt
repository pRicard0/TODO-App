package com.example.todoapp.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.screens.Theme
import com.example.todoapp.ui.screens.ThemeViewModel

private val DarkColorScheme = darkColorScheme(
    primary = DarkMainBlueColor,
    secondary = DarkSecondaryBlueColor,
    tertiary = DarkMainBackgroundColor,
    onPrimary = DarkOnMainBlueColor,
    onSecondary = DarkMainBlueColor,
    onTertiary = DarkParagraphColor,
    background = DarkMainBackgroundColor,
    onBackground = DarkParagraphColor,
    surface = DarkSwitchBackgroundColor,
    onSurface = DarkBlackParagraphColor,
    surfaceVariant = DarkSpacerColor,
    primaryContainer = DarkDeleteButton
)

private val LightColorScheme = lightColorScheme(
    primary = MainBlueColor,
    secondary = SecondaryBlueColor,
    tertiary = MainBackgroundColor,
    onPrimary = onMainBlueColor,
    onSecondary = MainBlueColor,
    onTertiary = ParagraphColor,
    background = MainBackgroundColor,
    onBackground = ParagraphColor,
    surface = SwitchBackgroundColor,
    onSurface = BlackParagraphColor,
    surfaceVariant = SpacerColor,
    primaryContainer = DarkDeleteButton
)

@Composable
fun TODOAppTheme(
    themeViewModel: ThemeViewModel = viewModel(),
    content: @Composable () -> Unit
) {
    val theme by themeViewModel.theme.observeAsState(Theme.AUTO)
    val colors = when (theme) {
        Theme.DARK -> DarkColorScheme
        Theme.LIGHT -> LightColorScheme
        Theme.AUTO -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}