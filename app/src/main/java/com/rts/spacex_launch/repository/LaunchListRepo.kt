package com.rts.spacex_launch.repository

import android.util.Log
import arrow.core.Either
import com.rts.graphqltest.LaunchesQuery
import com.rts.spacex_launch.domain.Launch
import com.rts.spacex_launch.localdatabase.AppDatabase
import com.rts.spacex_launch.localdatabase.dao.LaunchesDao
import com.rts.spacex_launch.localdatabase.entity.asDomain
import com.rts.spacex_launch.network.apolloClient
import com.rts.spacex_launch.network.asEntity
import javax.inject.Inject

class LaunchListRepo @Inject constructor(
    private val appDatabase: AppDatabase,
    private val launchesDao: LaunchesDao
){
//    suspend fun getLaunchList(): Either<List<Launch>,Exception> {
//        return try {
//            val response = apolloClient.query(LaunchesQuery()).execute()
//            Log.d("Launch", "Success ${response.data}")
//            val data=response.data?.launches?.filterNotNull()
//            if (data==null){
//                Either.left(emptyList())
//            }else{
//                Either.left(data.map { it.asDomain() })
//            }
//        }catch (e: Exception) {
//            Either.right(e)
//        }
//    }

    suspend fun fetchLaunchListFromNetwork():Either<Boolean,Exception>{
       return try {
           val response = apolloClient.query(LaunchesQuery()).execute()
           Log.d("Launch", "Success ${response.data}")
           val data=response.data?.launches?.filterNotNull()
           if (data != null) {
               launchesDao.saveLaunches(data.map { it.asEntity() })
               Either.left(true)
           }else{
               Either.left(false)
           }
       }catch (e:Exception){
           Either.right(e)
       }
    }

    suspend fun getLaunchListFromDb():List<Launch>{
        return launchesDao.getLaunchList().map { it.asDomain() }
    }

    suspend fun getLaunchDetails(id:String):Launch{
        return launchesDao.getLaunchDetail(id).asDomain()
    }

}