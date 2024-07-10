package com.example.todoapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (!darkTheme) {
        LightColorScheme
    } else {
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}