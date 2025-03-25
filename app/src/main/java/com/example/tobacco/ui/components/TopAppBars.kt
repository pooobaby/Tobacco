package com.example.tobacco.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import com.example.tobacco.R
import com.example.tobacco.navigation.LocalNavController
import com.example.tobacco.ui.screen.CodeScanner
import kotlinx.coroutines.launch

// home页的TopAppBar，传入了drawerState和scrollBehavior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    drawerState: DrawerState
) {
    val scopeDrawer = rememberCoroutineScope()
    val navController = LocalNavController.current
    // val cigViewModel: CigViewModel = hiltViewModel()
    // var showDialog by remember { mutableStateOf(false) } // 新增状态控制对话框显示
    val showScanner by remember { mutableStateOf(false) }

    // 这些是最开始测试扫描功能时的代码
    // val barcodeLauncher = rememberLauncherForActivityResult(
    //     contract = ScanContract(),
    //     onResult = { result ->
    //         if (result.contents != null) {
    //             // 处理扫描结果
    //             val idLiveData = cigViewModel.getIdByBoxCode(result.contents)
    //             idLiveData.observeForever { id ->
    //                 if (id != null) {
    //                     navController.navigate("product/$id")
    //                 } else {
    //                     // 显示提示信息，告知用户未找到对应商品
    //                     showDialog = true
    //                 }
    //             }
    //         }
    //     }
    // )

    // if (showDialog) { // 根据状态显示对话框
    //     AlertDialog(
    //         onDismissRequest = { showDialog = false },
    //         title = { Text("提示") },
    //         text = { Text("未找到对应商品") },
    //         confirmButton = {
    //             TextButton(onClick = { showDialog = false }) {
    //                 Text("确定")
    //             }
    //         }
    //     )
    // }

    if (showScanner) {CodeScanner()}

    TopAppBar(
        title = {
            Text(text = "典烟")
        },
        navigationIcon = {
            IconButton(onClick = {
                scopeDrawer.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }) {
                Icon(painter = painterResource(R.drawable.baseline_smoking_rooms_24), null)
            }
        },
        actions = {
            Row {
                IconButton(onClick = {
                    // todo SearchScreen()
                }) {
                    Icon(painter = painterResource(R.drawable.baseline_search_24), null)
                }
                IconButton(onClick = {
                    // showScanner = true
                    navController.navigate( "scanner") {
                        // 新建一个导航栈，scanner 页面将会在一个新的导航栈中打开，并且不会影响之前的导航历史。
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                    // val options = ScanOptions().apply {
                    //     setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
                    //     setPrompt("将条形码放入框内进行扫描")
                    //     setCameraId(0)
                    //     setBeepEnabled(false)
                    //     setBarcodeImageEnabled(true)
                    //     setOrientationLocked(true)
                    // }
                    // barcodeLauncher.launch(options)
                }) {
                    Icon(painter = painterResource(R.drawable.baseline_qr_code_24), null)
                }
            }
        },
        scrollBehavior = scrollBehavior     // 设置TopAppBar的滚动行为
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    title: String
) {
    val navController = LocalNavController.current
    val showScanner by remember { mutableStateOf(false) }

    if (showScanner) {CodeScanner()}
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(painter = painterResource(R.drawable.baseline_arrow_back_ios_24), null)
            }
        },
        actions = {
            Row {
                IconButton(onClick = {
                    // todo SearchScreen()
                }) {
                    Icon(painter = painterResource(R.drawable.baseline_search_24), null)
                }
                IconButton(onClick = {
                    navController.navigate( "scanner") {
                        // 新建一个导航栈，scanner 页面将会在一个新的导航栈中打开，并且不会影响之前的导航历史。
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                    }
                }) {
                    Icon(painter = painterResource(R.drawable.baseline_qr_code_24), null)
                }
            }
        },
        scrollBehavior = scrollBehavior     // 设置TopAppBar的滚动行为
    )
}
