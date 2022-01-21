package com.rts.graphqltest.localdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rts.graphqltest.localdatabase.entity.LaunchEntity

@Dao
interface LaunchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLaunches(launchlist:List<LaunchEntity>)

    @Query("SELECT * from launches")
    suspend fun getLaunchList():List<LaunchEntity>


}