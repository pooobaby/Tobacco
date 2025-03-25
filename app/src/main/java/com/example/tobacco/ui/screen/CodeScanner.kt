package com.example.tobacco.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.navigation.LocalNavController
import com.example.tobacco.ui.components.GeneralTopAppBar
import com.example.tobacco.viewmodel.CigViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeScanner() {
    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            GeneralTopAppBar(scrollBehavior, "扫描商品")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "将条形码放入框内进行扫描",
                )
            }
            ScannerContent()
        }
    }
}

@Composable
fun ScannerContent() {
    val navController = LocalNavController.current
    val cigViewModel: CigViewModel = hiltViewModel()
    var showDialog by remember { mutableStateOf(false) } // 新增状态控制对话框显示
    var showCamera by remember { mutableStateOf(false) } // 新增状态控制对话框显示
    var showResult by remember { mutableStateOf(false) } // 新增状态控制对话框显示
    var resultID by remember { mutableIntStateOf(0) } // 新增状态控制对话框显示

    AndroidView(
        modifier = Modifier.fillMaxWidth().height(300.dp).padding(20.dp),
        factory = { context ->
            BarcodeView(context).apply {
                val formats = listOf(
                    BarcodeFormat.QR_CODE,
                    BarcodeFormat.CODE_39,
                    BarcodeFormat.CODE_128,
                    BarcodeFormat.EAN_13,
                    BarcodeFormat.EAN_8
                )
                decoderFactory = DefaultDecoderFactory(formats)
                resume()
                setOnClickListener {
                    // 处理点击事件
                }
                decodeSingle { result ->
                    if (result.text != null) {
                        val idLiveData = cigViewModel.getIdByBoxCode(result.toString())
                        idLiveData.observeForever { id ->
                            if (id != null) {
                                showResult = true
                                resultID = id
                            } else {
                                showDialog = true

                            }
                        }
                    }
                }
            }
        },
    )

    if (showDialog) { // 根据状态显示对话框
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("提示") },
            text = { Text("未找到对应商品") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        // 重新加载CodeScanner页面
                        navController.popBackStack()
                        navController.navigate("scanner")
                    }) {
                    Text("确定")
                }
            }
        )
    }

    if (showResult) {
        ScannerResult(resultID)
    }

    if (showCamera) { // 根据状态显示对话框
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text("请确认开启相机权限，否则无法扫描条码") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        // 重新加载CodeScanner页面
                        navController.popBackStack()
                        navController.navigate("scanner")
                    }) {
                    Text("确定")
                }
            }
        )
    }

    val context = LocalContext.current
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // 权限被授予，刷新页面
            navController.popBackStack()
            navController.navigate("scanner")
        } else {
            // 处理权限被拒绝的情况
            showCamera = true
        }
    }

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                // 已经有权限，不做什么
            }
            else -> {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}

@Composable
fun ScannerResult(id: Int){
    val cigViewModel: CigViewModel = hiltViewModel()

    val cig = cigViewModel.getCigaretteById(id).observeAsState(initial = Cigarette()).value
    val oneCigList: List<Cigarette> = listOf(cig)

    BrandListItems(oneCigList)
}