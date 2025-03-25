package com.example.tobacco.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.local.entity.Group
import com.example.tobacco.data.repository.CigRepository
import com.example.tobacco.data.repository.GroupRepository
import com.github.promeg.pinyinhelper.Pinyin
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(private val repository: GroupRepository) : ViewModel() {
    // 将品牌按拼音首字母分类，生成Map，并排序
    val groupAllList: LiveData<Map<String, List<Group>>> = repository.getAllGroup().map { groupList ->
        groupList.groupBy { group ->
            group.groupName?.map { char ->
                if (Pinyin.isChinese(char)) Pinyin.toPinyin(char) else char.toString()
            }?.joinToString("")?.first()?.uppercase(Locale.ROOT) ?: "#"
        }.toSortedMap()
    }

    // 根据group的groupNum查询结果
    fun groupByGroupNum(groupNum:Int): LiveData<Group?> = repository.getGroupByGroupNum(groupNum)
}
