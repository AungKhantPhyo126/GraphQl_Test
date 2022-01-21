package com.rts.graphqltest.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rts.graphqltest.localdatabase.dao.LaunchesDao
import com.rts.graphqltest.localdatabase.entity.LaunchEntity


@Database(
    entities = [
        LaunchEntity::class
    ],
    version = 1
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun launchesDao(): LaunchesDao
    companion object {
        fun create(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "AppDatabase"
            ).allowMainThreadQueries()
                .build()
        }
    }
}