package com.example.tobacco.data.local.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tobacco.constant.Constant
import com.example.tobacco.data.local.dao.CigDao
import com.example.tobacco.data.local.dao.FactoryDao
import com.example.tobacco.data.local.dao.GroupDao
import com.example.tobacco.data.local.entity.Cigarette
import com.example.tobacco.data.local.entity.Factory
import com.example.tobacco.data.local.entity.Group

@Database(entities = [Cigarette::class, Group::class, Factory::class], version = 1, exportSchema = false)
abstract class TobaccoDatabase : RoomDatabase() {
    abstract fun cigDao(): CigDao
    abstract fun groupDao(): GroupDao
    abstract fun factoryDao(): FactoryDao

    // 单例模式
    companion object {
        private var instance: TobaccoDatabase? = null

        fun getCigDatabase(context: Context): TobaccoDatabase {
            instance?.let { return it }
            return Room.databaseBuilder(context.applicationContext,
                TobaccoDatabase::class.java, "tobacco")
                // 用于从 assets 目录加载预打包数据库文件，作为 Room 数据库的初始数据。
                // 它只会在数据库第一次创建时运行，不会覆盖后续的修改。
                .createFromAsset("database/tobacco.db")
                // 使用 addCallback 方法添加一个 RoomDatabase.Callback ，并在 onCreate 方法中记录日志。
                // onCreate 只会在数据库第一次创建时调用，因此日志只会在第一次加载预打包数据库时输出。
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d(Constant.TAG, "预打包数据库已加载: tobacco.db")
                    }
                })
                .build().apply {
                    instance = this
                }
        }
    }
}