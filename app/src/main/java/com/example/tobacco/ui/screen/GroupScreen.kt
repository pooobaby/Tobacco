package com.example.tobacco.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tobacco.navigation.LocalNavController
import com.example.tobacco.ui.components.GeneralBottomBar
import com.example.tobacco.ui.components.GeneralTopAppBar
import com.example.tobacco.viewmodel.CigViewModel
import com.example.tobacco.viewmodel.FactoryViewModel
import com.example.tobacco.viewmodel.GroupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen() {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        // modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GeneralTopAppBar(scrollBehavior, "烟草企业库")
        },
        bottomBar = {
            GeneralBottomBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            GroupContent()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupContent() {
    val cigViewModel: CigViewModel = hiltViewModel()
    val groupViewModel: GroupViewModel = hiltViewModel()
    val factoryViewModel: FactoryViewModel = hiltViewModel()

    val groupAllList = groupViewModel.groupAllList.observeAsState(initial = emptyMap()).value
    val navController = LocalNavController.current

    LazyColumn {
        groupAllList.forEach { (initial, groups) ->
            stickyHeader {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.size(36.dp)
                ) {
                    Text(
                        text = initial,
                        modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center).padding(6.dp)
                    )
                }
            }

            items(groups) { group ->
                // 获取集团所属的工厂数量
                val factoriesCountByGroupNum = group.groupNum?.let { groupNum ->
                    factoryViewModel.getFactoryCountByGroupNum(groupNum)
                        .observeAsState(initial = 0).value
                }

                val brandCountByGroupNum = group.groupNum?.let { groupNum ->
                    cigViewModel.getBrandCountByGroupNum(groupNum).observeAsState(initial = 0).value
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                        .clickable {
                            navController.navigate("group/${group.groupNum}")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = group.groupName ?: "未知名称",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${factoriesCountByGroupNum}家工厂/${brandCountByGroupNum}个品牌",
                        // modifier = Modifier.padding(start = 20.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
