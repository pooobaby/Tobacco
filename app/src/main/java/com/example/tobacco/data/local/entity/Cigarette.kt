package com.example.tobacco.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cigarettes")
data class Cigarette(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,      // ID
    @ColumnInfo(name = "brand") val brand: String? = null,      // 品牌
    @ColumnInfo(name = "name") val name: String? = null,        // 香烟名称
    @ColumnInfo(name = "number") val number: String? = null,    // 香烟编号
    @ColumnInfo(name = "group_name") val groupName: String? = null,     // 公司名称
    @ColumnInfo(name = "group_num") val groupNum: String? = null,       // 公司编号
    @ColumnInfo(name = "type") val type: String? = null,        // 卷烟类型
    @ColumnInfo(name = "tar") val tar: String? = null,          // 焦油量
    @ColumnInfo(name = "nicotine") val nicotine: String? = null,    // 烟气烟碱量
    @ColumnInfo(name = "co") val co: String? = null,            // 一氧化碳含量
    @ColumnInfo(name = "length") val length: String? = null,    // 香烟长度
    @ColumnInfo(name = "packaging") val packaging: String? = null,  // 包装形式
    @ColumnInfo(name = "box_count") val boxCount: Int? = null,      // 单盒数量
    @ColumnInfo(name = "box_price") val boxPrice: Double? = null,   // 单盒价格
    @ColumnInfo(name = "strip_count") val stripCount: Int? = null,  // 单条数量
    @ColumnInfo(name = "strip_price") val stripPrice: Double? = null,       // 单条价格
    @ColumnInfo(name = "box_code") val boxCode: String? = null,     // 盒码
    @ColumnInfo(name = "barcode") val barcode: String? = null,      // 条码
    @ColumnInfo(name = "is_thin") val isThin: String? = null,       // 是否为细支烟
    @ColumnInfo(name = "has_ball") val hasBall: String? = null,     // 是否有爆珠
    @ColumnInfo(name = "filter_type") val filterType: String? = null,       // 滤嘴类型
    @ColumnInfo(name = "status") val status: String? = null,        // 产品状态
    @ColumnInfo(name = "photo_main") val photoMain: String? = null, // 首页展示用香烟图片链接
    @ColumnInfo(name = "photo_links") val photoLinks: String? = null,       // 所有香烟图片链接
    @ColumnInfo(name = "favorite") var favorite: Boolean? = null           // 收藏
)
