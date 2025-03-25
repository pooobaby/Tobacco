package com.example.tobacco.ui.theme

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
    primary = Orange400, // 主色调
    secondary = Black300, // 辅助色调
    tertiary = White100, // 强调色调
    background = Black500, // 背景色
    surface = Black300, // 表面色
    onPrimary = Black500, // 主色调上的文字
    onSecondary = Black500, // 辅助色调上的文字
    onTertiary = White100, // 强调色调上的文字
    onBackground = White100, // 背景上的文字
    onSurface = White100, // 表面上的文字
    error = Red500, // 错误提示色
    onError = White100, // 错误提示上的文字
    outline = Black200 // 轮廓线颜色
)

private val LightColorScheme = lightColorScheme(
    primary = Blue500, // 主色调
    onPrimary = White100, // 主色调上的文字

    secondary = Orange300, // 辅助色调
    onSecondary = Black500, // 辅助色调上的文字

    // 条目小标题
    secondaryContainer = Black100,
    onSecondaryContainer = Black500,

    tertiary = Red500, // 强调色调
    onTertiary = White100, // 强调色调上的文字

    // 小标题
    tertiaryContainer = Red500,
    onTertiaryContainer = White100,

    background = White100, // 背景色
    onBackground = Black500, // 背景上的文字

    surface = Orange400, // 表面色，TopAppBar上面的颜色
    onSurface = Black500, // 表面上的文字

    surfaceVariant = Red500, // 表面色变体
    onSurfaceVariant = Black500, // 表面色变体上的文字，底部导航栏字体

    error = Red400, // 错误提示色
    onError = White100, // 错误提示上的文字

    outline = Black500, // 轮廓线颜色
    outlineVariant = Black200
)

@Composable
fun TobaccoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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