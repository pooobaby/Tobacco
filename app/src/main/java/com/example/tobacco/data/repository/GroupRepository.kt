package com.example.tobacco.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.tobacco.data.local.dao.CigDao
import com.example.tobacco.data.local.dao.GroupDao
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.local.entity.Group
import javax.inject.Inject

class GroupRepository @Inject constructor(private val groupDao: GroupDao){
    // 获取groups所有记录
    fun getAllGroup(): LiveData<List<Group>>{
        val list = groupDao.getAllGroup()
        return list
    }

    // 根据group的groupNum查询结果
    fun getGroupByGroupNum(groupNum: Int) = groupDao.getGroupByGroupNum(groupNum)
}
