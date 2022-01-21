package com.rts.graphqltest.localdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rts.graphqltest.domain.Launch

@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey val id:String,
    val launchDateLocal:String,
    val missionName:String,
    val flickerImage:String
)

fun LaunchEntity.asDomain():Launch{
    return Launch(
        id=id,
        launchDateLocal= launchDateLocal,
        missionName = missionName,
        flickerImage = flickerImage
    )
}