package com.example.tobacco.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tobacco.R
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.navigation.LocalNavController
import com.example.tobacco.ui.components.GeneralTopAppBar
import com.example.tobacco.ui.components.TitleCard
import com.example.tobacco.viewmodel.CigViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandListScreen(brand: String) {
    val navController = LocalNavController.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val cigViewModel: CigViewModel = hiltViewModel()

    val cigList = cigViewModel.getCigaretteByBrand(brand).observeAsState(initial = emptyList())
    val groupName = if (cigList.value.isNotEmpty()) cigList.value[0].groupName else "未知"
    val groupNum = if (cigList.value.isNotEmpty()) cigList.value[0].groupNum else "0"

    Scaffold(
        // modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GeneralTopAppBar(scrollBehavior, brand)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, top = 12.dp, end = 20.dp, bottom = 12.dp)
                    .clickable {
                        navController.navigate("group/${groupNum}")
                    },
            ) {
                TitleCard("出品")
                Text(
                    text = "$groupName",
                    modifier = Modifier.padding(start = 12.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
                    null,
                    modifier = Modifier.size(24.dp),
                )
            }
            BrandListItems(cigList.value)
        }
    }
}

@Composable
fun BrandListItems(cigList: List<Cigarette>) {
    val navController = LocalNavController.current
    LazyColumn(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        items(cigList) { cig ->
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .clickable {
                        navController.navigate("product/${cig.id}")
                    }
            ) {
                AsyncImage(
                    model = cig.photoMain,
                    null,
                    modifier = Modifier.height(100.dp),
                    placeholder = painterResource(R.drawable.baseline_smoking_rooms_24),
                    contentScale = ContentScale.Inside
                )
                Column(
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${cig.name}"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            if (cig.favorite == true)
                                painterResource(R.drawable.baseline_star_24)
                            else
                                painterResource(R.drawable.baseline_star_outline_24),
                            null,
                            tint = if (cig.favorite == true)
                                Color.Red
                            else
                                LocalContentColor.current
                        )
                    }
                    Text(
                        text = "类型：${cig.type}  盒价：${String.format(Locale.CHINA, "%,.2f元", cig.boxPrice)}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Text(
                        text = "焦油量：${cig.tar}  烟碱量：${cig.nicotine}  细支：${cig.isThin}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}

