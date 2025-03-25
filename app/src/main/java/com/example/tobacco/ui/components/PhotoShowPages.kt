package com.example.tobacco.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tobacco.R

// 展示翻页的图片
@Composable
fun PhotoShowPages(cigPhotoList: List<String?>) {
    val pagerState = rememberPagerState(pageCount = { cigPhotoList.size })
    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)
        ) { page ->
            AsyncImage(
                model = cigPhotoList[page],
                contentDescription = null,
                modifier = Modifier.height(240.dp),
                placeholder = painterResource(R.drawable.baseline_smoking_rooms_24)
            )
        }
        // 小圆点
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { index ->
                val color =
                    if (pagerState.currentPage == index)
                        MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.outline
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color)
                        .clip(CircleShape)
                        .size(6.dp)
                )
            }
        }
    }
}