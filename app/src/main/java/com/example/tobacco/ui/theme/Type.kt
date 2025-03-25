package com.example.tobacco.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle( // 用于正文大号字体，适用于长段落文本
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle( // 用于正文标准字体，适用于一般性文本内容
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle( // 用于较小的正文字体，适用于注释或辅助说明
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.4.sp
    ),
    titleLarge = TextStyle( // 用于大型标题，如页面或部分的主标题
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 22.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle( // 中等大小的标题，适用于列表项或卡片标题
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 22.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.2.sp
    ),
    titleSmall = TextStyle( // 小型标题，适用于简短提示或状态信息
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 18.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle( // 用于最大的标题，通常在首页或重要位置使用
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 16.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle( // 较大的标题，次于headlineLarge
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 22.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.2.sp
    ),
    headlineSmall = TextStyle( // 中等大小的标题，适用于子标题或次要信息
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 18.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle( // 用于中等大小的标签或按钮文本
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 14.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle( // 用于小标签或辅助文字，如按钮上的文本
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 12.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle( // 用于中等大小的标签或按钮文本
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp, // 行高调整为比字号大2.sp
        letterSpacing = 0.5.sp
    ),
)