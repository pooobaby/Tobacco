package com.example.tobacco.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.tobacco.data.local.dao.CigDao
import com.example.tobacco.data.local.entity.Cigarette
import javax.inject.Inject

class CigRepository @Inject constructor(private val cigDao: CigDao) {
    // 获取cigarettes所有记录
    fun getAllCigarette(): LiveData<List<Cigarette>> {
        val list = cigDao.getAllCigarette()
        return list
    }

    // 获取所有Brand的记录
    fun getAllBrand(): LiveData<List<String>> {
        val list = cigDao.getAllBrand()
        return list
    }

    // 查询所有符合brand条件的记录
    fun getCigaretteByBrand(brand: String) = cigDao.getCigaretteByBrand(brand)

    // 根据ID查询香烟信息
    fun getCigaretteById(id: Int) = cigDao.getCigaretteById(id)

    // 随机生成一条记录
    fun getRandomCigarette() = cigDao.getRandomCigarette()

    // 根据groupNum查询brand的数量
    fun getBrandCountByGroupNum(groupNum: Int) = cigDao.getBrandCountByGroupNum(groupNum)

    // 根据groupNum查询brand的名称列表并去重
    fun getBrandsByGroupNum(groupNum: Int) = cigDao.getBrandsByGroupNum(groupNum)

    // 更新Cigarette
    fun updateCigarette(cig: Cigarette) = cigDao.updateCigarette(cig)

    // 随机生成10个Cigarette
    fun getRandomCigarettes() = cigDao.getRandomCigarettes()

    // 获取所有已收藏的列表
    fun getFavoriteCigarettes() = cigDao.getFavoriteCigarettes()

    // 通过box_code查询并获取id
    fun getIdByBoxCode(boxCode: String) = cigDao.getIdByBoxCode(boxCode)
}