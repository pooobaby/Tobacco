package com.example.tobacco.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.repository.CigRepository
import com.github.promeg.pinyinhelper.Pinyin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CigViewModel @Inject constructor(private val repository: CigRepository) : ViewModel() {
    // 获取所有记录
    private val cigAllList: LiveData<List<Cigarette>> = repository.getAllCigarette()

    // 获取所有Brand的记录
    private val brandAllList: LiveData<List<String>> = repository.getAllBrand()

    // 查询所有符合brand条件的记录
    fun getCigaretteByBrand(brand: String): LiveData<List<Cigarette>>{
            val list = repository.getCigaretteByBrand(brand)
            return list
    }

    // 随机生成一条记录
    val randomCigarette = repository.getRandomCigarette()

    // 随机生成10个Cigarette
    val randomCigarettes = repository.getRandomCigarettes()

    // 获取所有已收藏的列表
    val favoriteCigarettes = repository.getFavoriteCigarettes()

    // 按品牌分组并统计每个品牌的数量
    val brandAllMap: LiveData<Map<String, Int>> =
        cigAllList.map { cigList ->
            cigList.mapNotNull { it.brand } // 过滤掉 null 值
                .groupingBy { it } // 按品牌分组
                .eachCount() // 统计每个品牌的数量
        }

    // 将品牌按拼音首字母分类，生成Map
    val brandSortedByPinyin: LiveData<Map<String, List<String>>?> = brandAllList.map { brandList ->
        brandList
            .sortedBy { brand ->
                brand.map { char ->
                    if (Pinyin.isChinese(char)) Pinyin.toPinyin(char) else char.toString()
                }.joinToString("")
            }
            .groupBy { brand ->
                brand.map { char ->
                    if (Pinyin.isChinese(char)) Pinyin.toPinyin(char) else char.toString()
                }.joinToString("").first().uppercase(Locale.ROOT)
            }
    }

    // 根据ID查询香烟信息
    fun getCigaretteById(id: Int) = repository.getCigaretteById(id)

    // 根据groupNum查询brand的数量
    fun getBrandCountByGroupNum(groupNum: Int) = repository.getBrandCountByGroupNum(groupNum)

    // 根据groupNum查询brand的名称列表并去重
    fun getBrandsByGroupNum(groupNum: Int) = repository.getBrandsByGroupNum(groupNum)

    // 更新Cigarette
    fun updateCigarette(cig: Cigarette) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.updateCigarette(cig)
        }
    }

    // 通过box_code查询并获取id
    fun getIdByBoxCode(boxCode: String) = repository.getIdByBoxCode(boxCode)
}
