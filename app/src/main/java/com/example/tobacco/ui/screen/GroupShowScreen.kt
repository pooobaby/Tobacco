package com.example.tobacco.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tobacco.data.local.entity.Factory
import com.example.tobacco.data.local.entity.Group
import com.example.tobacco.navigation.LocalNavController
import com.example.tobacco.ui.components.GeneralTopAppBar
import com.example.tobacco.ui.components.TitleCard
import com.example.tobacco.viewmodel.CigViewModel
import com.example.tobacco.viewmodel.FactoryViewModel
import com.example.tobacco.viewmodel.GroupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupShowScreen(groupNum: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val groupViewModel: GroupViewModel = hiltViewModel()
    val group = groupViewModel.groupByGroupNum(groupNum).observeAsState(initial = Group()).value

    Scaffold(
        // modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            group?.groupName?.let {
                GeneralTopAppBar(
                    scrollBehavior,
                    it.replace("有限责任公司", "")
                )
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            GroupShowContent(group ?: Group())
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GroupShowContent(group: Group) {
    val navController = LocalNavController.current
    val factoryViewModel: FactoryViewModel = hiltViewModel()
    val cigViewModel: CigViewModel = hiltViewModel()
    val groupNum = group.groupNum
    val factoryList: List<Factory>? = groupNum?.let {
        factoryViewModel.getFactoriesByGroupNum(it)
            .observeAsState(initial = emptyList()).value
    }

    val brandList: List<String>? = groupNum?.let {
        cigViewModel.getBrandsByGroupNum(it).observeAsState(initial = emptyList()).value
    }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var selectedFactory by remember { mutableStateOf<Factory?>(null) }

    Column {
        TitleCard("企业简介")
        Text(
            text = "${group.groupDesc}",
            modifier = Modifier.padding(top = 12.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 12.dp, top = 12.dp)
        )
        TitleCard("下属工厂")
        FlowRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            factoryList?.forEach { factory ->
                Card(
                    modifier = Modifier
                        // .widthIn(min = 100.dp, max = 200.dp)
                        .padding(4.dp)
                        .clickable {
                            showBottomSheet = true
                            selectedFactory = factory
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text(
                        text = factory.factoryName ?: "未知工厂",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 12.dp, top = 12.dp)
        )

        TitleCard("出产品牌")
        FlowRow(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            brandList?.forEach { brand ->
                Card(
                    modifier = Modifier
                        // .widthIn(min = 100.dp, max = 200.dp)
                        .padding(4.dp)
                        .clickable {
                            navController.navigate("brand/${brand}")
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text(
                        text = brand,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onBackground,
            shape = BottomSheetDefaults.HiddenShape,
            modifier = Modifier.systemBarsPadding()
        ) {
            selectedFactory?.let { ShowFactoryInfo(it) }
        }
    }
}

// 底部信息栏，展示工厂信息
@Composable
fun ShowFactoryInfo(factory: Factory) {
    Column(
        Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        Row {
            Text(
                text = "${factory.factoryName}"
            )
        }
        Text(
            text = "${factory.factoryDesc}",
            modifier = Modifier.padding(top = 12.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
