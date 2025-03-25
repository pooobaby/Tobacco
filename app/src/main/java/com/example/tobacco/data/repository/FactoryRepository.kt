package com.example.tobacco.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.tobacco.data.local.dao.CigDao
import com.example.tobacco.data.local.dao.FactoryDao
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.local.entity.Factory
import javax.inject.Inject

class FactoryRepository @Inject constructor(private val factoryDao: FactoryDao){
    // 获取factories所有记录
    fun getAllFactory(): LiveData<List<Factory>>{
        val list = factoryDao.getAllFactory()
        return list
    }

    // 按照group_num查询符合记录的数量
    fun getFactoryCountByGroupNum(groupNum: Int) = factoryDao.getFactoryCountByGroupNum(groupNum)

    // 按照group_num查询符合记录的列表
    fun getFactoriesByGroupNum(groupNum: Int) = factoryDao.getFactoriesByGroupNum(groupNum)
}
