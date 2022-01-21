package com.rts.spacex_launch.domain

data class Launch(
    val id:String,
    val launchDateLocal:String,
    val missionName:String,
    val flickerImage:String
)
