package com.example.tobacco.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tobacco.R
import com.example.tobacco.navigation.LocalNavController

@Composable
fun GeneralBottomBar() {
    val navController = LocalNavController.current

    NavigationBar(
        modifier = Modifier.height(100.dp),
        containerColor = Color(0x00000000)  // 底部导航栏底色透明
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_home_24),
                    null,
                    )
            },
            label = { Text("主页") },
            selected = false,
            onClick = {
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true } // 弹出到 "home" 并包含它
                    launchSingleTop = true // 确保 "home" 是栈顶
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(R.drawable.baseline_storage_24), null) },
            label = { Text("资料库") },
            selected = false,
            onClick = {
                navController.navigate("data") {
                    popUpTo("home")
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(R.drawable.baseline_fireplace_24), null) },
            label = { Text("企业") },
            selected = false,
            onClick = {
                navController.navigate("company") {
                    popUpTo("home")
                    launchSingleTop = true
                }
            }
        )
    }
}