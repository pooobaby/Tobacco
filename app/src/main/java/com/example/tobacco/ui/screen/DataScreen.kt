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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen() {

    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        // modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GeneralTopAppBar(scrollBehavior, "香烟资料库")
        },
        bottomBar = {
            GeneralBottomBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(20.dp)
        ) {
            DataContent()
        }
    }
}

// 资料库主页面内容
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataContent() {
    val cigViewModel: CigViewModel = hiltViewModel()
    val brandAllMap = cigViewModel.brandAllMap.observeAsState(initial = emptyMap())
    val brandSortByPinyin = cigViewModel.brandSortedByPinyin.observeAsState(initial = emptyMap())

    val navController = LocalNavController.current

    LazyColumn {
        brandSortByPinyin.value?.forEach { (initial, brands) ->
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

            items(brands) { brand ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 20.dp)
                        .clickable {
                            navController.navigate("brand/${brand}")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = brand,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = brandAllMap.value[brand].toString(),
                        modifier = Modifier.padding(end = 20.dp),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

            }
        }
    }
}
