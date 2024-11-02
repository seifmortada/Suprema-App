package com.seifmortada.applications.suprema.data.remote.apis

import com.seifmortada.applications.suprema.data.remote.response.DoorCollectionResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface DoorService {
    @GET("doors")
    suspend fun getDoors(): Response<DoorCollectionResponse>

    @DELETE("doors")
    suspend fun deleteDoor(
        @Query("id") doorId: String
    ):Response<com.seifmortada.applications.suprema.data.remote.response.Response>
}