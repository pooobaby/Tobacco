package com.example.tobacco.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tobacco.ui.screen.BrandListScreen
import com.example.tobacco.ui.screen.CodeScanner
import com.example.tobacco.ui.screen.DataScreen
import com.example.tobacco.ui.screen.GroupScreen
import com.example.tobacco.ui.screen.GroupShowScreen
import com.example.tobacco.ui.screen.HomeScreen
import com.example.tobacco.ui.screen.ProductShowScreen


// LocalNavController 本身并不存储 NavController ，而是通过 CompositionLocalProvider 将 navController 实例绑定到 Compose 树中。
// 这样，任何需要 NavController 的地方都可以通过 LocalNavController.current 获取它，而不需要手动传递。
val LocalNavController = staticCompositionLocalOf<NavController> { error("No NavController provided") }

@Composable
fun TobaccoNavigation() {
    // NavController 是Compose导航的核心，负责管理导航堆栈和屏幕切换
    val navController = rememberNavController()
    // compositionLocalOf是Compose提供的一个工具，用于创建可以在Compose树中共享的全局变量
    CompositionLocalProvider(LocalNavController provides navController) {
        // // 在这个作用域内，LocalNavController.current 将返回 navController
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeScreen() }
            composable("data") { DataScreen() }
            composable("company") { GroupScreen() }
            composable("scanner") { CodeScanner() }
            // 品牌展示页
            composable(
                // 定义一个动态路由 brand/{brand} ，其中 {brand} 是一个字符串参数。
                route = "brand/{brand}",
                arguments = listOf(
                    navArgument("brand") { type = NavType.StringType }
                )
            ) { backStackEntry ->   // 在导航到该路由时，从 backStackEntry 中提取 brand 参数。
                val brand = backStackEntry.arguments?.getString("brand") ?: "0"
                BrandListScreen(brand)
            }
            // 产品详情页
            composable(
                route = "product/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                ProductShowScreen(id)
            }
            // 集团展示页
            composable(
                route = "group/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                GroupShowScreen(id)
            }
        }
    }
}