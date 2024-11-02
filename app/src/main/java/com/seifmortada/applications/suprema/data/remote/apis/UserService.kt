package com.seifmortada.applications.suprema.data.remote.apis

import com.seifmortada.applications.suprema.data.remote.response.UsersCollectionResponse
import com.seifmortada.applications.suprema.data.remote.request.add_user_dataclasses.AddUserDataClass
import com.seifmortada.applications.suprema.data.remote.response.add_user_response.AddUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("users")
    suspend fun addUser(
        @Body user: AddUserDataClass
    ): Response<AddUserResponse>

    @GET("users")
    suspend fun getUsers(): Response<UsersCollectionResponse>

    @DELETE("users")
    suspend fun deleteUser(
        @Query("id") userId: String
    ): Response<com.seifmortada.applications.suprema.data.remote.response.Response>
}