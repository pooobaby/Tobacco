package com.example.tobacco.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.tobacco.data.local.entity.Cigarette

@Dao
interface CigDao {
    // 查询cigarettes所有记录
    @Query("SELECT * FROM cigarettes")
    fun getAllCigarette(): LiveData<List<Cigarette>>

    // 查询所有Brand的记录并去重
    @Query("SELECT DISTINCT brand FROM cigarettes")
    fun getAllBrand(): LiveData<List<String>>

    // 查询所有符合brand条件的记录
    @Query("SELECT * FROM cigarettes WHERE brand = :brand")
    fun getCigaretteByBrand(brand: String): LiveData<List<Cigarette>>

    // 根据ID查询香烟信息
    @Query("SELECT * FROM cigarettes WHERE id = :id")
    fun getCigaretteById(id: Int): LiveData<Cigarette>

    // 随机生成一条记录
    @Query("SELECT * FROM cigarettes ORDER BY RANDOM() LIMIT 1")
    fun getRandomCigarette(): LiveData<Cigarette>

    // 根据groupNum查询brand的数量
    @Query("SELECT COUNT(DISTINCT brand) FROM cigarettes WHERE group_num = :groupNum")
    fun getBrandCountByGroupNum(groupNum: Int): LiveData<Int>

    // 根据groupNum查询brand的名称列表并去重
    @Query("SELECT DISTINCT brand FROM cigarettes WHERE group_num = :groupNum")
    fun getBrandsByGroupNum(groupNum: Int): LiveData<List<String>>

    // 更新Cigarette
    @Update
    fun updateCigarette(cig: Cigarette): Int

    // 随机生成10个Cigarette
    @Query("SELECT * FROM cigarettes ORDER BY RANDOM() LIMIT 10")
    fun getRandomCigarettes(): LiveData<List<Cigarette>>

    // 获取所有已收藏的列表
    @Query("SELECT * FROM cigarettes WHERE favorite = 1")
    fun getFavoriteCigarettes(): LiveData<List<Cigarette>>

    // 通过box_code查询并获取id
    @Query("SELECT id FROM cigarettes WHERE box_code = :boxCode LIMIT 1")
    fun getIdByBoxCode(boxCode: String): LiveData<Int>

}