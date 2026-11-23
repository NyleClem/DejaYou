package com.example.dejayou.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = MoonLavender,
    secondary = MidnightViolet,
    tertiary = Lavender,
    background = MidnightNavy,
    onBackground = DreamyWhite,
    surface = MidnightIndigo,
    onSurface = DreamyWhite
)

private val LightColorScheme = lightColorScheme(
    primary = RoyalPurple,
    secondary = RoseCopper,
    tertiary = Lavender,
    background = ChampagnePeach,
    onBackground = DeepPlum
)

@Composable
fun DejaYouTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}