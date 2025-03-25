package com.example.tobacco.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.local.entity.Group

@Dao
interface GroupDao {
    // 查询groups所有记录
    @Query("SELECT * FROM groups")
    fun getAllGroup(): LiveData<List<Group>>

    // 根据group的groupNum查询结果
    @Query("SELECT * FROM groups WHERE group_num = :groupNum LIMIT 1")
    fun getGroupByGroupNum(groupNum: Int): LiveData<Group?>
}