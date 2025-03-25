package com.example.tobacco.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tobacco.R
import com.example.tobacco.constant.Constant
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.ui.components.GeneralTopAppBar
import com.example.tobacco.ui.components.HomeItemCard
import com.example.tobacco.ui.components.PhotoShowPages
import com.example.tobacco.util.SnackBarUtil
import com.example.tobacco.viewmodel.CigViewModel
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductShowScreen(id: Int) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val cigViewModel: CigViewModel = hiltViewModel()

    val cig = cigViewModel.getCigaretteById(id).observeAsState(initial = Cigarette()).value
    var favorite by remember { mutableStateOf(false) }

    // 当cig更新时，同步favorite状态
    LaunchedEffect(cig) {
        favorite = cig.favorite ?: false
    }

    val cigPhotoList = cig.photoLinks?.split(",")

    Scaffold(
        // modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GeneralTopAppBar(scrollBehavior, "${cig.name}")
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                if (cigPhotoList != null) {
                    PhotoShowPages(cigPhotoList)
                }
                IconButton(
                    onClick = {
                        favorite = !favorite
                        cig.id?.let {
                            cig.favorite = favorite
                            cigViewModel.updateCigarette(cig)
                        }
                        val snackText =
                            if (favorite) "收藏成功：${cig.name}" else "取消收藏：${cig.name}"
                        scope.launch {
                            SnackBarUtil.showMySnackbar(snackbarHostState, snackText)
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = if (favorite)
                            painterResource(R.drawable.baseline_star_24)
                        else
                            painterResource(R.drawable.baseline_star_outline_24),
                        contentDescription = null,
                        tint = if (favorite)
                            Color.Red
                        else
                            LocalContentColor.current
                    )
                }
            }
            // 展示产品详情
            ProductShowContent(cig)
        }

    }
}


@Composable
fun ProductShowContent(cig: Cigarette) {
    // Column 中的所有 Text 都会继承 LocalTextStyle 提供的样式
    CompositionLocalProvider(
        LocalTextStyle provides MaterialTheme.typography.bodyLarge
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Row(Modifier.weight(1f)) {
                    HomeItemCard("类型", "${cig.type}")
                }
                Row(Modifier.weight(1f)) {
                    HomeItemCard("焦油含量", "${cig.tar}")
                }
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Row(Modifier.weight(1f)) {
                    HomeItemCard("烟气烟碱量", "${cig.nicotine}")
                }
                Row(Modifier.weight(1f)) {
                    HomeItemCard("一氧化碳含量", "${cig.co}")
                }
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Row(Modifier.weight(1f)) {
                    HomeItemCard("长度", "${cig.length}")
                }
                Row(Modifier.weight(1f)) {
                    HomeItemCard("包装形式", "${cig.packaging}")
                }
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Row(Modifier.weight(1f)) {
                    HomeItemCard("单盒数量", cig.boxCount.toString())
                }
                Row(Modifier.weight(1f)) {
                    HomeItemCard("单盒价格", String.format(Locale.CHINA, "%,.2f元", cig.boxPrice))
                }
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Row(Modifier.weight(1f)) {
                    HomeItemCard("单条数量", cig.stripCount.toString())
                }
                Row(Modifier.weight(1f)) {
                    HomeItemCard("单条价格", String.format(Locale.CHINA, "%,.2f元", cig.stripPrice))
                }
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                HomeItemCard("盒码", "${cig.boxCode}")
                Spacer(Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                HomeItemCard("条码", "${cig.barcode}")
                Spacer(Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Row(Modifier.weight(1f)) {
                    HomeItemCard("细支", "${cig.isThin}")
                }
                Row(Modifier.weight(1f)) {
                    HomeItemCard("爆珠", "${cig.hasBall}")
                }
            }
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Row(Modifier.weight(1f)) {
                    HomeItemCard("滤嘴类型", "${cig.filterType}")
                }
                Row(Modifier.weight(1f)) {
                    HomeItemCard("产品状态", "${cig.status}")
                }
            }
        }
    }
}
