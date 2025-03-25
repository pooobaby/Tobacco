package com.example.tobacco.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Query
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.local.entity.Factory
import com.example.tobacco.data.repository.CigRepository
import com.example.tobacco.data.repository.FactoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FactoryViewModel @Inject constructor(private val repository: FactoryRepository) : ViewModel() {
    // 获取所有记录
    val factoryAllList: LiveData<List<Factory>> = repository.getAllFactory()

    // 按照group_num查询符合记录的数量
    fun getFactoryCountByGroupNum(groupNum: Int) = repository.getFactoryCountByGroupNum(groupNum)

    // 按照group_num查询符合记录的列表
    fun getFactoriesByGroupNum(groupNum: Int) = repository.getFactoriesByGroupNum(groupNum)
}
