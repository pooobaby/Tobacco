package com.example.tobacco.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "factories")
data class Factory(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "group_num") val groupNum: Int? = null,         // 集团编号
    @ColumnInfo(name = "group_name") val groupName: String? = null,    // 集团名称
    @ColumnInfo(name = "factory_num") val factoryNum: Int? = null,         // 工厂编号
    @ColumnInfo(name = "factory_name") val factoryName: String? = null,    // 工厂名称
    @ColumnInfo(name = "factory_desc") val factoryDesc: String? = null,       // 工厂简介
)
