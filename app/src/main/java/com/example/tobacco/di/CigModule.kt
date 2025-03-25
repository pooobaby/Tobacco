package com.example.tobacco.di

import android.content.Context
import com.example.tobacco.data.local.dao.CigDao
import com.example.tobacco.data.local.dao.FactoryDao
import com.example.tobacco.data.local.dao.GroupDao
import com.example.tobacco.data.local.database.TobaccoDatabase
import com.example.tobacco.data.repository.CigRepository
import com.example.tobacco.data.repository.FactoryRepository
import com.example.tobacco.data.repository.GroupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CigModule {

    // 提供 TobaccoDatabase 实例
    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext context: Context): TobaccoDatabase {
        return TobaccoDatabase.getCigDatabase(context)
    }

    // 提供 CigDao 实例
    @Provides
    @Singleton
    fun provideCigDao(database: TobaccoDatabase): CigDao {
        return database.cigDao()
    }

    // 提供 GroupDao 实例
    @Provides
    @Singleton
    fun provideGroupDao(database: TobaccoDatabase): GroupDao {
        return database.groupDao()
    }

    // 提供 FactoryDao 实例
    @Provides
    @Singleton
    fun provideFactoryDao(database: TobaccoDatabase): FactoryDao {
        return database.factoryDao()
    }

    // 提供 CigRepository 实例
    @Provides
    @Singleton
    fun provideCigRepository(cigDao: CigDao): CigRepository {
        return CigRepository(cigDao)
    }

    // 提供 GroupRepository 实例
    @Provides
    @Singleton
    fun provideGroupRepository(groupDao: GroupDao): GroupRepository {
        return GroupRepository(groupDao)
    }

    // 提供 FactoryRepository 实例
    @Provides
    @Singleton
    fun provideFactoryRepository(factoryDao: FactoryDao): FactoryRepository {
        return FactoryRepository(factoryDao)
    }
}