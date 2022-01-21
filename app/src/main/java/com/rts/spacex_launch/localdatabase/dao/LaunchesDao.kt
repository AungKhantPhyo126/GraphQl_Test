package com.rts.spacex_launch.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rts.spacex_launch.localdatabase.entity.LaunchEntity

@Dao
interface LaunchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLaunches(launchlist:List<LaunchEntity>)

    @Query("SELECT * from launches")
    suspend fun getLaunchList():List<LaunchEntity>


}