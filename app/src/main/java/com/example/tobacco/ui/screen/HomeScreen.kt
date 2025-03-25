package com.example.tobacco.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tobacco.R
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.local.entity.Group
import com.example.tobacco.navigation.LocalNavController
import com.example.tobacco.ui.components.GeneralBottomBar
import com.example.tobacco.ui.components.HomeItemCard
import com.example.tobacco.ui.components.HomeTopAppBar
import com.example.tobacco.ui.components.TitleCard
import com.example.tobacco.viewmodel.CigViewModel
import com.example.tobacco.viewmodel.GroupViewModel
import java.util.Locale

@Composable
fun HomeScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,  // 控制抽屉的打开和关闭
        gesturesEnabled = true,    // 抽屉式导航栏是否响应拖动(左右)
        // 使用 drawerContent 槽提供 ModalDrawerSheet 并提供抽屉式导航栏的内容
        drawerContent = {
            ModalDrawerSheet {
                ModalDrawerSheetContent()
            }
        },
    ) {
        // 这里是主界面框架的内容
        HomeScaffoldExample(drawerState)
    }
}

// 侧边栏抽屉中的内容
@Composable
fun ModalDrawerSheetContent() {
    val navController = LocalNavController.current
    val cigViewModel: CigViewModel = hiltViewModel()

    val favoriteList = cigViewModel.favoriteCigarettes.observeAsState(initial = emptyList()).value

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(12.dp))
        Text(
            text = "我的收藏(${favoriteList.size})",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        HorizontalDivider()

        LazyColumn(
            Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            items(favoriteList) { cig ->
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clickable {
                            navController.navigate("product/${cig.id}")
                        }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = null,
                    )
                    Text(
                        text = "${cig.name}",
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${cig.tar}"
                    )
                }
            }
        }
    }
}

// 主界面框架的内容，包含topBar和bottomBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffoldExample(drawerState: DrawerState) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        // modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(scrollBehavior, drawerState)
        },
        bottomBar = {
            GeneralBottomBar()
        },
    ) { paddingValues ->
        HomeContent(paddingValues)
    }
}

// home页主页面的内容
@Composable
fun HomeContent(paddingValues: PaddingValues) {
    // hiltViewModel() 能够导出不同的 ViewModel 是因为 Hilt 根据函数的返回类型自动推断并注入对应的实例。
    val cigViewModel: CigViewModel = hiltViewModel()
    val groupViewModel: GroupViewModel = hiltViewModel()

    val cig = cigViewModel.randomCigarette.observeAsState(initial = Cigarette()).value
    val cigList = cigViewModel.randomCigarettes.observeAsState(initial = emptyList()).value

    val num = cig.groupNum?.toInt()?:0
    val group = groupViewModel.groupByGroupNum(num).observeAsState(initial = Group()).value

    val navController = LocalNavController.current

    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        // 推荐品类
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 12.dp, end = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleCard("推荐")
            Text(
                text = "${cig.name}",
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    navController.navigate("home")
                },
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Icon(Icons.Filled.Refresh, null)
            }
        }
        // 推荐品类展示
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 12.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = cig.photoMain,
                null,
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .clickable {
                        navController.navigate("product/${cig.id}")
                    },
                placeholder = painterResource(R.drawable.baseline_smoking_rooms_24),
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                HomeItemCard("类型", "${cig.type}")
                HomeItemCard("焦油量", "${cig.tar}")
                HomeItemCard("烟碱量", "${cig.nicotine}")
                HomeItemCard("CO量", "${cig.co}")
                HomeItemCard("是否细支", "${cig.isThin}")
                HomeItemCard("是否有爆珠", "${cig.hasBall}")
                HomeItemCard("单盒价格", String.format(Locale.CHINA, "%,.2f元", cig.boxPrice))
            }
        }
        // 推荐品牌的企业展示
        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TitleCard("出品")
                Text(
                    text = "${group?.groupName}",
                    modifier = Modifier.padding(start = 12.dp)
                )
                IconButton(
                    onClick = {
                        navController.navigate("group/${group?.groupNum}")
                    },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                        null,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
            Text(
                text = "${group?.groupDesc}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 12.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis    // 超出部分显示省略号
            )
        }

        // 分隔线
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 6.dp),
            color = MaterialTheme.colorScheme.outlineVariant
        )

        // 显示随机生成的10个香烟品类
        BrandListItems(cigList)

    }
}
