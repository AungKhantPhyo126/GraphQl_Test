package com.rts.spacex_launch.localdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rts.spacex_launch.domain.Launch

@Entity(tableName = "launches")
data class LaunchEntity(
    @PrimaryKey val id:String,
    val launchDateLocal:String,
    val missionName:String,
    val flickerImage:String,
    val details:String,
    val wikiLink:String
)

fun LaunchEntity.asDomain():Launch{
    return Launch(
        id=id,
        launchDateLocal= launchDateLocal,
        missionName = missionName,
        flickerImage = flickerImage,
        details = details,
        wikiLink = wikiLink
    )
}