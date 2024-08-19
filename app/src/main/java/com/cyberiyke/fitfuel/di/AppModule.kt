package com.cyberiyke.fitfuel.di

import android.content.Context
import androidx.room.Room
import com.cyberiyke.fitfuel.data.db.dao.RunDB
import com.cyberiyke.fitfuel.data.db.dao.RunDB.Companion.RUN_DB_NAME
import com.cyberiyke.fitfuel.data.db.dao.RunDoa
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object{

        @Provides
        @Singleton
        fun providingRunningDB(
            @ApplicationContext context: Context)
        :RunDB = Room.databaseBuilder(context, RunDB::class.java, RUN_DB_NAME)
            .build()

        @Singleton
        @Provides
        fun provideRunDao(db: RunDB) = db.getRunDao()

    }

}