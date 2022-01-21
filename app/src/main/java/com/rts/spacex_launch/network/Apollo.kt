package com.rts.spacex_launch.network
import com.apollographql.apollo3.ApolloClient
import com.rts.graphqltest.LaunchesQuery
import com.rts.spacex_launch.domain.Launch
import com.rts.spacex_launch.localdatabase.entity.LaunchEntity

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://api.spacex.land/graphql/")
    .build()

fun LaunchesQuery.Launch.asDomain():Launch{
    return Launch(
        id=id.orEmpty(),
        launchDateLocal= launch_date_local.toString(),
        missionName = mission_name.orEmpty(),
        flickerImage = if (links?.flickr_images.isNullOrEmpty()){
            ""
        }else{
            links?.flickr_images?.get(0).orEmpty()
        },
        details = details.orEmpty(),
        wikiLink = links?.wikipedia.orEmpty()
    )
}
fun LaunchesQuery.Launch.asEntity():LaunchEntity{
    return LaunchEntity(
        id=id.orEmpty(),
        launchDateLocal= launch_date_local.toString(),
        missionName = mission_name.orEmpty(),
        flickerImage = if (links?.flickr_images.isNullOrEmpty()){
            ""
        }else{
            links?.flickr_images?.get(0).orEmpty()
        },
        details = details.orEmpty(),
        wikiLink = links?.wikipedia.orEmpty()
    )
}
