package com.example.tobacco.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class Group(
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    @ColumnInfo(name = "group_num") val groupNum: Int? = null,         // 集团编号
    @ColumnInfo(name = "group_name") val groupName: String? = null,    // 集团名称
    @ColumnInfo(name = "group_desc") val groupDesc: String? = null,    // 集团简介
)
