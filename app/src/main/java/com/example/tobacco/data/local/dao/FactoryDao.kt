package com.example.tobacco.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.tobacco.data.local.entity.Factory

@Dao
interface FactoryDao {
    // 查询factories所有记录
    @Query("SELECT * FROM factories")
    fun getAllFactory(): LiveData<List<Factory>>

    // 按照group_num查询符合记录的数量
    @Query("SELECT COUNT(*) FROM factories WHERE group_num = :groupNum")
    fun getFactoryCountByGroupNum(groupNum: Int): LiveData<Int>

    // 按照group_num查询符合记录的列表
    @Query("SELECT * FROM factories WHERE group_num = :groupNum")
    fun getFactoriesByGroupNum(groupNum: Int): LiveData<List<Factory>>
}